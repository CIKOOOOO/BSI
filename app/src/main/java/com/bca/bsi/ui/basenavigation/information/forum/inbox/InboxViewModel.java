package com.bca.bsi.ui.basenavigation.information.forum.inbox;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.OutputResponse;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InboxViewModel extends AndroidViewModel {

    private IInboxCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IInboxCallback callback) {
        this.callback = callback;
    }

    public InboxViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadData(String token, String profileID) {
//        callback.onLoadInbox(DummyData.getInboxList());
        Call<OutputResponse> call = apiInterface.getInboxList(token, profileID);
        call.enqueue(new Callback<OutputResponse>() {
            @Override
            public void onResponse(Call<OutputResponse> call, Response<OutputResponse> response) {
                if (null != response.body()) {
                    OutputResponse outputResponse = response.body();
                    OutputResponse.ErrorSchema errorSchema = outputResponse.getErrorSchema();
                    if ("200".equalsIgnoreCase(errorSchema.getErrorCode())) {
                        OutputResponse.OutputSchema outputSchema = outputResponse.getOutputSchema();
                        callback.onLoadInbox(outputSchema.getInboxList());
                    }else if("204".equalsIgnoreCase(errorSchema.getErrorCode())){

                    }else if (errorSchema.getErrorCode().equalsIgnoreCase(Constant.SESSION_EXPIRED)) {
                        callback.onSessionExpired();
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
}
