package com.bca.bsi.ui.basenavigation.portfolio;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Portfolio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortfolioViewModel extends AndroidViewModel {
    private IPortfolioCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IPortfolioCallback callback) {
        this.callback = callback;
    }

    public PortfolioViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadBundle(String profil_resiko, String no_rekening){
        Call<OutputResponse> call = apiInterface.getRoboRekomen(profil_resiko,no_rekening);
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
                        for (Portfolio p: outputSchema.getBundles()
                             ) {
                            Log.e("x",p.getExpReturn());
                        }
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
