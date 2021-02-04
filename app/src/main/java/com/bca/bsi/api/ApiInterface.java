package com.bca.bsi.api;


import com.bca.bsi.model.OutputResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @Headers({"Content-Type:application/json;", "hashcode:x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @POST("mobile/login")
    Call<OutputResponse> loginWith(@Body Map<String, Object> data);

    //    @Headers({"Accept: application/json", "content-type: application/json"})
    @GET("products/Reksadana")
    Call<OutputResponse> getReksaDanaData(@Header("profile-risiko") int profile_risiko);

    @GET("products/Reksadana/{reksaDanaID}")
    Call<OutputResponse> getDetailReksaDana(@Path("reksaDanaID") int reksaDanaID);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("ceksaldo")
    Call<OutputResponse> getDetailTransaksi(@Header("reksadana-id") String reksadanaID, @Header("no-rekening") String accountNumber);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode: x"})
    @POST
    Call<OutputResponse> sendTransactionData(@Url String URL, @Header("token-user") String token, @Header("BCA-ID") String bcaID, @Header("PIN") String pin
            , @Body Map<String, Object> stringObjectMap);

    @GET("quiz")
    Call<OutputResponse> getKuisData(@Header("category-id") int categoryId);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("smartbot/recommendation")
    Call<OutputResponse> getRoboRekomen(@Header("profil-resiko") String profil_resiko, @Header("no-rekening") String no_rekening);

    @Headers({"client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("smartbot/recommendation/custom")
    Call<OutputResponse> getRoboHitungCustom(@Header("no-rekening") String no_rekening, @Query("reksa-dana-id") String reksa_id,
                                             @Query("proportion") String proportion);

    @GET("quiz/score")
    Call<OutputResponse> getUserScore(@Header("category-id") int categoryId, @Header("bca-id") String bcaId);

    @PUT("quiz/score")
    Call<OutputResponse> putUserScore(@Header("category-id") int categoryId, @Header("bca-id") String bcaId, @Header("score") int score);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("portfolio")
    Call<OutputResponse> getInformationPortfolioData(@Header("token") String token, @Header("no-rekening") String nomor_rekening);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("transaction")
    Call<OutputResponse> getHistoryTransaction(@Header("token") String token, @Header("no-rekening") String nomor_rekening);

    @Headers({"client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode: x"})
    @GET("tips/{id}")
    Call<OutputResponse> getTipsOfTheWeek(@Header("token") String token, @Path("id") String id);

    //    @Multipart
    @Headers({"client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @POST("attachment")
    Call<OutputResponse> sendData(@Body RequestBody file);
//    Call<OutputResponse> sendData(@Part MultipartBody.Part part);
}
