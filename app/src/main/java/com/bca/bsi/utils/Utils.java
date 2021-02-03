package com.bca.bsi.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bca.bsi.utils.constant.Constant;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null) return;
        else if (activity.getCurrentFocus() == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void setupUI(View view, final Activity activity) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static String imageURL(String path) {
        return Constant.BASE_URL + path;
    }

    public static String priceFormat(double totalPrice) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(totalPrice);
    }

    public static double roundDouble(double d, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static String getTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String formatDateFromDateString(String inputDateFormat, String outputDateFormat, String inputDate) throws ParseException {
        Date mParsedDate;
        String mOutputDateString;
        SimpleDateFormat mInputDateFormat = new SimpleDateFormat(inputDateFormat, java.util.Locale.getDefault());
        SimpleDateFormat mOutputDateFormat = new SimpleDateFormat(outputDateFormat, java.util.Locale.getDefault());
        mParsedDate = mInputDateFormat.parse(inputDate);
        mOutputDateString = mOutputDateFormat.format(mParsedDate);
        return mOutputDateString;
    }

    public static String toJSON(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    public static String removeEnter(String data) {
        return data.replaceAll("\n", "");
    }

    public static String formatDecimal(String currentFormat) {
        String output = "";
        output = currentFormat.replaceAll("[.]", ",");
        return output;
    }

    public static String formatUang(double totalPrice) {
        String currentFormat;
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        currentFormat = formatter.format(totalPrice);
        currentFormat = currentFormat.replaceAll("[,]", ".");
        return currentFormat;
    }

    public static String formatUang2(double totalPrice) {
        return String.format("%,.2f", totalPrice);
    }

    public static String formatUang3(double uang) {
        String duit = formatUang2(uang);
        String temp = duit.replace(",", "@");
        temp = temp.replace(".", ",");
        temp = temp.replace("@", ".");
        return temp;
    }


    //================================ Hitung Target ====================================
    public double getTarget(double initialCost, double monthlyCost, double ror, int durationMonth, int durationYear) {
        int n = 12 * durationYear + durationMonth;

        // making sure it convert to double type
        double r = Math.pow(1 + ror, 1 / 12.0) - 1;
        double u = Math.pow(1 + r, n);
        return (monthlyCost / r) * (u - 1) + initialCost * u;
    }

    //============================== Hitung Annualitas =================================
    public double getMonthlyCost(double initialCost, double target, double ror, int durationMonth, int durationYear) {
        int n = 12 * durationYear + durationMonth;
        double r = Math.pow(1 + ror, 1 / 12.0) - 1;
        double u = Math.pow(1 + r, n);
        return 1.0 * ((target - (initialCost * u)) * r) / (u - 1.0);
    }

    //============================== Hitung duration =================================
    // result is [y year,m month]
    public int[] getDuration(double initialCost, double monthlyCost, double target, double ror) {
        double T = target;
        double M = initialCost;
        double A = monthlyCost;
        double r = Math.pow(1 + ror, 1 / 12.0) - 1;

        double nom = Math.log(1.0 * (T + (A / r)) / (M + (A / r)));
        double denom = Math.log(1 + r);
        int n = (int) Math.round(nom / denom);

        int month = n % 12;
        int year = n / 12;
        int[] res = {month, year};
        return res;
    }

    //============================== Hitung ror =======================================
    private double M, A, T;
    private int n;
    private double eps = 0.000001;

    public double getRor(double initialCost, double monthlyCost, double target, int durationMonth, int durationYear) {
        this.M = initialCost;
        this.A = monthlyCost;
        this.T = target;
        this.n = 12 * durationYear + durationMonth;

        // check return
        double ret = getInvestmentReturn();
        if (nearZero(ret)) {
            return 0;
        } else if (ret < 0) {
            System.out.println("Negative return detected");
            return -1; //code for negative IRR
        }

        // In this step, ror must be positive
        // First we attempt to use Newton Method
        int maxiter = 1000;
        double x = 1;
        int iter = 1;
//		double maxRor = 0.22118855031; //monthly ror
        double maxRorYear = 0.5; // berarti *50%
        double maxRor = Math.pow(1 + maxRorYear, 1.0 / 12.0) - 1;
        System.out.println(maxRor);

        while (iter < maxiter) {
            x -= 1.0 * phi(x) / diffPhi(x);
            if (nearZero(phi(x)) && x > 0) {
                System.out.println("from newton");
                if (x > maxRor) {
                    System.out.println("IRR larger than " + maxRorYear * 100 + " %");
                    return 123123; // code for IRR > 10
                } else {
                    return Math.pow(1 + x, 12) - 1;
                }
            }
            iter++;
        }


        // if fails, attempt using bisection
        // Assuming eps < IRR < 10
        double a, b, c, tol;
        a = eps;
        b = maxRor;    // boundary
        tol = eps;        // tolerance for (b-a)/2
        iter = 1;

        while (iter < maxiter) {
            iter++;
            c = a + (b - a) / 2.0;
            if (nearZero(phi(c))) { // you can add tolerance for range of (a,b)
                System.out.println("from bisection");
                return Math.pow(1 + c, 12) - 1;
            }
            if (phi(c) * phi(a) > 0) {
                a = c;
            } else {
                b = c;
            }
        }
        // conclude that IRR > 10
        System.out.println("from bisection");
        System.out.println("IRR larger than " + maxRorYear * 100 + " %");
        return 123123; // code for IRR > 10


    }

    private double phi(double r) {
        return A * (Math.pow(1.0 + r, n) - 1.0) + M * r * Math.pow(1.0 + r, n) - r * T;
    }

    private double diffPhi(double r) {
        return (A * n * Math.pow(1.0 + r, n - 1)) + (M * Math.pow(1.0 + r, n)) + (n * M * r * Math.pow(1.0 + r, n - 1)) - T;
    }

    private double getInvestmentReturn() {
        return T - n * A - M;
    }

    private boolean nearZero(double val) {
        return Math.abs(val) < eps;
    }

    public static String getRealPathFromURI(Uri uri, Context context) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}
