package com.bca.bsi.ui.basenavigation.information.promonews.news;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.dummydata.DummyData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsInformationViewModel extends AndroidViewModel {

    private INewsInformationCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(INewsInformationCallback callback) {
        this.callback = callback;
    }

    public NewsInformationViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void getNewsList(String token) {
        callback.resultOf(DummyData.getPromoNewsList());

        Call<OutputResponse> call = apiInterface.getListDetailNews(token);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.resultOf(outputSchema.getPromoNewsList());
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
