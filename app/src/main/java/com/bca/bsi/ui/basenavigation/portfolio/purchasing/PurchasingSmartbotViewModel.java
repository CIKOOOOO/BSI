package com.bca.bsi.ui.basenavigation.portfolio.purchasing;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchasingSmartbotViewModel extends AndroidViewModel {
    private IPurchasingSmartbotCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IPurchasingSmartbotCallback callback) {
        this.callback = (IPurchasingSmartbotCallback) callback;
    }

    public PurchasingSmartbotViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadBundle(String token, String no_rekening, String reksa_id, String proportion) {
        Call<OutputResponse> call = apiInterface.getRoboHitungCustom(token, no_rekening, reksa_id, proportion);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                        callback.onLoadData(outputSchema.getBundles());
                    } else if (outputResponse.getErrorSchema().getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
                    } else {
                        callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    callback.onFail("");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFail("Mohon periksa jaringan anda");
            }
        });
    }


    public void loadBundleCustom(String token, String no_rekening, String reksa_id, String proportion) {
        Call<OutputResponse> call = apiInterface.getRoboHitungCustom(token, no_rekening, reksa_id, proportion);

        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                        callback.onLoadDataCustom(outputSchema.getBundles());
                    } else if (outputResponse.getErrorSchema().getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
                    } else {
                        callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    callback.onFail("Mohon periksa jaringan anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFail("Mohon periksa jaringan anda");
            }
        });
    }
}
