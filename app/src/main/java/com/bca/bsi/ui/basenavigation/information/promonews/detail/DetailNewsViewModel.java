package com.bca.bsi.ui.basenavigation.information.promonews.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNewsViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IDetailNewsCallback callback;

    public void setCallback(IDetailNewsCallback callback) {
        this.callback = callback;
    }

    public DetailNewsViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadNews(String token, String newsID) {
        Call<OutputResponse> call = apiInterface.getListDetailNews(token, newsID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        if (null != outputSchema.getPromoNewsList().get(0)) {
                            callback.onLoadDetailNews(outputSchema.getPromoNewsList().get(0));
                        } else {
                            callback.onFailed("");
                        }
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
