package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.dummydata.DummyData;

import java.util.ArrayList;

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

    public void loadData(String postID) {
//        callback.onLoadComment();
        callback.onLoadComment(DummyData.getPostShareTradeList().get(2), new ArrayList<Forum.Comment>(), CommentActivity.SHARE_TRADE);
    }

    public void onReport(Forum.Comment comment) {

    }

}
