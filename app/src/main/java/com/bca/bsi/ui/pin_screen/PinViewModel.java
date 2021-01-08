package com.bca.bsi.ui.pin_screen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinViewModel extends AndroidViewModel {

    private IPinCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IPinCallback callback) {
        this.callback = callback;
    }

    public PinViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void checkingPin(String type, String pin, String data) {
        Gson gson = new Gson();
        switch (type) {
            case ConfirmationTransactionActivity.FROM_CONFIRMATION_ACTIVITY:
                Transaction.Purchasing purchasing = gson.fromJson(data, Transaction.Purchasing.class);

                Map<String, Object> stringObjectMap = new HashMap<>();
                stringObjectMap.put("bca_id", purchasing.getBcaID());
                stringObjectMap.put("amount", purchasing.getAmount());
                stringObjectMap.put("no_rekening", purchasing.getAccountNumber());
                stringObjectMap.put("reksa_dana_id", purchasing.getReksaDanaID());
                stringObjectMap.put("transaction_type", purchasing.getTransactionType());
                stringObjectMap.put("tipe_pembayaran", purchasing.getPaymentType());
                stringObjectMap.put("nominal_biaya_pembelian", purchasing.getNominalBiayaPembelian());
                stringObjectMap.put("reksa_dana_unit", purchasing.getReksaDanaUnit());
                stringObjectMap.put("pin", pin);

                Call<OutputResponse> call = apiInterface.sendTransactionData(stringObjectMap);
                call.enqueue(new Callback<OutputResponse>() {
                    @Override
                    public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                        if (response.body() != null) {
                            OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                            if (errorSchema.getErrorCode() == 200) {
                                OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                                Transaction.TransactionResult transactionResult = response.body().getOutputSchema().getTransactionResult();
                                callback.onSuccessPin(transactionResult);
                            } else {
                                callback.onFailed(errorSchema.getErrorMessage());
                            }
                        } else {
                            callback.onFailed("Mohon cek kembali jaringan Anda");
                        }
                    }

                    @Override
                    public void onFailure(Call<OutputResponse> call, Throwable t) {
                        callback.onFailed("Mohon cek kembali jaringan Anda");
                    }
                });
                break;
        }
    }

}
