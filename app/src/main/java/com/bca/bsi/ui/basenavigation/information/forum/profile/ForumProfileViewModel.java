package com.bca.bsi.ui.basenavigation.information.forum.profile;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.constant.Type;

import java.util.List;

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

                        List<Forum.Post> postShareTrade = getPostList(outputSchema.getMyPostList());
                        List<Forum.Post> bookmarkList = getPostList(outputSchema.getMyBookmarkList());

                        callback.onLoadData(user, postShareTrade, bookmarkList);
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

    private List<Forum.Post> getPostList(List<Forum.Post> postShareTrade) {
        if (null != postShareTrade && postShareTrade.size() > 0) {

            for (int i = 0; i < postShareTrade.size(); i++) {

                Forum.Post post = postShareTrade.get(i);
                String type;
                if (null == post) {
                    return null;
                }

                if (null == post.getPost()) {
                    type = post.getType();
                } else if (null != post.getPost().getPromoNews()) {
                    type = Type.REPOST_NEWS;
                } else {
                    type = Type.REPOST;
                }

                switch (type.toLowerCase()) {
                    case Type.TRENDING:
                        break;
                    case Type.STRATEGY:
                        break;
                    case Type.SHARE_TRADE:
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
                        break;
                    case Type.NEWS:
                        break;
                    case Type.TIMELINE:
                        break;
                }
            }
        }

        return postShareTrade;
    }
}
