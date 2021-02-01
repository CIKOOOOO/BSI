package com.bca.bsi.ui.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private ILoginCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(ILoginCallback callback) {
        this.callback = callback;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loginWith(String token, String bcaID, String password) {

//        callback.onSuccess(null, null);

        Map<String, Object> stringStringMap = new HashMap<>();
        stringStringMap.put("bca_id", bcaID);
        stringStringMap.put("password", getEncryptedPassword(password));

        Log.e("asd", stringStringMap.toString());

        Call<OutputResponse> call = apiInterface.loginWith(stringStringMap);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                try {
//                    Log.e("asd", response.code() + " - " + response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (response.body() != null) {
                    OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        User.ForumUser forumUser = response.body().getOutputSchema().getForumUser();
                        User.WelmaUser welmaUser = response.body().getOutputSchema().getWelmaUser();
                        callback.onSuccess(forumUser, welmaUser);
                    } else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    callback.onFailed("Terdapat kesalahan jaringan");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                Log.e("asd", t.getMessage() + "on fail");
                callback.onFailed("Terdapat kesalahan jaringan");
            }
        });
    }

    public void getAccessToken(String bcaID, String password) {
        callback.onSuccess(null, null);
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
                            loginWith(accessToken, bcaID, password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onFailed("Terdapat kesalahan jaringan");
                    }
                });
    }

    private String getEncryptedPassword(String pass) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(pass.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
