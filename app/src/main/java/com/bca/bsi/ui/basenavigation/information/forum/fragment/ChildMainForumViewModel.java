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
    private int page;

    public void setCallback(IChildMainForumCallback callback) {
        this.callback = callback;
    }

    public ChildMainForumViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadForumPost(String type, int page) {
        if(this.page != page){
            this.page = page;
            List<Forum.Post> postList = new ArrayList<>();
            switch (type.toLowerCase()) {
                case Type.TRENDING:
                    postList.addAll(DummyData.getPostNewsList());
                    postList.addAll(DummyData.getPostShareTradeList());
                    postList.addAll(DummyData.getPostStrategyList());
                    callback.onLoadData(postList);
                    break;
                case Type.STRATEGY:
                    postList.addAll(DummyData.getRepostGeneralList());
                    postList.addAll(DummyData.getPostStrategyList());
                    callback.onLoadData(postList);
                    break;
                case Type.SHARE_TRADE:
                    callback.onLoadData(DummyData.getPostShareTradeList());
                    break;
                case Type.NEWS:
                    postList.addAll(DummyData.getRepostNewsList());
                    postList.addAll(DummyData.getPostNewsList());
                    callback.onLoadData(postList);
                    break;
                case Type.TIMELINE:
                    postList.addAll(DummyData.getPostStrategyList());
                    postList.addAll(DummyData.getRepostNewsList());
                    postList.addAll(DummyData.getPostShareTradeList());
                    postList.addAll(DummyData.getRepostGeneralList());
                    postList.addAll(DummyData.getPostNewsList());
                    callback.onLoadData(postList);
                    break;
            }
        }
    }

    public void loadReportData(){
        callback.onLoadReportData(DummyData.getReportList());
    }

    public void sendDeleteConfirmation(String postID){

    }

    public void savedPost(String postID){

    }

    public void likePost(String postID){

    }

    public void resharePost(String postID){

    }

    public void undoResharePost(String postID){

    }
}
