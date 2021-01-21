package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

import com.bca.bsi.model.KuisData;

public interface IMateriQuizCallback {
    void onRetriveData(KuisData.UserScore userScore);

    void onFailed(String msg);
}
