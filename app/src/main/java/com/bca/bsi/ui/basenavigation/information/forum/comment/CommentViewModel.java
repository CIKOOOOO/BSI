package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.bca.bsi.utils.dummydata.DummyData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void loadData(String token, String profileID, String postID) {
//        callback.onLoadComment();
//        callback.onLoadComment(DummyData.getPostShareTradeList().get(1), DummyData.getCommentList(), CommentActivity.SHARE_TRADE);

        Call<OutputResponse> call = apiInterface.detailPost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    Log.e("asd", Utils.toJSON(outputResponse));
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        Forum.Post post = outputSchema.getDetailPost();

                        if (null != post) {
                            int type = -1;
                            if (null != post.getPost()) {
                                if (null != post.getPost().getPromoNews()) {
                                    type = CommentActivity.REPOST_NEWS;
                                } else {
                                    type = CommentActivity.REPOST_GENERAL;
                                }
                            } else {
                                switch (post.getType().toLowerCase()) {
                                    case Type.SHARE_TRADE:
                                        type = CommentActivity.SHARE_TRADE;
                                        break;
                                    case Type.NEWS:
                                        type = CommentActivity.NEWS;
                                        break;
                                    case Type.STRATEGY:
                                        type = CommentActivity.STRATEGY;
                                }
                            }
                            callback.onLoadComment(post, post.getCommentList(), type);
                        }

                    } else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    callback.onFailed("");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("");
            }
        });
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
