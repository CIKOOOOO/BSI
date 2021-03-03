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
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * purpose : to load data and process logical business
 */
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
                            int type;
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
                                    default:
                                        type = CommentActivity.STRATEGY;
                                }
//                                Log.e("asd", "aaa" + type);
                            }
                            callback.onLoadComment(post, post.getCommentList(), type);
                        }

                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
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

    public void onReport(String token, Forum.Comment comment) {
        Call<OutputResponse> call = apiInterface.getReportReason(token);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadReport(outputSchema.getReportList(), comment);
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
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
//        callback.onLoadReport(DummyData.getReportList(), comment);
    }

    public void onReport(String token, Forum.Post post) {
        Call<OutputResponse> call = apiInterface.getReportReason(token);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadReport(outputSchema.getReportList(), post);
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
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
//        callback.onLoadReport(DummyData.getReportList(), post);
    }

    public void savePost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.savePost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", response.code() + "");
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    Log.e("asd", Utils.toJSON(outputResponse));
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onSaveResult(outputSchema.getSavePost());
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
                    } else {
                        callback.onFailed("Save post gagal, mohon cek jaringan Anda");
                    }
                } else {
                    callback.onFailed("Save post gagal, mohon cek jaringan Anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("");
            }
        });
    }

    public void deletePost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.deletePost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        callback.onDeleteSuccess();
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
                    } else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    callback.onFailed("Hapus post gagal, mohon cek jaringan Anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                callback.onFailed("Hapus post gagal, mohon cek jaringan Anda");
            }
        });
    }

    public void deleteComment(String token, String profileID, String commentID) {
        Call<OutputResponse> call = apiInterface.deleteComment(token, profileID, commentID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        callback.onDeleteCommentSuccess(commentID);
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
                    } else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    callback.onFailed("Hapus comment gagal, mohon cek jaringan Anda");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                Log.e("asd", "On Failed : " + t.getMessage());
                callback.onFailed("Hapus comment gagal, mohon cek jaringan Anda");
            }
        });
    }

    public void likePost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.likePost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", Utils.toJSON(response.body()));
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLikeResult(outputSchema.getLikePost());
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
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

    public void sharePost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.sendRepost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                Log.e("asd",""+response.code());
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    if (null != outputResponse.getErrorSchema()) {
                        OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                        if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                            callback.onRepostSuccess();
                        } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                            callback.onSessionExpired();
                        } else {
                            callback.onFailed(errorSchema.getErrorMessage());
                        }
                    } else {
                        callback.onFailed("");
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

    public void sendComment(String tokenUser, String profileID, String postID, String content) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("comment", content);
        Log.e("asd", tokenUser + " - " + profileID + " - " + postID + " - " + content);
        Call<OutputResponse> call = apiInterface.sendComment(tokenUser, profileID, postID, objectMap);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", response.code() + " - " + Utils.toJSON(response.body()));
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onSuccessSendComment(outputSchema.getComment());
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
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

    public void reportPostOrForumWith(Forum.Report report, String postID, String token, String profileID, String type) {
        Call<OutputResponse> call = apiInterface.sendReportPost(token, profileID, report.getReportID(), postID, type);
        Log.e("asd", report.getReportID() + " ");
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                try {
//                    Log.e("asd", response.raw().request().url().toString() + " - " + " - " + response.errorBody().string() + Utils.toJSON(response.body()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        callback.onSuccessReport();
                    } else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
                    } else {
                        callback.onFailed("Mengirim report gagal. Mohon coba lagi kembali");
                    }
                } else {
                    callback.onFailed("");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                Log.e("asd", t.getMessage() + "on failed");
                callback.onFailed("");
            }
        });
    }
}
