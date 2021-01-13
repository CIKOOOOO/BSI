package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.KuisData;

public class MateriQuizViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IMateriQuizViewModel callback;

    public void setCallback(IMateriQuizViewModel callback) {
        this.callback = callback;
    }

    public MateriQuizViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void getData(String bcaId, String categoryId){

    }
}
