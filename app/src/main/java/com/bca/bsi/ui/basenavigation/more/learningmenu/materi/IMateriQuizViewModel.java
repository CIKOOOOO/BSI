package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

import com.bca.bsi.model.KuisData;

public interface IMateriQuizViewModel {
    void onRetriveData(KuisData.UserScore userScore);

    void onFailed(String msg);
}
