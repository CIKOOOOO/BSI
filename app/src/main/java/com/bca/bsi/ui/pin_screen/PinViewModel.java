package com.bca.bsi.ui.pin_screen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class PinViewModel extends AndroidViewModel {

    private IPinCallback callback;

    public void setCallback(IPinCallback callback) {
        this.callback = callback;
    }

    public PinViewModel(@NonNull Application application) {
        super(application);
    }

    public void checkingPin(String pin){
        callback.onSuccessPin();
    }

}
