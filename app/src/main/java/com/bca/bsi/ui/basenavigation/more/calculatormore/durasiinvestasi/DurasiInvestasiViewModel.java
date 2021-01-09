package com.bca.bsi.ui.basenavigation.more.calculatormore.durasiinvestasi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.utils.Utils;

public class DurasiInvestasiViewModel extends AndroidViewModel {

    private IDurasiInvestasiCallback callback;

    public DurasiInvestasiViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCallback(IDurasiInvestasiCallback callback) {
        this.callback = callback;
    }

    public void kalkulasi(String modalAwalStr, String investBulananStr, String targetInvestasiStr,String rorStr){
        Double modalAwal = Double.parseDouble(modalAwalStr);
        Double investBulanan = Double.parseDouble(investBulananStr);
        Double targetHasilInvestasi = Double.parseDouble(targetInvestasiStr);
        Double ror = Double.parseDouble(rorStr)/100;
        String hasilStr;

        Utils utils = new Utils();
        int[] hasil = utils.getDuration(modalAwal,investBulanan,targetHasilInvestasi,ror);
        hasilStr = hasil[1]+" tahun "+hasil[0]+" bulan";
        callback.kalkulasiOutput(hasilStr,utils.formatUang(targetHasilInvestasi),utils.formatUang(modalAwal),utils.formatUang(investBulanan),utils.formatDecimal(rorStr));
    }
}
