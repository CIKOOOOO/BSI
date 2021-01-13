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

    public void getData(String categoryId){
        Call<OutputResponse> call = apiInterface.getKuisData(categoryId);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if(response.body() != null){
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();

                    if(errorSchema.getErrorCode()==0){
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        KuisData kuisData = outputSchema.getKuisData();
                        if(kuisData != null){
                            callback.onRetriveData(kuisData);
                        }else {
                            callback.onFailed("Data is null");
                        }
                    }else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                }else {
                    callback.onFailed("Jaringan Anda hilang");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("Jaringan Anda hilang");
            }
        });
    }
}
