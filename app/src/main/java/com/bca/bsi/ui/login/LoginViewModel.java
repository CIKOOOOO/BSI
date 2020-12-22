package com.bca.bsi.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LoginViewModel extends AndroidViewModel {

    private ILoginCallback callback;

    public void setCallback(ILoginCallback callback) {
        this.callback = callback;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void loginWith(String bcaID, String password) {
        callback.onSuccess();
    }

}
