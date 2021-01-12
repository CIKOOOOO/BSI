package com.bca.bsi.ui.basenavigation.more.calculator.besarror;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.R;
import com.bca.bsi.utils.Utils;

public class BesarRoRViewModel extends AndroidViewModel {

    private IBesarRoRCallback callback;

    public BesarRoRViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCallback(IBesarRoRCallback callback) {
        this.callback = callback;
    }

    public void kalkulasi(String modalAwalStr, String investBulananStr, String targetHasilInvestasiStr, String durasiBulanStr, String durasiTahunStr){
        Double modalAwal = Double.parseDouble(modalAwalStr);
        Double investBulanan = Double.parseDouble(investBulananStr);
        Double targetHasilInvestasi = Double.parseDouble(targetHasilInvestasiStr);
        Integer durasiBulan = Integer.parseInt(durasiBulanStr);
        Integer durasiTahun = Integer.parseInt(durasiTahunStr);
        String hasilStr;

        Utils utils = new Utils();
        Double hasil = utils.getRor(modalAwal,investBulanan,targetHasilInvestasi,durasiBulan,durasiTahun);

        if (hasil == -1) {
            hasilStr = getApplication().getApplicationContext().getResources().getString(R.string.ror_bernilai_negatif);
        } else if(hasil == 123123){
            hasilStr = getApplication().getApplicationContext().getResources().getString(R.string.ror_bernilai_lebih_dari_50_persen);
        } else {
            hasil*=100;
            hasil = utils.roundDouble(hasil,4);
            hasilStr = (hasil.toString().replaceAll("[.]",","));
        }

        callback.kalkulasiOutput(hasilStr,utils.formatUang(targetHasilInvestasi),utils.formatUang(modalAwal),utils.formatUang(investBulanan));
    }
}
