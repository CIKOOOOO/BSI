package com.bca.bsi.ui.pin_screen;

import com.bca.bsi.model.Transaction;

import java.util.List;

public interface IPinCallback {
    void onSuccessPin(Object o);

    void onSuccessPin(List<Transaction.TransactionResult> transactionResultList);

    void onWrongPin();

    void onFailed(String msg);
}
