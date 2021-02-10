package com.bca.bsi.ui.basenavigation;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.OutputResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseNavigationViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IBaseNavigatonCallback callback;

    public void setCallback(IBaseNavigatonCallback callback) {
        this.callback = callback;
    }

    public BaseNavigationViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void reportPostOrForumWith(Forum.Report report, String postID, String token, String profileID, String type) {
        Call<OutputResponse> call = apiInterface.sendReportPost(token, profileID, report.getReportID(), postID, type);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        callback.onFailed("Mengirim report sukses");
                    } else {
                        callback.onFailed("Mengirim report gagal. Mohon coba lagi kembali");
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

    public void getTipsOfTheWeek(String token) {
//        callback.onLoadTipsOfTheWeek(DummyData.getTipsOfTheWeek());

        Call<OutputResponse> call = apiInterface.getTipsOfTheWeek(token);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadTipsOfTheWeek(outputSchema.getTipsOfTheWeek());
                    } else {
                        Log.e("asd", errorSchema.getErrorMessage());
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
