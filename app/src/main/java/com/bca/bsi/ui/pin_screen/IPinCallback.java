package com.bca.bsi.ui.pin_screen;

public interface IPinCallback {
    void onSuccessPin(Object o);

    void onWrongPin();

    void onFailed(String msg);
}
