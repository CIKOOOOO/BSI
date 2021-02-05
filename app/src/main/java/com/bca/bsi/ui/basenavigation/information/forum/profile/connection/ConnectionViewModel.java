package com.bca.bsi.ui.basenavigation.information.forum.profile.connection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.constant.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionViewModel extends AndroidViewModel {

    private IConnectionCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IConnectionCallback callback) {
        this.callback = callback;
    }

    public ConnectionViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadConnection(String type, String token, String profileID) {
        String url = type.equalsIgnoreCase(Type.FOLLOWING) ? "forum/profile/me/following" : "forum/profile/me/follower";

        Call<OutputResponse> call = apiInterface.getSelfConnection(url, token, profileID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadData(outputSchema.getConnectionList());
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
