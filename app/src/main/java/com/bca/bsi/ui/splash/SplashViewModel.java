package com.bca.bsi.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashViewModel extends AndroidViewModel {

    private ISplashCallback callback;

    public void setCallback(ISplashCallback callback) {
        this.callback = callback;
    }

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAccessToken() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("grant_type", "client_credentials");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("https://sso-apigw-int.dti.co.id/auth/realms/3scale-dev/protocol/openid-connect/token")
                .addHeaders("Authorization", "Basic MmNkMzc5MTc6Zjg2MzIzZjUyMmQzZmQyNzM2ODhiNmQ0NzgyMDJkNDI=")
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String accessToken = response.getString("access_token");
                            callback.onGettingToken(accessToken);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onFailed();
                    }
                });
    }
}
