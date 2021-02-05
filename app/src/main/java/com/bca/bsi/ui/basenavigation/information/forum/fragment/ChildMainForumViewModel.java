package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.constant.Type;
import com.bca.bsi.utils.dummydata.DummyData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildMainForumViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IChildMainForumCallback callback;
    private int page;
    private boolean isLast;

    public void setCallback(IChildMainForumCallback callback) {
        this.callback = callback;
    }

    public ChildMainForumViewModel(@NonNull Application application) {
        super(application);
        isLast = false;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadForumPost(String type, int pages, String tokenUser, String profileID, String profileRisiko) {
        if (this.page != pages && !isLast) {
            Call<OutputResponse> call;
            this.page = pages;
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
                    call = apiInterface.getPostList("forum/post/share-trade", tokenUser, page, profileRisiko, profileID);
                    call.enqueue(new Callback<OutputResponse>() {
                        @Override
                        public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                            Log.e("asd", response.code() + "" + response.raw().request().url().toString());
                            if (null != response.body()) {
                                OutputResponse outputResponse = response.body();
                                OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                                if ("200".equals(errorSchema.getErrorCode())
                                        || "206".equalsIgnoreCase(errorSchema.getErrorCode())) {
                                    if("206".equalsIgnoreCase(errorSchema.getErrorCode())){
                                       isLast = true;
                                    }
                                    OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                                    List<Forum.Post> postShareTrade = outputSchema.getMyPostList();
                                    for (int i = 0; i < postShareTrade.size(); i++) {
                                        Forum.Post forum = postShareTrade.get(i);
                                        Forum.ShareTrade shareTrade = forum.getShareTrade();
                                        String title;
                                        if (shareTrade.getType().equalsIgnoreCase("jual")) {
                                            title = "Saya baru saja menjual";
                                        } else if (shareTrade.getType().equalsIgnoreCase("beli")) {
                                            title = "Saya baru saja membeli";
                                        } else {
                                            title = "Nilai Investasi Saya";
                                        }
                                        shareTrade.setTitle(title);
                                        forum.setShareTrade(shareTrade);
                                        postShareTrade.set(i, forum);
                                    }
                                    callback.onLoadData(postShareTrade);
                                } else if ("204".equalsIgnoreCase(errorSchema.getErrorCode())) {
                                    isLast = true;
                                } else {
                                    page -= 1;
                                    callback.onFailed(errorSchema.getErrorMessage());
                                }
                            } else {
                                page -= 1;
                                callback.onFailed("");
                            }
                        }

                        @Override
                        public void onFailure(Call<OutputResponse> call, Throwable t) {
                            page -= 1;
                            Log.e("asd", "On failed " + t.getMessage());
                            callback.onFailed("");
                        }
                    });
//
//                    callback.onLoadData(DummyData.getPostShareTradeList());
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

    public void loadReportData() {
        callback.onLoadReportData(DummyData.getReportList());
    }

    public void sendDeleteConfirmation(String postID) {

    }

    public void savedPost(String postID) {

    }

    public void likePost(String postID) {

    }

    public void resharePost(String postID) {

    }

    public void undoResharePost(String postID) {

    }
}
