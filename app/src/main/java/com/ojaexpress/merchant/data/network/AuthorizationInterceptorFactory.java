package com.ojaexpress.merchant.data.network;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by TerryCheerio on 6/4/18.
 */

public class AuthorizationInterceptorFactory {

    private static OkHttpClient.Builder getBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor());
    }

    public static OkHttpClient getAnonymousClient() {
        return getBuilder().addInterceptor(getAnonymousRequestInterceptor()).build();
    }

    public static OkHttpClient getAuthenticatedClient(String accessToken) {
        return getBuilder().addInterceptor(getAuthenticatedRequestInterceptor(accessToken)).build();
    }

    private static Interceptor getAnonymousRequestInterceptor() {
        return chain -> {
            Request newRequest = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .build();
            return chain.proceed(newRequest);
        };
    }

    private static Interceptor getAuthenticatedRequestInterceptor(String accessToken) {
        return chain -> {
            Request newRequest = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + accessToken )
                    .build();
            return chain.proceed(newRequest);
        };
    }
}
