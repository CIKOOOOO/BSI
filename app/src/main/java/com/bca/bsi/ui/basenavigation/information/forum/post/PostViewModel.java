package com.bca.bsi.ui.basenavigation.information.forum.post;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void loadCategoryData(String tokenUser) {
//        callback.onLoadCategoryData(DummyData.getCategoryList());

        Call<OutputResponse> call = apiInterface.getCategoryList(tokenUser);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if (null != errorSchema) {
                        if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                            OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                            callback.onLoadCategoryData(outputSchema.getCategoryList());
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

    public void loadDetail(String token, Forum.Post post) {
        switch (post.getType().toLowerCase()) {
            case "general":

                break;
            case Type.STRATEGY:

                break;
            case Type.SHARE_TRADE:

                break;
            case Type.NEWS:

                break;
        }
    }

    public void sendNewPost(String token, HashMap<String, Object> hashMap) {
        List<String> imageEncodedList = new ArrayList<>();

        for (Bitmap bitmap : (List<Bitmap>) hashMap.get("post_attachment")) {
            Log.e("asd", Utils.encodeBitmap(bitmap));
            imageEncodedList.add(Utils.encodeBitmap(bitmap));
        }

        hashMap.put("post_attachment", imageEncodedList);

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
}
