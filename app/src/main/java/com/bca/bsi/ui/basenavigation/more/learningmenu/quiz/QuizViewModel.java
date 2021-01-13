package com.bca.bsi.ui.basenavigation.more.learningmenu.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.KuisData;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.IQuizCallback;

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

    public void getData(String categoryId){
        Call<OutputResponse> call = apiInterface.getKuisData(Integer.parseInt(categoryId));
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if(response.body() != null){
                    System.out.println("INI RESPONSE BODY TIDAK SAMA DENGAN NULL");
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();

                    if(errorSchema.getErrorCode().equals("200")){
                        System.out.println("INI ERROR SCHEMA NYA 200");
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        KuisData kuisData = outputSchema.getKuisData();
                        if(kuisData != null){
                            System.out.println("DATAA ADAAAAAAAAAAAAAAAAAAAAAA");
                            callback.onRetriveData(kuisData);
                        }else {
                            callback.onFailed("Data is null");
                        }
                    }else {
                        System.out.println("ERROR SCHEMA NDA 200");
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                }else {
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
                System.out.println("nda masuk ke API"+ t.getMessage());
                callback.onFailed("Jaringan Anda hilang");
            }
        });
    }
}
