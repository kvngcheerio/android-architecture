package com.ojaexpress.merchant.data.network.clients;

import com.ojaexpress.merchant.data.network.responses.StoreDetailResponseBody;
import com.ojaexpress.merchant.data.network.responses.StoreListResponseBody;
import com.ojaexpress.merchant.utils.ApiUtil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;



public interface StoreNetworkDataClient {

    @GET(ApiUtil.GET_ALL_ADMIN_STORES)
    Call<StoreListResponseBody> getAllAdminStores();

    @GET(ApiUtil.GET_STORE_BY_ID)
    Call<StoreDetailResponseBody> getStoreById(@Path("id") String storeId);
}
