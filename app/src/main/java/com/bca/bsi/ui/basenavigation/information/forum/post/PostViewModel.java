package com.bca.bsi.ui.basenavigation.information.forum.post;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.dummydata.DummyData;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends AndroidViewModel {

    private IPostCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IPostCallback callback) {
        this.callback = callback;
    }

    public PostViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadCategoryData() {
        callback.onLoadCategoryData(DummyData.getCategoryList());
    }

//    public void sendData(List<Bitmap> bitmapList) {
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        for (Bitmap bitmap : bitmapList) {
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 30, bos);
//
//            builder.addFormDataPart("images", "lalala", RequestBody.create(MultipartBody.FORM, bos.toByteArray()));
//        }
//
//        RequestBody requestBody = builder.build();
//        Call<OutputResponse> call = apiInterface.sendData(requestBody);
//        call.enqueue(new Callback<OutputResponse>() {
//            @Override
//            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
//                Log.e("asd", "response " + Utils.toJSON(response.body()));
////                try {
////                    Log.e("asd", "response " + response.errorBody().string());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//            }
//
//            @Override
//            public void onFailure(Call<OutputResponse> call, Throwable t) {
//                Log.e("asd", "On Failure : " + t.getMessage());
//            }
//        });
//    }

    public void loadDetail(String token, String postID) {


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
}
