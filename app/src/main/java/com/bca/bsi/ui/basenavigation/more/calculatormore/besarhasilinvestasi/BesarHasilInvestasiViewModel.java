package com.bca.bsi.ui.basenavigation.more.calculatormore.besarhasilinvestasi;

import android.app.Application;

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
        Double modalAwal = Double.parseDouble(modalAwalStr);
        Double investBulanan = Double.parseDouble(investBulananStr);
        Double ror = Double.parseDouble(rorStr)/100;
        Integer durasiBulan = Integer.parseInt(durasiBulanStr);
        Integer durasiTahun = Integer.parseInt(durasiTahunStr);

        Utils utils = new Utils();
        Double hasil = utils.getTarget(modalAwal,investBulanan,ror,durasiBulan,durasiTahun);

        callback.kalkulasiOutput(utils.formatUang(hasil),utils.formatUang(modalAwal),utils.formatUang(investBulanan),utils.formatDecimal(rorStr));
    }
}
