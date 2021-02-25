package com.bca.bsi.api;


import com.bca.bsi.utils.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton of Retrofit client
 */
public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
