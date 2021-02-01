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
import java.io.File;
import java.util.List;

import okhttp3.MediaType;
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

    public void sendData(List<Bitmap> bitmapList) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Bitmap bitmap : bitmapList) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 30, bos);

            builder.addFormDataPart("images", "lalala", RequestBody.create(MultipartBody.FORM, bos.toByteArray()));
        }

        RequestBody requestBody = builder.build();
        Call<OutputResponse> call = apiInterface.sendData(requestBody);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                Log.e("asd", "response " + Utils.toJSON(response.body()));
//                try {
//                    Log.e("asd", "response " + response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<OutputResponse> call, Throwable t) {
                Log.e("asd", "On Failure : " + t.getMessage());
            }
        });
    }
}
