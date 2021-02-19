package com.bca.bsi.ui.basenavigation.information.forum.fragment;

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
            this.page = pages;

            List<Forum.Post> postList = new ArrayList<>();

            Call<OutputResponse> call;

            String url = "";

            switch (type.toLowerCase()) {
                case Type.TRENDING:
                    url = "forum/post/trending";
                    isLast = true;
//                    postList.addAll(DummyData.getPostNewsList());
//                    postList.addAll(DummyData.getPostShareTradeList());
//                    postList.addAll(DummyData.getPostStrategyList());
//                    callback.onLoadData(postList);
                    break;
                case Type.STRATEGY:
                    url = "forum/post/strategy";
//                    postList.addAll(DummyData.getRepostGeneralList());
//                    postList.addAll(DummyData.getPostStrategyList());
//                    callback.onLoadData(postList);
                    break;
                case Type.SHARE_TRADE:
                    url = "forum/post/share-trade";
//                    callback.onLoadData(DummyData.getPostShareTradeList());
                    break;
                case Type.NEWS:
                    url = "forum/post/news";
//                    postList.addAll(DummyData.getRepostNewsList());
//                    postList.addAll(DummyData.getPostNewsList());
//                    callback.onLoadData(postList);
                    break;
                case Type.TIMELINE:
                    url = "forum/post/timeline";
//                    postList.addAll(DummyData.getPostStrategyList());
//                    postList.addAll(DummyData.getRepostNewsList());
//                    postList.addAll(DummyData.getPostShareTradeList());
//                    postList.addAll(DummyData.getRepostGeneralList());
//                    postList.addAll(DummyData.getPostNewsList());
//                    callback.onLoadData(postList);
                    break;
            }
            call = apiInterface.getPostList(url, tokenUser, page, profileRisiko, profileID);
            String finalUrl = url;
            call.enqueue(new Callback<OutputResponse>() {
                @Override
                public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                            Log.e("asd", response.code() + "" + response.raw().request().url().toString());
//                    Log.e("asd", finalUrl + " - " + Utils.toJSON(response.body()));
                    if (null != response.body()) {
                        OutputResponse outputResponse = response.body();
                        OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                        if ("200".equals(errorSchema.getErrorCode())
                                || "206".equalsIgnoreCase(errorSchema.getErrorCode())) {
                            if ("206".equalsIgnoreCase(errorSchema.getErrorCode())) {
                                isLast = true;
//                                if(!type.equalsIgnoreCase(Type.TRENDING)){
//                                    callback.onFailed("Anda sudah mencapai halaman akhir");
//                                }
                            }
                            OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                            List<Forum.Post> postShareTrade = outputSchema.getMyPostList();
                            if (null != postShareTrade && postShareTrade.size() > 0) {
                                for (int i = 0; i < postShareTrade.size(); i++) {
                                    Forum.Post forum = postShareTrade.get(i);
//                                    Log.e("asd", Utils.toJSON(forum));
                                    switch (forum.getType()) {
                                        case Type.STRATEGY:
                                            break;
                                        case Type.SHARE_TRADE:
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
                                            break;
                                        case Type.NEWS:
                                            break;
                                    }
                                }
                            }
                            callback.onLoadData(postShareTrade);
                        } else if ("204".equalsIgnoreCase(errorSchema.getErrorCode())) {
                            isLast = true;
                            if(!type.equalsIgnoreCase(Type.TRENDING)){
                                callback.onFailed("Anda sudah mencapai halaman akhir");
                            }
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
//                    Log.e("asd", "On failed " + t.getMessage());
                    callback.onFailed("");
                }
            });
        }

//        Log.e("asd", page + "");
    }

    public void loadReportData(String token, String type, String postID) {
        Call<OutputResponse> call = apiInterface.getReportReason(token);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadReportData(outputSchema.getReportList(), postID, type);
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

//        callback.onLoadReportData(DummyData.getReportList());
    }

    public void sendDeleteConfirmation(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.deletePost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        callback.onDeleteSuccess(postID);
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

    public void savedPost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.savePost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                Log.e("asd", response.code() + "");
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
//                    Log.e("asd", Utils.toJSON(outputResponse));
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onSaveResult(outputSchema.getSavePost());
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

    public void resharePost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.sendRepost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", "" + Utils.toJSON(response.body()));
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    if (null != outputResponse.getErrorSchema()) {
                        OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                        if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                            callback.onReshareResult(true, postID);
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

    public void undoResharePost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.sendRepost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                Log.e("asd",""+response.code());
                Log.e("asd", "" + Utils.toJSON(response.body()));
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    if (null != outputResponse.getErrorSchema()) {
                        OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                        if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                            callback.onReshareResult(false, postID);
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
}
