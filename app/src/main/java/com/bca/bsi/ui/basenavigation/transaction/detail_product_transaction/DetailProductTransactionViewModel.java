package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductTransactionViewModel extends AndroidViewModel {

    private IDetailProductTransactionCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IDetailProductTransactionCallback callback) {
        this.callback = callback;
    }

    public DetailProductTransactionViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadDetailTransaksi(String accountNumber, String reksaDanaID) {

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("account_number", accountNumber);
        objectMap.put("reksadana_id", reksaDanaID);

        Call<OutputResponse> call = apiInterface.getDetailTransaksi(objectMap);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.loadSaldo(outputSchema.getBcaUser(), outputSchema.getDetailReksaDana());
                    } else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    callback.onFailed("Mohon periksa jaringan Anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("Mohon periksa jaringan Anda");
            }
        });
    }


}
