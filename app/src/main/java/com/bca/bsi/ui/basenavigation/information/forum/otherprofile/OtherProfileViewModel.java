package com.bca.bsi.ui.basenavigation.information.forum.otherprofile;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherProfileViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IOtherProfileCallback callback;

    public void setCallback(IOtherProfileCallback callback) {
        this.callback = callback;
    }

    public OtherProfileViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadOtherProfile(String token, String profileID) {
        Log.e("asd",profileID);
        Call<OutputResponse> call = apiInterface.getOtherProfile(token, profileID, profileID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                Log.e("asd", response.raw().request().url().toString() + "adsads");
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    Log.e("asd", Utils.toJSON(outputResponse));
                    if (null == errorSchema) {
                        callback.onFailed("");
                    } else {
                        if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                            OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                            callback.onLoadData(outputSchema.getForumProfileUser(), getListPost(outputSchema.getMyPostList()));
                        } else {
                            callback.onFailed(errorSchema.getErrorMessage());
                        }
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

    public void followUnFollowProfile(String token, String profileID, String otherProfileID) {
        Log.e("asd", otherProfileID + " - " + profileID);
        Call<OutputResponse> call = apiInterface.editFollowUnFollowProfile(token, profileID, otherProfileID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", response.code() + " follow");
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    Log.e("asd", Utils.toJSON(outputResponse));
                    if (null != outputResponse.getErrorSchema()) {
                        OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                        if (errorSchema.getErrorCode().equals("200")) {
                            OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                            callback.onLoadData(outputSchema.getForumProfileUser());
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

    private List<Forum.Post> getListPost(List<Forum.Post> postList) {
        if (null != postList && postList.size() > 0) {
            for (int i = 0; i < postList.size(); i++) {
                Forum.Post forum = postList.get(i);
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
                        postList.set(i, forum);
                        break;
                    case Type.NEWS:
                        break;
                }
            }
        }
        return postList;
    }
}
