package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.bookmark;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookmarkViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IBookmarkCallback callback;

    public void setCallback(IBookmarkCallback callback) {
        this.callback = callback;
    }

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void savedPost(String token, String profileID, String postID) {
        Call<OutputResponse> call = apiInterface.savePost(token, profileID, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body() && null != response.body().getErrorSchema()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
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

    public void resharePost(String token, String profileID, String postID) {
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
                        callback.onLoadReportData(outputSchema.getReportList(), type, postID);
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

}
