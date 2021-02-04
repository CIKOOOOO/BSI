package com.bca.bsi.ui.basenavigation.transaction.sell_transaction;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSellViewModel extends AndroidViewModel {

    private IDetailSellCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IDetailSellCallback callback) {
        this.callback = callback;
    }

    public DetailSellViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadData(String token, String reksaDana) {
        Call<OutputResponse> call = apiInterface.getDetailReksaDana(token, Integer.parseInt(reksaDana));
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        Product.DetailReksaDana detailReksaDana = outputSchema.getDetailReksaDana();
                        callback.onLoadData(detailReksaDana);
                    } else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    callback.onFailed("");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("");
            }
        });

    }
}
