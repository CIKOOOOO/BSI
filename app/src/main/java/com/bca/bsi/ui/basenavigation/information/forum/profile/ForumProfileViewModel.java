package com.bca.bsi.ui.basenavigation.information.forum.profile;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.OutputResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumProfileViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IForumProfileCallback callback;

    public ForumProfileViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void setCallback(IForumProfileCallback callback) {
        this.callback = callback;
    }

    public void loadProfile(String token, String profileID) {
        Call<OutputResponse> call = apiInterface.getForumProfile(token, profileID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        Forum.User user = outputSchema.getForumProfileUser();
                        callback.onLoadData(user, outputSchema.getMyPostList(), outputSchema.getMyBookmarkList());
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

    public void loadPicture(int type, String tokenUser, String profileID) {
        String url = type == 0 ? "get-picture/forum/profile-picture" : "get-picture/forum/background-image";
        Call<OutputResponse> call = apiInterface.getForumProfilePicture(url, tokenUser, profileID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadProfilePicture(outputSchema.getProfilePictureList());
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

    public void updatePicture(int type, String tokenUser, String profileID, String imageID) {
        String url = type == 0 ? "forum/profile/me/picture/profile-pic" : "forum/profile/me/picture/background-pic";
        Call<OutputResponse> call = apiInterface.setForumProfilePicture(url, tokenUser, profileID, imageID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (response.body() != null) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        loadProfile(tokenUser, profileID);
                        callback.onDismissBottomNavigation();
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

    public void editUsername(String token, String profileID, String username) {
        Call<OutputResponse> call = apiInterface.editUsername(token, profileID, username);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", response.code() + "");
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        callback.onLoadUsername(username);
                    } else if ("409".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        callback.onDismissBottomNavigation();
                        callback.onFailed(errorSchema.getErrorMessage());
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
