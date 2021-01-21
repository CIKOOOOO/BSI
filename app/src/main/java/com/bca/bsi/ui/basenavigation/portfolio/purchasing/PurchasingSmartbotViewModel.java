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

    public void loadBundle(String no_rekening, String reksa_id){
        Call<OutputResponse> call = apiInterface.getRoboHitungCustom(no_rekening,reksa_id,"");
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("a","tes1"+response.code());
                if(response.body()!=null){
                    Log.e("b","tes2");
                    OutputResponse outputResponse = response.body();
                    if(outputResponse.getErrorSchema().getErrorCode().equals("200")){
                        Log.e("c","tes3");
                        OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                        callback.onLoadData(outputSchema.getBundles());
                    } else {
                        Log.e("d","tes4");
                        callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    Log.e("e","tes5");
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
