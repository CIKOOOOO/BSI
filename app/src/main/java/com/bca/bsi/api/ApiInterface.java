package com.bca.bsi.api;


import com.bca.bsi.model.OutputResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

//    @Headers("client-id: OV4B2FXHY1Y7W0WMSUUB")
    @POST("mobile/login")
    Call<OutputResponse> loginWith(@Header("token") String token, @Body Map<String, String> data);

    @GET("Reksadana")
    Call<OutputResponse> getReksaDanaData(@Header("profile_risiko") String profile_risiko);

    @GET("Reksadana/{reksaDanaID}")
    Call<OutputResponse> getDetailReksaDana(@Path("reksaDanaID") String reksaDanaID);
}
