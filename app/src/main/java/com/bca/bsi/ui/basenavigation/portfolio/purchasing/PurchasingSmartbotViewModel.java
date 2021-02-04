package com.bca.bsi.ui.basenavigation.portfolio.purchasing;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;

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
        Log.e("asd", reksa_id);
        Call<OutputResponse> call = apiInterface.getRoboHitungCustom(token, no_rekening, reksa_id, proportion);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                try {
//                    Log.e("asd", "tes1" + response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (response.body() != null) {
                    Log.e("asd", "tes2");
                    OutputResponse outputResponse = response.body();
                    if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                        Log.e("asd", "tes3");
                        OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                        callback.onLoadData(outputSchema.getBundles());
                    } else {
                        Log.e("asd", "tes4");
                        callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    Log.e("asd", "tes5");
                    callback.onFail("Mohon periksa jaringan anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                Log.e("asd", "tes6");
                callback.onFail("Mohon periksa jaringan anda");
            }
        });
    }

    public void loadBundleCustom(String token, String no_rekening, String reksa_id, String proportion) {
        Log.e("asd", reksa_id);
        Call<OutputResponse> call = apiInterface.getRoboHitungCustom(token, no_rekening, reksa_id, proportion);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                try {
//                    Log.e("asd", "tes1" + response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (response.body() != null) {
                    Log.e("asd", "tes2");
                    OutputResponse outputResponse = response.body();
                    if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                        Log.e("asd", "tes3");
                        OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                        callback.onLoadDataCustom(outputSchema.getBundles());
                    } else {
                        Log.e("asd", "tes4");
                        callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    Log.e("asd", "tes5");
                    callback.onFail("Mohon periksa jaringan anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                Log.e("asd", "tes6");
                callback.onFail("Mohon periksa jaringan anda");
            }
        });
    }
}
