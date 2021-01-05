package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class DetailProductTransactionViewModel extends AndroidViewModel {

    private IDetailProductTransactionCallback callback;

    public void setCallback(IDetailProductTransactionCallback callback) {
        this.callback = callback;
    }

    public DetailProductTransactionViewModel(@NonNull Application application) {
        super(application);
    }

    public void getSaldo(String accountNumber){
        callback.loadSaldo(5000000);
    }


}
