package com.ojaexpress.merchant.data.network.clients;

import com.ojaexpress.merchant.data.network.responses.ProductDetailResponseBody;
import com.ojaexpress.merchant.data.network.responses.ProductListResponseBody;
import com.ojaexpress.merchant.utils.ApiUtil;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface ProductNetworkDataClient {

    @GET(ApiUtil.GET_ALL_ADMIN_PRODUCTS)
    Call<ProductListResponseBody> getAllAdminProducts();

    @GET(ApiUtil.GET_PRODUCT_BY_ID)
    Call<ProductDetailResponseBody> getProductById(@Path("id") String productId);
}
