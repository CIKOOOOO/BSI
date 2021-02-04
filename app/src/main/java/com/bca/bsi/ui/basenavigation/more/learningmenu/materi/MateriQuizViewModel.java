package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.KuisData;
import com.bca.bsi.model.OutputResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriQuizViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IMateriQuizCallback callback;

    public void setCallback(IMateriQuizCallback callback) {
        this.callback = callback;
    }

    public MateriQuizViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void getScoreData(String token, String bcaId, String categoryId) {
        Call<OutputResponse> call = apiInterface.getUserScore(token, Integer.parseInt(categoryId), bcaId);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    System.out.println("INI RESPONSE BODY TIDAK SAMA DENGAN NULL");
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();

                    if (errorSchema.getErrorCode().equals("200")) {
                        System.out.println("INI ERROR SCHEMA NYA 200");
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        KuisData.UserScore userScore = outputSchema.getUserScore();

                        if (userScore != null) {
                            System.out.println("DATAA ADAAAAAAAAAAAAAAAAAAAAAA");
                            callback.onRetriveData(userScore);
                        } else {
                            callback.onFailed("Data is null");
                        }

                    } else if (errorSchema.getErrorCode().equals("500")) {
                        KuisData.UserScore userScore = null;
                        callback.onRetriveData(userScore);
                    } else {
                        System.out.println("ERROR SCHEMA NDA 200");
                        callback.onFailed(errorSchema.getErrorMessage());
                    }

                } else {
                    System.out.println("Ini data null");
                    callback.onFailed("Jaringan Anda hilang");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                System.out.println("nda masuk ke API" + t.getMessage());
                callback.onFailed("Jaringan Anda hilang");
            }
        });
    }
}
