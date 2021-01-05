package com.bca.bsi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bca.bsi.R;
import com.bca.bsi.utils.constant.Constant;

public class PrefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void setLogin(boolean login) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), login);
        editor.apply();
    }

    public void setUser(String uid, String name, String phoneNumber, String email, String main_address_id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_uid), uid);
        editor.putString(context.getString(R.string.pref_name), name);
        editor.putString(context.getString(R.string.pref_phone_number), phoneNumber);
        editor.putString(context.getString(R.string.pref_main_address), main_address_id);
        editor.putString(context.getString(R.string.pref_email), email);
        editor.apply();
    }

    public void logOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_uid), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_name), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_phone_number), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_email), Constant.EMPTY);
        editor.putBoolean(context.getString(R.string.pref_login_status), false);
        editor.apply();
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name), name);
        editor.apply();
    }

    public void setAccountNumber(String accountNumber) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_account_number), accountNumber);
        editor.apply();
    }

    public void setAddress(String address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_main_address), address);
        editor.apply();
    }

    public void setPhoneNumber(String phoneNumber) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_phone_number), phoneNumber);
        editor.apply();
    }

    public void setTokenFCM(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_token_fcm), token);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public String getUID() {
        return sharedPreferences.getString(context.getString(R.string.pref_uid), Constant.EMPTY);
    }

    public String getName() {
        return sharedPreferences.getString(context.getString(R.string.pref_name), Constant.EMPTY);
    }

    public String getEmail() {
        return sharedPreferences.getString(context.getString(R.string.pref_email), Constant.EMPTY);
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString(context.getString(R.string.pref_phone_number), Constant.EMPTY);
    }

    public String getMainAddressID() {
        return sharedPreferences.getString(context.getString(R.string.pref_main_address), Constant.EMPTY);
    }

    public String getTokenFCM() {
        return sharedPreferences.getString(context.getString(R.string.pref_token_fcm), Constant.EMPTY);
    }

    public String getAccountNumber() {
        return sharedPreferences.getString(context.getString(R.string.pref_account_number), Constant.EMPTY);
    }

}
