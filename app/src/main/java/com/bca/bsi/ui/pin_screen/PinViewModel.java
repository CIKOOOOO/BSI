package com.bca.bsi.ui.pin_screen;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public void checkingPin(String type, String pin, String bcaID, String data, String token) {
        Gson gson = new Gson();
        Call<OutputResponse> call;
        switch (type) {
            case ConfirmationTransactionActivity.PURCHASE_FROM_CONFIRMATION_ACTIVITY:
            case ConfirmationTransactionActivity.SELLING_FROM_CONFIRMATION_ACTIVITY:
//                Log.e("asd", "masuk switch case");
                Transaction.Purchasing purchasing = gson.fromJson(data, Transaction.Purchasing.class);

                Map<String, Object> stringObjectMap = new HashMap<>();
                stringObjectMap.put("bca_id", purchasing.getBcaID());
                stringObjectMap.put("amount", purchasing.getAmount());
                stringObjectMap.put("no_rekening", purchasing.getAccountNumber());
                stringObjectMap.put("reksa_dana_id", purchasing.getReksaDanaID());
                stringObjectMap.put("transaction_type", purchasing.getTransactionType());
                stringObjectMap.put("tipe_pembayaran", purchasing.getPaymentType());
                stringObjectMap.put("nominal_biaya_transaksi", purchasing.getNominalBiayaPembelian());
                stringObjectMap.put("reksa_dana_unit", purchasing.getReksaDanaUnit());
                stringObjectMap.put("nab", purchasing.getNab());

                call = apiInterface.sendTransactionData("pin-validation",token, bcaID, pin, stringObjectMap);

                call.enqueue(new Callback<OutputResponse>() {
                    @Override
                    public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                        Log.e("asd", "on response " + response.code());
                        if (response.body() != null) {
                            OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                            if (errorSchema.getErrorCode().equals("200")) {
                                OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                                Transaction.TransactionResult transactionResult = outputSchema.getTransactionResult();
                                callback.onSuccessPin(transactionResult);
                            } else {
                                Log.e("asd", Utils.toJSON(response.body()));
                                callback.onFailed(errorSchema.getErrorMessage());
                            }
                        } else {
                            callback.onFailed("Mohon cek kembali jaringan Anda");
                        }
                    }

                    @Override
                    public void onFailure(Call<OutputResponse> call, Throwable t) {
//                        Log.e("asd", "on failed " + t.getMessage());
                        callback.onFailed("Mohon cek kembali jaringan Anda");
                    }
                });
                break;
            case Type.PURCHASING_WITH_SMARTBOT:
//                Map<String, Object> objectMap =

                try {
                    JSONArray jsonArray = new JSONArray(data);
                    List<Transaction.Purchasing> purchasingList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Transaction.Purchasing purchasing1 = new Transaction.Purchasing();
                        purchasing1.setBcaID(jsonObject.getString("bca_id"));
                        purchasing1.setAmount(jsonObject.getString("amount"));
                        purchasing1.setAccountNumber(jsonObject.getString("no_rekening"));
                        purchasing1.setReksaDanaID(jsonObject.getString("reksa_dana_id"));
                        purchasing1.setTransactionType(jsonObject.getString("transaction_type"));
                        purchasing1.setPaymentType(jsonObject.getString("tipe_pembayaran"));
                        purchasing1.setNominalBiayaTransaksi(jsonObject.getString("nominal_biaya_transaksi"));
                        purchasing1.setReksaDanaUnit(jsonObject.getString("reksa_dana_unit"));
                        purchasing1.setNab(jsonObject.getString("nab"));
                        purchasingList.add(purchasing1);
                    }

                    Map<String, Object> objectMap = new HashMap<>();
                    objectMap.put("transaction", purchasingList);

                    call = apiInterface.sendTransactionData("pin-validation/robo",token, bcaID, pin, objectMap);

                    call.enqueue(new Callback<OutputResponse>() {
                        @Override
                        public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                            try {
//                                Log.e("asd", "on response " + response.errorBody().string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
                            if (response.body() != null) {
                                OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                                if (errorSchema.getErrorCode().equals("200")) {
                                    Log.e("asd", "200");
                                    OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                                    callback.onSuccessPin(outputSchema.getTransactionResultList());
                                } else {
//                                    Log.e("asd", Utils.toJSON(response.body()));
//                                    Log.e("asd", response.raw().request().url() + "");
//                                    try {
//                                        Log.e("asd", response.errorBody().string());
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
                                    callback.onFailed(errorSchema.getErrorMessage());
                                }
                            } else {
                                callback.onFailed("Mohon cek kembali jaringan Anda");
                            }
                        }

                        @Override
                        public void onFailure(Call<OutputResponse> call, Throwable t) {
                            Log.e("asd", "on failed " + t.getMessage());
                            callback.onFailed("Mohon cek kembali jaringan Anda");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

}
