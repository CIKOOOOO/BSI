package com.bca.bsi.ui.basenavigation.more.learningmenu.quiz;

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

public class QuizViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IQuizCallback callback;

    public void setCallback(IQuizCallback callback) {
        this.callback = callback;
    }

    public QuizViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void getData(String token, String categoryId) {
        Call<OutputResponse> call = apiInterface.getKuisData(token, Integer.parseInt(categoryId));
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
                        KuisData kuisData = outputSchema.getKuisData();
                        if (kuisData != null) {
                            System.out.println("DATAA ADAAAAAAAAAAAAAAAAAAAAAA");
                            callback.onRetriveData(kuisData);
                        } else {
                            callback.onFailed("Data is null");
                        }
                    } else {
                        System.out.println("ERROR SCHEMA NDA 200");
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    System.out.println("Ini data null");
                    callback.onFailed("Jaringan Anda hilang");
                }

//                try {
//                    System.out.println("error code: "+response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }


            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                System.out.println("nda masuk ke API" + t.getMessage());
                callback.onFailed("Jaringan Anda hilang");
            }
        });
    }

    public void getScore(String token, String bcaId, String categoryId) {
        Call<OutputResponse> call = apiInterface.getUserScore(token, Integer.parseInt(categoryId), bcaId);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    System.out.println("GETUSERSCORE: INI RESPONSE BODY TIDAK SAMA DENGAN NULL");
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();

                    if (errorSchema.getErrorCode().equals("200")) {
                        System.out.println("GETUSERSCORE: INI ERROR SCHEMA NYA 200");
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        KuisData.UserScore userScore = outputSchema.getUserScore();
                        if (userScore != null) {
                            System.out.println("GETUSERSCORE: DATAA ADAAAAAAAAAAAAAAAAAAAAAA");
                            callback.onRetrieveDataGetUserScore(userScore);
                        } else {
                            callback.onFailedScoreGetUserScore("Data is null");
                        }
                    } else {
                        System.out.println("GETUSERSCORE: ERROR SCHEMA NDA 200");
                        callback.onFailedScoreGetUserScore(errorSchema.getErrorMessage());
                    }

                } else {
                    System.out.println("GETUSERSCORE: Ini data null");
                    callback.onFailedScoreGetUserScore("Jaringan Anda hilang");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                System.out.println("GETUSERSCORE: nda masuk ke API" + t.getMessage());
                callback.onFailedScoreGetUserScore("Jaringan Anda hilang");
            }
        });
    }

    public void putUserScore(String token, String bcaId, String categoryId, int score) {
        Call<OutputResponse> call = apiInterface.putUserScore(token, Integer.parseInt(categoryId), bcaId, score);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    System.out.println("PUTUSERSCORE: INI RESPONSE BODY TIDAK SAMA DENGAN NULL");
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        System.out.println("PUTUSERSCORE: INI ERROR SCHEMA NYA 200");
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        KuisData.UserScore userScore = outputSchema.getUserScore();
                        if (userScore != null) {
                            System.out.println("PUTUSERSCORE: DATAA ADAAAAAAAAAAAAAAAAAAAAAA");
                            callback.onRetrieveDataGetUserScore(userScore);
                        } else {
                            callback.onFailedScoreGetUserScore("Data is null");
                        }
                    } else {
                        System.out.println("PUTUSERSCORE: ERROR SCHEMA NDA 200");
                        callback.onFailedScoreGetUserScore(errorSchema.getErrorMessage());
                    }
                } else {
                    System.out.println("PUTUSERSCORE: Ini data null");
                    callback.onFailedScoreGetUserScore("Jaringan Anda hilang");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                System.out.println("GETUSERSCORE: nda masuk ke API" + t.getMessage());
                callback.onFailedScoreGetUserScore("Jaringan Anda hilang");
            }
        });
    }
}
