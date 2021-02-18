package com.bca.bsi.ui.basenavigation.portfolio.produkmenu;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukChoiceViewModel extends AndroidViewModel {
    private IProductChoiceCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IProductChoiceCallback callback) {
        this.callback = callback;
    }

    public ProdukChoiceViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadProducts(String token, String profil_resiko) {
        if (profil_resiko.isEmpty()) {
            return;
        }
        Call<OutputResponse> call = apiInterface.getReksaDanaData(token, Integer.parseInt(profil_resiko));
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                        callback.onLoadData(outputSchema.getReksaDanaList());
                    } else {
                        callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                    }
                } else {
                    callback.onFail("");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFail("");
            }
        });
    }

    public void setFiltering(List<Integer> filterList, int sortPosition, String token, String profileRisiko) {
        if (filterList.size() == 0) {
            sortData(sortPosition, new ArrayList<>());
        } else {
            String filter = "";
            for (Integer integer : filterList) {
                filter += integer + ",";
            }
            filter = filter.substring(0, filter.length() - 1);
            Call<OutputResponse> call = apiInterface.getFilterListProduct(token, profileRisiko, filter);
            call.enqueue(new Callback<OutputResponse>() {
                @Override
                public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                    if (response.body() != null) {
                        OutputResponse outputResponse = response.body();
                        if (outputResponse.getErrorSchema().getErrorCode().equals("200")) {
                            OutputResponse.OutputSchema outputSchema = response.body().getOutputSchema();
                            sortData(sortPosition, outputSchema.getReksaDanaList());
                        } else {
                            callback.onFail(outputResponse.getErrorSchema().getErrorMessage());
                        }
                    } else {
                        callback.onFail("");
                    }
                }

                @Override
                public void onFailure(Call<OutputResponse> call, Throwable t) {
                    callback.onFail("");
                }
            });
        }
    }

    public void sortData(int sortPosition, List<Product.ReksaDana> products) {
        switch (sortPosition) {
            case 0:
                Collections.sort(products, new Comparator<Product.ReksaDana>() {
                    @Override
                    public int compare(Product.ReksaDana reksaDana, Product.ReksaDana t1) {
                        return reksaDana.getName().compareToIgnoreCase(t1.getName());
                    }
                });
                break;
            case 1:
                Collections.sort(products, new Comparator<Product.ReksaDana>() {
                    @Override
                    public int compare(Product.ReksaDana reksaDana, Product.ReksaDana t1) {
                        return t1.getName().compareToIgnoreCase(reksaDana.getName());
                    }
                });
                break;
            case 2:
                Collections.sort(products, new Comparator<Product.ReksaDana>() {
                    @Override
                    public int compare(Product.ReksaDana reksaDana, Product.ReksaDana t1) {
                        return Double.compare(Double.parseDouble(reksaDana.getNab()), Double.parseDouble(t1.getNab()));
                    }
                });
                break;
            case 3:
                Collections.sort(products, new Comparator<Product.ReksaDana>() {
                    @Override
                    public int compare(Product.ReksaDana reksaDana, Product.ReksaDana t1) {
                        return Double.compare(Double.parseDouble(t1.getNab()), Double.parseDouble(reksaDana.getNab()));
                    }
                });
                break;
        }
        callback.onLoadData(products);
    }
}
