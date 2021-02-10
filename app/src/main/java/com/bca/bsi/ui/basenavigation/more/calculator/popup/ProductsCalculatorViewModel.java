package com.bca.bsi.ui.basenavigation.more.calculator.popup;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan.IBesarInvestasiBulananCallback;
import com.bca.bsi.ui.basenavigation.more.calculator.popup.IProductsCalculatorCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsCalculatorViewModel extends AndroidViewModel {

    private IProductsCalculatorCallback callback;
    private IBesarInvestasiBulananCallback callbackBIB;
    private ApiInterface apiInterface;

    public void setCallbackProduct(IProductsCalculatorCallback callback) {
        this.callback = callback;
    }

    public void setCallbackBesarInvestasi(IBesarInvestasiBulananCallback callbackBIB) {
        this.callbackBIB = callbackBIB;
    }

    public ProductsCalculatorViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    /*
    public void retrieveDetailReksaDana(String token, int reksaDanaID) {

        Call<OutputResponse> call = apiInterface.getDetailReksaDana(token, reksaDanaID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                    OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                    if (!errorSchema.getErrorCode().equals("200")) {
                        callback.onFailed(errorSchema.getErrorMessage());
                    } else {
                        System.out.println("INI DARI CALLBACK UNTUK CALLBACKBIB "+outputSchema.getDetailReksaDana().getName());
                        if(null != outputSchema.getDetailReksaDana()) {
                            callback.retrieveDetailReksaDana(outputSchema.getDetailReksaDana());
                        }else{

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("Mohon cek jaringan Anda");
            }
        });
    }
    */


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
                    } else {
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

                        System.out.println("INI DETAIL REKSA YANG DIPILIH : "+outputSchema.getDetailReksaDana().getName());
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

    public void loadProducts(String profile_resiko, String token){

        if(profile_resiko.isEmpty()){
            System.out.println("MASUK SINIIII> RESIKO KU KOSONG DOONG");
            return;
        }

        Call<OutputResponse> call = apiInterface.getReksaDanaData(token, Integer.parseInt(profile_resiko));
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("a","tes1"+response.code());
                if(response.body()!=null){
                    Log.e("b","tes2");
                    OutputResponse outputResponse = response.body();
                    if(outputResponse.getErrorSchema().getErrorCode().equals("200")){
                        Log.e("c","tes3");
                        OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                        System.out.println("HASILNYA INIIII : "+outputSchema.getReksaDanaList().toString());
                        callback.onLoadData(outputSchema.getReksaDanaList());
                    } else {
                        Log.e("d","tes4");
                        callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    Log.e("e","tes5");
                    callback.onFail("Mohon periksa jaringan anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFail("Mohon periksa jaringan anda");
            }
        });

    }
}
