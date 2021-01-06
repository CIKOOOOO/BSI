package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.dummydata.DummyData;

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

    public void getReksaDanaList(String profile_risiko) {
        callback.resultOf(DummyData.getReksaDanaDummyList());
//        Call<OutputResponse> call = apiInterface.getReksaDanaData(profile_risiko);
//        call.enqueue(new Callback<OutputResponse>() {
//            @Override
//            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                if (response.body() != null) {
//                    OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
//                    OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
//
//                    if (errorSchema.getErrorCode() != 200) {
//                        callback.onFailed(errorSchema.getErrorMessage());
//                    } else {
//                        reksaDanaList = outputSchema.getReksaDanaList();
//                        callback.resultOf(reksaDanaList);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OutputResponse> call, Throwable t) {
//                callback.onFailed("Mohon coba lagi kembali");
//            }
//        });
    }
}
