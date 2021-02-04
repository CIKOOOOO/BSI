package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReksadanaProductViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IReksaDanaProductCallback callback;
    private List<Product.ReksaDana> reksaDanaList;

    public void setCallback(IReksaDanaProductCallback callback) {
        this.callback = callback;
    }

    public ReksadanaProductViewModel(@NonNull Application application) {
        super(application);
        reksaDanaList = new ArrayList<>();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void getReksaDanaList(String token, String profile_risiko) {
//        callback.resultOf(DummyData.getReksaDanaDummyList());
        if (profile_risiko.isEmpty()) return;

        Call<OutputResponse> call = apiInterface.getReksaDanaData(token, Integer.parseInt(profile_risiko));
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", "on response : " + response.code());
                if (response.body() != null) {
                    OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                    OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();

                    if (!errorSchema.getErrorCode().equals("200")) {
                        Log.e("asd", errorSchema.getErrorMessage());
                        callback.onFailed(errorSchema.getErrorMessage());
                    } else {
//                        Log.e("asd", Utils.toJSON(outputSchema));
                        reksaDanaList = outputSchema.getReksaDanaList();
                        callback.resultOf(reksaDanaList);
                    }
                } else {
                    callback.onFailed("Mohon coba lagi kembali");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                Log.e("asd", t.getMessage() + "");
                callback.onFailed("Mohon coba lagi kembali");
            }
        });
    }
}
