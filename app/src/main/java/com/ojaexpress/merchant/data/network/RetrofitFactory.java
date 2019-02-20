package com.ojaexpress.merchant.data.network;

import com.ojaexpress.merchant.utils.ApiUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitFactory {

    private static final Object LOCK = new Object();
    private static volatile Retrofit authenticatedInstance;
    private static volatile Retrofit anonymousInstance;

    public static Retrofit getRetrofit() {

        if (anonymousInstance == null) {
            synchronized (LOCK) {
                if (anonymousInstance == null) {
                    anonymousInstance = new Retrofit.Builder().baseUrl(ApiUtil.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(AuthorizationInterceptorFactory.getAnonymousClient())
                            .build();
                }
            }
        }
        return anonymousInstance;
    }

    public static Retrofit getRetrofit(String accessToken) {
        if (authenticatedInstance == null) {
            synchronized (LOCK) {
                if (authenticatedInstance == null) {
                    authenticatedInstance = new Retrofit.Builder().baseUrl(ApiUtil.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(AuthorizationInterceptorFactory.getAuthenticatedClient(accessToken))
                            .build();
                }
            }
        }
        return authenticatedInstance;
    }

}


