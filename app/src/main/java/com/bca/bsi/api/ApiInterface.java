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

    @Headers({"Content-Type:application/json;", "client-id:x", "hashcode:x"})
    @POST("mobile/login")
    Call<OutputResponse> loginWith(@Header("token") String token, @Body Map<String, Object> data);

    //    @Headers({"Accept: application/json", "content-type: application/json"})
    @GET("products/Reksadana")
    Call<OutputResponse> getReksaDanaData(@Header("profile_risiko") int profile_risiko);

    @GET("products/Reksadana/{reksaDanaID}")
    Call<OutputResponse> getDetailReksaDana(@Path("reksaDanaID") int reksaDanaID);

    @Headers({"client_id: OV4B2FXHY1Y7W0WMSUUB", "hashcode: x", "Content-Type:application/json;"})
    @GET("ceksaldo")
    Call<OutputResponse> getDetailTransaksi(@Header("reksadana_id") String reksadanaID, @Header("no_rekening") String accountNumber);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode: x"})
    @POST("transaction")
    Call<OutputResponse> sendTransactionData(@Body Map<String, Object> stringObjectMap);

    @GET("quiz")
    Call<OutputResponse> getKuisData(@Header("category_id") int categoryId);
}
