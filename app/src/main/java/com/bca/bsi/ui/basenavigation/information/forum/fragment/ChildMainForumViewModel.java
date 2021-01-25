package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.constant.Type;
import com.bca.bsi.utils.dummydata.DummyData;

import java.util.ArrayList;
import java.util.List;

public class ChildMainForumViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IChildMainForumCallback callback;

    public void setCallback(IChildMainForumCallback callback) {
        this.callback = callback;
    }

    public ChildMainForumViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadForumPost(String type) {
        switch (type.toLowerCase()) {
            case Type.TRENDING:
                List<Forum.Post> postList = new ArrayList<>();
                postList.addAll(DummyData.getPostNewsList());
                postList.addAll(DummyData.getPostShareTradeList());
                postList.addAll(DummyData.getPostStrategyList());
                callback.onLoadData(postList);
                break;
            case Type.STRATEGY:
                callback.onLoadData(DummyData.getPostStrategyList());
                break;
            case Type.SHARE_TRADE:
                callback.onLoadData(DummyData.getPostShareTradeList());
                break;
            case Type.NEWS:
                callback.onLoadData(DummyData.getPostNewsList());
                break;
            case Type.TIMELINE:
                List<Forum.Post> postList2 = new ArrayList<>();
                postList2.addAll(DummyData.getPostStrategyList());
                postList2.addAll(DummyData.getPostShareTradeList());
                postList2.addAll(DummyData.getPostNewsList());
                callback.onLoadData(postList2);
                break;
        }

    }
}
