package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductTransactionViewModel extends AndroidViewModel {

    private static final String TAG = DetailProductTransactionViewModel.class.getSimpleName();

    private IDetailProductTransactionCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IDetailProductTransactionCallback callback) {
        this.callback = callback;
    }

    public DetailProductTransactionViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadDetailTransaksi(String nomorRekening, String reksaDanaID) {
        Call<OutputResponse> call = apiInterface.getDetailTransaksi(reksaDanaID, nomorRekening);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                try {
//                    Log.e(TAG, "on response : " + response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.loadSaldo(outputSchema.getRekeningUser(), outputSchema.getDetailReksaDana());
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
