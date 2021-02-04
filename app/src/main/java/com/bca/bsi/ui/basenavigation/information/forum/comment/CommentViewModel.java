package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.dummydata.DummyData;

public class CommentViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private ICommentCallback callback;

    public void setCallback(ICommentCallback callback) {
        this.callback = callback;
    }

    public CommentViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadData(String postID) {
//        callback.onLoadComment();
        callback.onLoadComment(DummyData.getPostShareTradeList().get(1), DummyData.getCommentList(), CommentActivity.SHARE_TRADE);
    }

    public void onReport(Forum.Comment comment) {
        callback.onLoadReport(DummyData.getReportList(), comment);
    }

    public void onReport(Forum.Post post) {
        callback.onLoadReport(DummyData.getReportList(), post);
    }

    public void reportPostOrForumWith(Forum.Report report, String profileID, String token) {

    }

    public void savePost(String postID) {

    }

    public void deletePost(String postID) {

    }

    public void likePost(String postID) {

    }

    public void sharePost(String postID) {

    }

    public void sendComment(String tokenUser, String profileID, String content) {

    }
}
