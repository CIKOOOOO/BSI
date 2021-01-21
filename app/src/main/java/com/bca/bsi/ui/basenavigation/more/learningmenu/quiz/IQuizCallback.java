package com.bca.bsi.ui.basenavigation.more.learningmenu.quiz;

import com.bca.bsi.model.KuisData;

public interface IQuizCallback {
    void onRetriveData(KuisData kuisData);

    void onFailed(String msg);

    void onRetrieveDataGetUserScore(KuisData.UserScore userScore);

    void onFailedScoreGetUserScore(String msg);
}