package com.ojaexpress.merchant.data.network.sources;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ojaexpress.merchant.data.network.clients.OrderNetworkDataClient;
import com.ojaexpress.merchant.data.network.responses.CartItemResponseData;
import com.ojaexpress.merchant.data.network.responses.OrderDetailResponseBody;
import com.ojaexpress.merchant.data.network.responses.OrderListResponseBody;
import com.ojaexpress.merchant.data.network.responses.OrderResponseData;
import com.ojaexpress.merchant.data.network.services.OrderSyncIntentService;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.CustomPreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderNetworkDataSource {

    private static final String LOG_TAG = OrderNetworkDataSource.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static volatile OrderNetworkDataSource sInstance;
    private OrderNetworkDataClient orderNetworkDataClient;
    private Context mContext;

    private final MutableLiveData<List<OrderResponseData>> orders;
    private final MutableLiveData<String> orderListFetchMsg;
    private final MutableLiveData<Integer> orderListFetchStatus;
    private final MutableLiveData<String> orderDetailFetchMsg;
    private final MutableLiveData<Integer> orderDetailFetchStatus;

    private OrderNetworkDataSource(Context context, OrderNetworkDataClient orderNetworkDataClient) {
        this.orderNetworkDataClient = orderNetworkDataClient;
        this.mContext = context;
        this.orders = new MutableLiveData<>();
        this.orderListFetchMsg = new MutableLiveData<>();
        this.orderListFetchStatus = new MutableLiveData<>();
        this.orderDetailFetchMsg = new MutableLiveData<>();
        this.orderDetailFetchStatus = new MutableLiveData<>();
    }

    public static OrderNetworkDataSource getInstance (Context context, OrderNetworkDataClient orderNetworkDataClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new OrderNetworkDataSource(context, orderNetworkDataClient);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<OrderResponseData>> getOrders() {
        return orders;
    }

    public LiveData<String> getOrderListFetchMsg() {
        return orderListFetchMsg;
    }

    public LiveData<Integer> getOrderListFetchStatus() {
        return orderListFetchStatus;
    }

    public LiveData<String> getOrderDetailFetchMsg() {
        return orderDetailFetchMsg;
    }

    public LiveData<Integer> getOrderDetailFetchStatus() {
        return orderDetailFetchStatus;
    }

    public void scheduleRecurringFetchOrderSync() {

    }

    public void startOrderFetchService() {
        Intent intent = new Intent(mContext, OrderSyncIntentService.class);
        mContext.startService(intent);
        Log.e(LOG_TAG, "Order Fetch Service created!");
    }


    public void fetchOrders() {
        Log.e(LOG_TAG, "Fetching Orders...");
        orderListFetchStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        Call<OrderListResponseBody> call = orderNetworkDataClient.getAllAdminOrders();
        call.enqueue(new Callback<OrderListResponseBody>() {
            @Override
            public void onResponse(Call<OrderListResponseBody> call, Response<OrderListResponseBody> response) {
                int statusCode = response.code();

                Log.e(LOG_TAG, "Order list url => " + call.request().url().toString() + " response returned with code ==> " + statusCode  );

                if (statusCode == 200) {
                    Log.e(LOG_TAG, "Orders fetched successfully!");
                    orderListFetchMsg.postValue("Order List fetched successfully!");
                    orderListFetchStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    OrderListResponseBody body = response.body();
                    if (body != null) {
                        orders.postValue(body.getData());
                    }

                } else {
                    Log.e(LOG_TAG, "Order fetch returned with unexpected status code => " + statusCode);
                    orderListFetchMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.GET_ALL_ADMIN_ORDERS));
                    orderListFetchStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<OrderListResponseBody> call, Throwable t) {
                Log.e(LOG_TAG, "Error fetching Orders!", t);
                orderListFetchMsg.postValue("Could not refresh orders list!");
                orderListFetchStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }

    public void fetchOrderById(String orderId) {
        orderDetailFetchStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        Call<OrderDetailResponseBody> call = orderNetworkDataClient.getOrderById(orderId);
        call.enqueue(new Callback<OrderDetailResponseBody>() {
            @Override
            public void onResponse(Call<OrderDetailResponseBody> call, Response<OrderDetailResponseBody> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    orderDetailFetchMsg.postValue("Order details fetched successfully!");
                    orderDetailFetchStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    OrderDetailResponseBody body = response.body();

                    if (body != null) {
                       orders.postValue(body.getData());
                    }

                } else {
                    orderDetailFetchMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.GET_ORDER_BY_ID));
                    orderDetailFetchStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<OrderDetailResponseBody> call, Throwable t) {
                orderDetailFetchMsg.postValue("Could not refresh selected order");
                orderDetailFetchStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }
}
