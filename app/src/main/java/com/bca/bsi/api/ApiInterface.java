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
import retrofit2.http.Query;

public interface ApiInterface {

    //    @Headers("client-id: OV4B2FXHY1Y7W0WMSUUB")
    @Headers({"Content-Type:application/json;","client-id:x","hashcode:x"})
    @POST("mobile/login")
    Call<OutputResponse> loginWith(@Header("token") String token, @Body Map<String, Object> data);

//    @Headers({"Accept: application/json", "content-type: application/json"})
    @GET("products/Reksadana")
    Call<OutputResponse> getReksaDanaData(@Header("profile_risiko") int profile_risiko);

    @GET("Reksadana/{reksaDanaID}")
    Call<OutputResponse> getDetailReksaDana(@Path("reksaDanaID") int reksaDanaID);

    @POST("")
    Call<OutputResponse> getDetailTransaksi(@Body Map<String, Object> stringObjectMap);

    @POST("")
    Call<OutputResponse> sendTransactionData(@Body Map<String, Object> stringObjectMap);

    @GET("quiz")
    Call<OutputResponse> getKuisData(@Header("category_id") int categoryId);

    @Headers({"Content-Type:application/json","client_id: OV4B2FXHY1Y7W0WMSUUB","hashcode:x"})
    @GET("smartbot/recommendation")
    Call<OutputResponse> getRoboRekomen(@Header("profil_resiko") String profil_resiko, @Header("no_rekening") String no_rekening);

    @Headers({"Content-Type:application/json","client_id: OV4B2FXHY1Y7W0WMSUUB","hashcode:x"})
    @GET("smartbot/custom")
    Call<OutputResponse> getRoboHitungCustom(@Header("no_rekening") String no_rekening, @Query("reksa-dana-id") String reksa_id,
                                             @Query("proportion") String proportion);

}
