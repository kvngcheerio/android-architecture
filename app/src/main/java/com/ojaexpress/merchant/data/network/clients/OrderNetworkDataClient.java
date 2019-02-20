package com.ojaexpress.merchant.data.network.clients;


import com.ojaexpress.merchant.data.network.responses.OrderDetailResponseBody;
import com.ojaexpress.merchant.data.network.responses.OrderListResponseBody;
import com.ojaexpress.merchant.data.network.responses.OrderResponseData;
import com.ojaexpress.merchant.utils.ApiUtil;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface OrderNetworkDataClient {

    @GET(ApiUtil.GET_ALL_ADMIN_ORDERS)
    Call<OrderListResponseBody> getAllAdminOrders();

    @GET(ApiUtil.GET_ORDER_BY_ID)
    Call<OrderDetailResponseBody> getOrderById(@Path("id") String orderId);


}
