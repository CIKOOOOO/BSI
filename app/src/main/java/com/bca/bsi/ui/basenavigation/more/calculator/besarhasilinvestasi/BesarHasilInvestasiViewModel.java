package com.bca.bsi.ui.basenavigation.more.calculator.besarhasilinvestasi;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.utils.Utils;

public class BesarHasilInvestasiViewModel extends AndroidViewModel {

    private IBesarHasilInvestasiCallback callback;

    public void setCallback(IBesarHasilInvestasiCallback callback) {
        this.callback = callback;
    }

    public BesarHasilInvestasiViewModel(@NonNull Application application) {
        super(application);
    }

    public void kalkulasi(String modalAwalStr, String investBulananStr, String rorStr, String durasiBulanStr, String durasiTahunStr){
        Log.e("rorRoRroR",rorStr);

        Double modalAwal = Double.parseDouble(modalAwalStr);
        Double investBulanan = Double.parseDouble(investBulananStr);
        Double ror = Double.parseDouble(rorStr)/100;
        Integer durasiBulan = Integer.parseInt(durasiBulanStr);
        Integer durasiTahun = Integer.parseInt(durasiTahunStr);

        Utils utils = new Utils();
        Double hasil = utils.getTarget(modalAwal,investBulanan,ror,durasiBulan,durasiTahun);

        //callback.kalkulasiOutput(utils.formatUang(hasil),utils.formatUang(modalAwal),utils.formatUang(investBulanan),utils.formatDecimal(rorStr));
        callback.kalkulasiOutput(utils.formatUang(hasil),utils.formatUang(modalAwal),utils.formatUang(investBulanan),rorStr);
    }
}
