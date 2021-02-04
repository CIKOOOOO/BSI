package com.bca.bsi.ui.basenavigation.information.forum.post.direct;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.User;
import com.bca.bsi.utils.dummydata.DummyData;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectShareViewModel extends AndroidViewModel {

    private IDirectShareCallback callback;
    private ApiInterface apiInterface;
    private List<User.ForumUser> chosenUserList;
    private List<User.ForumUser> forumUserList;
    private List<User.ForumUser> visibleForumList;

    public void setCallback(IDirectShareCallback callback) {
        this.callback = callback;
    }

    public DirectShareViewModel(@NonNull Application application) {
        super(application);
        chosenUserList = new ArrayList<>();
        forumUserList = new ArrayList<>();
        visibleForumList = new ArrayList<>();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public String[] getChosenUserList() {
        String[] strings = new String[chosenUserList.size()];
        for (int i = 0; i < chosenUserList.size(); i++) {
            strings[i] = chosenUserList.get(i).getProfileID();
        }
        return strings;
    }

    public void loadUser(String token, String username) {
        Call<OutputResponse> call = apiInterface.getDirectUserList(token, username);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        forumUserList = outputSchema.getDirectUserList();
                        if (0 != chosenUserList.size())
                            compareData();
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


        this.forumUserList = DummyData.getForumUser();
        this.visibleForumList.addAll(this.forumUserList);
        callback.onLoadForumUser(DummyData.getForumUser());
    }

    public void sendNewPost(String token, HashMap<String, Object> hashMap) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("post_id_source", hashMap.get("post_id_source").toString());
        builder.addFormDataPart("profile_id", hashMap.get("profile_id").toString());
        builder.addFormDataPart("post_privacy", hashMap.get("post_privacy").toString());
        builder.addFormDataPart("post_text", hashMap.get("post_text").toString());
        builder.addFormDataPart("news_id", hashMap.get("news_id").toString());
        builder.addFormDataPart("post_attachment", "");
        builder.addFormDataPart("post_category_id", hashMap.get("post_category_id").toString());
        builder.addFormDataPart("repost_from", hashMap.get("repost_from").toString());
        builder.addFormDataPart("visible_to_id", hashMap.get("visible_to_id").toString());
        builder.addFormDataPart("reksa_dana_id", hashMap.get("reksa_dana_id").toString());
        builder.addFormDataPart("transaction_type", hashMap.get("transaction_type").toString());
        builder.addFormDataPart("share_trade_type", hashMap.get("share_trade_type").toString());

        for (Bitmap bitmap : (List<Bitmap>) hashMap.get("post_attachment")) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 30, bos);

            builder.addFormDataPart("post_attachment", "x", RequestBody.create(MultipartBody.FORM, bos.toByteArray()));
        }

        RequestBody requestBody = builder.build();

        Call<OutputResponse> call = apiInterface.sendNewPost(token, requestBody);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body()) {
                    OutputResponse.ErrorSchema errorSchema = response.body().getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        callback.onSuccessPost();
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

    public void addChosenUser(User.ForumUser forumUser) {
        this.chosenUserList.add(forumUser);
        for (int i = 0; i < this.visibleForumList.size(); i++) {
            User.ForumUser forumUser1 = this.visibleForumList.get(i);
            if (forumUser1.getProfileID().equalsIgnoreCase(forumUser.getProfileID())) {
                this.visibleForumList.remove(i);
                break;
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
        callback.onLoadChosenForumUser(this.chosenUserList);
    }

    public void removeChosenUser(User.ForumUser forumUser) {
        for (int i = 0; i < this.chosenUserList.size(); i++) {
            User.ForumUser forumUser1 = this.chosenUserList.get(i);
            if (forumUser1.getProfileID().equalsIgnoreCase(forumUser.getProfileID())) {
                this.chosenUserList.remove(i);
                break;
            }
        }
        for (int i = 0; i < this.forumUserList.size(); i++) {
            User.ForumUser forumUser1 = this.forumUserList.get(i);
            if (forumUser1.getProfileID().equalsIgnoreCase(forumUser.getProfileID())) {
                this.visibleForumList.add(forumUser);
                break;
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
        callback.onLoadChosenForumUser(this.chosenUserList);
    }

    private void compareData() {
        this.visibleForumList.addAll(this.forumUserList);
        int removeCounter = 0;
        for (int i = 0; i < this.forumUserList.size(); i++) {
            boolean isChosen = false;
            User.ForumUser forumUser = this.forumUserList.get(i);
            for (int j = 0; j < this.chosenUserList.size(); j++) {
                User.ForumUser forumUser1 = this.chosenUserList.get(j);
                if (forumUser.getProfileID().equalsIgnoreCase(forumUser1.getProfileID())) {
                    isChosen = true;
                    break;
                }
            }

            if (isChosen) {
                removeCounter++;
                this.visibleForumList.remove(i + removeCounter);
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
    }
}
