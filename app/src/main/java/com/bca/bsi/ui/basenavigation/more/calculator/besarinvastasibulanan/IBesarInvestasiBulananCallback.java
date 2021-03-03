package com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan;

import com.bca.bsi.model.Product;

import java.util.List;

public interface IBesarInvestasiBulananCallback {
    void kalkulasiOutput(String formatTargetHasilInvest, String formatModalAwal, String formatRoR, String formatHasil);

    void resultOf(List<Product.ReksaDana> reksaDanaList);

    void onFailed(String msg);

    void onSessionExpired();
}
