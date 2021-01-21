package com.bca.bsi.ui.basenavigation.portfolio.information;

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

public class InformationHistoryViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IInformationHistoryCallback callback;

    public void setCallback(IInformationHistoryCallback callback) {
        this.callback = callback;
    }

    public InformationHistoryViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadData(int type, String token, String accountNumber) {
        if (type == 1) {
            loadPortfolio(token, accountNumber);
        } else {
            loadTransactionHistory(token, accountNumber);
        }
    }

    private void loadPortfolio(String token, String accountNumber) {
        Call<OutputResponse> call = apiInterface.getInformationPortfolioData(token, accountNumber);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", response.code()+" ");
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadPortfolioData(outputSchema.getInformationList());
                    } else {
                        callback.onFailed(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    callback.onFailed("Mohon periksa jaringan Anda.");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("Mohon periksa jaringan Anda.");
            }
        });
    }

    private void loadTransactionHistory(String token, String accountNumber) {
        Call<OutputResponse> call = apiInterface.getHistoryTransaction(token, accountNumber);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadHistoryTransaction(outputSchema.getHistoryList());
                    } else {
                        callback.onFailed(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    callback.onFailed("Mohon periksa jaringan Anda.");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("Mohon periksa jaringan Anda.");
            }
        });
    }
}
