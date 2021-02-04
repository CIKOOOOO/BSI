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

    @Headers({"Content-Type:application/json;", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @POST("mobile/login")
    Call<OutputResponse> loginWith(@Body Map<String, Object> data);

    @Headers({"Accept: application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("products/reksadana")
    Call<OutputResponse> getReksaDanaData(@Header("token-user") String token, @Header("profile-risiko") int profile_risiko);

    @Headers({"Accept: application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("products/reksadana/{reksaDanaID}")
    Call<OutputResponse> getDetailReksaDana(@Header("token-user") String token, @Path("reksaDanaID") int reksaDanaID);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("ceksaldo")
    Call<OutputResponse> getDetailTransaksi(@Header("token-user") String token, @Header("reksadana-id") String reksadanaID, @Header("no-rekening") String accountNumber);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode: x"})
    @POST
    Call<OutputResponse> sendTransactionData(@Url String URL, @Header("token-user") String token, @Header("BCA-ID") String bcaID, @Header("PIN") String pin
            , @Body Map<String, Object> stringObjectMap);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("quiz")
    Call<OutputResponse> getKuisData(@Header("token-user") String token,@Header("category-id") int categoryId);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("smartbot/recommendation")
    Call<OutputResponse> getRoboRekomen(@Header("token-user") String token,@Header("profil-resiko") String profil_resiko, @Header("no-rekening") String no_rekening);

    @Headers({"client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("smartbot/recommendation/custom")
    Call<OutputResponse> getRoboHitungCustom(@Header("token-user") String token,@Header("no-rekening") String no_rekening, @Query("reksa-dana-id") String reksa_id,
                                             @Query("proportion") String proportion);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("quiz/score")
    Call<OutputResponse> getUserScore(@Header("token-user") String token,@Header("category-id") int categoryId, @Header("bca-id") String bcaId);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @PUT("quiz/score")
    Call<OutputResponse> putUserScore(@Header("token-user") String token,@Header("category-id") int categoryId, @Header("bca-id") String bcaId, @Header("score") int score);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("portfolio")
    Call<OutputResponse> getInformationPortfolioData(@Header("token-user") String token, @Header("no-rekening") String nomor_rekening);

    @Headers({"Content-Type:application/json", "client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode:x"})
    @GET("transaction")
    Call<OutputResponse> getHistoryTransaction(@Header("token-user") String token, @Header("no-rekening") String nomor_rekening);

    @Headers({"client-id: OV4B2FXHY1Y7W0WMSUUB", "hashcode: x"})
    @GET("tips")
    Call<OutputResponse> getTipsOfTheWeek(@Header("token-user") String token);

    //    @Multipart
    @Headers({"client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @POST("attachment")
    Call<OutputResponse> sendData(@Header("token-user") String token,@Body RequestBody file);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("forum/profile/me")
    Call<OutputResponse> getForumProfile(@Header("token-user") String tokenUser
            , @Header("profile-id") String profileID);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET
    Call<OutputResponse> getForumProfilePicture(@Url String url, @Header("token-user") String tokenUser
            , @Header("profile-id") String profileID);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @PUT
    Call<OutputResponse> setForumProfilePicture(@Url String url, @Header("token-user") String tokenUser
            , @Header("profile-id") String profileID, @Body String s);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("user")
    Call<OutputResponse> getDirectUserList(@Header("token-user") String tokenUser, @Query("username") String username);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @POST("syalala")
    Call<OutputResponse> sendNewPost(@Header("token-user") String tokenUser, @Body RequestBody requestBody);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("news")
    Call<OutputResponse> getListDetailNews(@Header("token-user") String tokenUser);

    @Headers({"hashcode: x", "client-id: OV4B2FXHY1Y7W0WMSUUB"})
    @GET("news/{news_id}")
    Call<OutputResponse> getListDetailNews(@Header("token-user") String tokenUser, @Path("news_id") String newsID);
}
