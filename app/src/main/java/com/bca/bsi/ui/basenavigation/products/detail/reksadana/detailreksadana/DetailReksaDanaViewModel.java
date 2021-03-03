package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.constant.Constant;

import java.util.ArrayList;
import java.util.List;

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

    public void loadDetailReksaDana(String token, int reksaDanaID) {
        List<Product.Performance> performanceList = new ArrayList<>();

        Call<OutputResponse> call = apiInterface.getDetailReksaDana(token, reksaDanaID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                    OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                    if (!errorSchema.getErrorCode().equals("200")) {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
                    }  else {
                        if (outputSchema.getDetailReksaDana().getKinerja1Bulan() != null) {
                            performanceList.add(new Product.Performance("1 Bulan", Double.parseDouble(outputSchema.getDetailReksaDana().getKinerja1Bulan())));
                        }

                        if (outputSchema.getDetailReksaDana().getKinerja6Bulan() != null) {
                            performanceList.add(new Product.Performance("6 Bulan", Double.parseDouble(outputSchema.getDetailReksaDana().getKinerja6Bulan())));
                        }

                        if (outputSchema.getDetailReksaDana().getYearToDate() != null) {
                            performanceList.add(new Product.Performance("YTD", Double.parseDouble(outputSchema.getDetailReksaDana().getYearToDate())));
                        }

                        if (outputSchema.getDetailReksaDana().getKinerja1Tahun() != null) {
                            performanceList.add(new Product.Performance("1 Tahun", Double.parseDouble(outputSchema.getDetailReksaDana().getKinerja1Tahun())));
                        }

                        if (outputSchema.getDetailReksaDana().getKinerja3Tahun() != null) {
                            performanceList.add(new Product.Performance("3 Tahun", Double.parseDouble(outputSchema.getDetailReksaDana().getKinerja3Tahun())));
                        }

                        if (outputSchema.getDetailReksaDana().getKinerja5Tahun() != null) {
                            performanceList.add(new Product.Performance("5 Tahun", Double.parseDouble(outputSchema.getDetailReksaDana().getKinerja5Tahun())));
                        }

                        callback.onLoadReksaDanaDetail(outputSchema.getDetailReksaDana(), performanceList);
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
