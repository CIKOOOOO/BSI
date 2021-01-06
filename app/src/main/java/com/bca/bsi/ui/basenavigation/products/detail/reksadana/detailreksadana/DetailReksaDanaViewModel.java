package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailReksaDanaViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IDetailReksaDanaCallback callback;

    public void setCallback(IDetailReksaDanaCallback callback) {
        this.callback = callback;
    }

    public DetailReksaDanaViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadDetailReksaDana(String reksaDanaID){
        Call<OutputResponse> call = apiInterface.getDetailReksaDana(reksaDanaID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if(response.body() != null){
                    OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                    OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                    if(errorSchema.getErrorCode() != 200){
                        callback.onFailed(errorSchema.getErrorMessage());
                    }else{
                        
                    }
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("Mohon cek jaringan Anda");
            }
        });
    }
}
