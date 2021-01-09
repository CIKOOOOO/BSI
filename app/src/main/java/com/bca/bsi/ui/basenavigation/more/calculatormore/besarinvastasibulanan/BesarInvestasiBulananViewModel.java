package com.bca.bsi.ui.basenavigation.more.calculatormore.besarinvastasibulanan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.utils.Utils;

public class BesarInvestasiBulananViewModel extends AndroidViewModel {

    private IBesarInvestasiBulananCallback callback;

    public BesarInvestasiBulananViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCallback(IBesarInvestasiBulananCallback callback) {
        this.callback = callback;
    }

    public void kalkulasi(String modalAwalStr, String targerHasilStr, String rorStr, String durasiBulanStr, String durasiTahunStr){
        Double modalAwal = Double.parseDouble(modalAwalStr);
        Double targetHasil = Double.parseDouble(targerHasilStr);
        Double ror = Double.parseDouble(rorStr)/100;
        Integer durasiBulan = Integer.parseInt(durasiBulanStr);
        Integer durasiTahun = Integer.parseInt(durasiTahunStr);

        Utils utils = new Utils();
        Double hasil = utils.getMonthlyCost(modalAwal,targetHasil,ror,durasiBulan,durasiTahun);

        callback.kalkulasiOutput(utils.formatUang(targetHasil),utils.formatUang(modalAwal),utils.formatDecimal(rorStr),utils.formatUang(hasil));
    }
}
