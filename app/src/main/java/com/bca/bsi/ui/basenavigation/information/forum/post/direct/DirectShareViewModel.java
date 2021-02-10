package com.bca.bsi.ui.basenavigation.information.forum.post.direct;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.User;
import com.bca.bsi.utils.Utils;
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

    public void clearForumList() {
        this.forumUserList = new ArrayList<>();
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

    public void loadUser(String token, String username, String profileID) {
        clearForumList();
        Call<OutputResponse> call = apiInterface.getDirectUserList(token, profileID, username);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                try {
//                    Log.e("asd", "aaa" + response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Log.e("aaaaa", "" + Utils.toJSON(response.body()) + " - " + response.raw().request().url().toString());
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        forumUserList = outputSchema.getDirectUserList();
                        if (0 != chosenUserList.size())
                            compareData();
                        else {
                            visibleForumList.addAll(forumUserList);
                            callback.onLoadForumUser(forumUserList);
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


//        this.forumUserList = DummyData.getForumUser();
//        this.visibleForumList.addAll(this.forumUserList);
//        callback.onLoadForumUser(DummyData.getForumUser());
    }

    public void sendNewPost(String token, HashMap<String, Object> hashMap) {

        List<String> imageEncodedList = new ArrayList<>();

        for (Bitmap bitmap : (List<Bitmap>) hashMap.get("post_attachment")) {
            Log.e("asd", Utils.encodeBitmap(bitmap));
            imageEncodedList.add(Utils.encodeBitmap(bitmap));
        }

        hashMap.put("post_attachment", imageEncodedList);

        Log.e("asd", hashMap.toString());
        Call<OutputResponse> call = apiInterface.sendNewPost(token, hashMap);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", response.code() + " - ");
//                try {
//                    Log.e("asd", response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
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
                Log.e("asd", "Onfailed " + t.getMessage());
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
        this.visibleForumList.clear();
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
                if (null != this.visibleForumList.get(i + removeCounter)) {
                    this.visibleForumList.remove(i + removeCounter);
                    removeCounter++;
                }
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
    }
}
