package com.bca.bsi.ui.basenavigation.information.forum.post.direct;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<String> getChosenUserList() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < chosenUserList.size(); i++) {
            Log.e("asd", chosenUserList.get(i).getProfileID());
            stringList.add(chosenUserList.get(i).getProfileID());
        }
        return stringList;
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
//                Log.e("asd", "11" + Utils.toJSON(response.body()));
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

    /**
     * This condition appear when user edit the post with privacy direct
     * so we need to get those list of direct user and show it
     *
     * @param token
     * @param postID
     */
    public void loadExistingChosenUserList(String token, String postID) {
//        Log.e("asd", postID);
        Call<OutputResponse> call = apiInterface.getDirectChosenUserList(token, postID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                Log.e("asd", Utils.toJSON(response.body()));
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (errorSchema.getErrorCode().equals("200")) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        chosenUserList.addAll(outputSchema.getDirectUserList());
                        compareData();
//                        Log.e("asd", chosenUserList.size() + " size");
                        callback.onLoadChosenForumUser(chosenUserList);
                    } else {
                        callback.onFailed(errorSchema.getErrorMessage());
                    }
                } else {
                    callback.onFailed("");
                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
//                Log.e("asd", t.getMessage() + " on fail");
                callback.onFailed("");
            }
        });
    }

    public void editPost(String token, String postID, Map<String, Object> hashMap) {
        Call<OutputResponse> call = apiInterface.editPost(token, postID, hashMap);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                Log.e("asd", response.code() + " - " + Utils.toJSON(response.body()));
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
//                Log.e("asd", "Onfailed " + t.getMessage());
                callback.onFailed("");
            }
        });
    }

    public void sendNewPost(String token, Map<String, Object> hashMap) {

//        List<String> imageEncodedList = new ArrayList<>();

//        for (Bitmap bitmap : (List<Bitmap>) hashMap.get("post_attachment")) {
////            Log.e("asd", Utils.encodeBitmap(bitmap));
//            imageEncodedList.add(Utils.encodeBitmap(bitmap));
//        }
//
//        hashMap.put("post_attachment", imageEncodedList);

        Log.e("asd", hashMap.toString());
        Call<OutputResponse> call = apiInterface.sendNewPost(token, hashMap);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", response.code() + " - ");
//                try {
////                    Log.e("asd", response.errorBody().string());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
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

    /**
     * condition when user add chosen user
     * we should remove from visible data in UI
     * then show it back
     *
     * @param forumUser
     */
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

    /**
     * condition when user remove chosen user
     * we should compare data, check if profile id is visible in UI
     * if visible, then we add to visible forum list
     *
     * @param forumUser
     */
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

    /**
     * condition when user search id
     * we should remove the same id in visible forum list with chosen forum list
     */
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
                if (null != this.visibleForumList.get(i - removeCounter)) {
                    this.visibleForumList.remove(i - removeCounter);
                    removeCounter++;
                }
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
    }
}
