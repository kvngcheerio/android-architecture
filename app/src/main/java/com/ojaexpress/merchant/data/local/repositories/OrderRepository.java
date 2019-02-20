package com.ojaexpress.merchant.data.local.repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.ojaexpress.merchant.AppExecutors;
import com.ojaexpress.merchant.data.local.daos.AddressDao;
import com.ojaexpress.merchant.data.local.daos.CartItemDao;
import com.ojaexpress.merchant.data.local.daos.OrderDao;
import com.ojaexpress.merchant.data.local.models.Address;

import com.ojaexpress.merchant.data.local.models.CartItem;
import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.data.local.models.OrderCartItem;
import com.ojaexpress.merchant.data.local.models.OrderWithCartItems;
import com.ojaexpress.merchant.data.network.responses.CartItemResponseData;
import com.ojaexpress.merchant.data.network.responses.OrderResponseData;
import com.ojaexpress.merchant.data.network.sources.OrderNetworkDataSource;

import java.util.ArrayList;
import java.util.List;



public class OrderRepository {

    private static final String LOG_TAG = OrderRepository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static volatile OrderRepository sInstance;
    private boolean dataInitialized = false;
    private final OrderDao orderDao;
    private final AddressDao addressDao;
    private final CartItemDao cartItemDao;



    private final OrderNetworkDataSource orderNetworkDataSource;
    private final AppExecutors executors;

    private OrderRepository (OrderDao orderDao, AddressDao addressDao, CartItemDao cartItemDao,
                             OrderNetworkDataSource orderNetworkDataSource,
                             AppExecutors executors) {
        this.orderDao = orderDao;
        this.addressDao = addressDao;
        this.cartItemDao = cartItemDao;
        this.orderNetworkDataSource = orderNetworkDataSource;
        this.executors = executors;

        LiveData<List<OrderResponseData>> networkData = orderNetworkDataSource.getOrders();
        networkData.observeForever(orderResponseDataList -> {
            if (orderResponseDataList == null) return;
            executors.diskIO().execute(() -> {
                deleteOldData();
                Log.d(LOG_TAG, "Old orders deleted");
                insertOrdersFromOrderResponseDataList(orderResponseDataList);
            });
        });

        LiveData<List<CartItemResponseData>> cartData = orderNetworkDataSource.getCartitems();
        cartData.observeForever(cartItemResponseDataList -> {
            if (cartItemResponseDataList == null) return;
            executors.diskIO().execute(() -> {
                deleteOldData();
                Log.d(LOG_TAG, "Old carts deleted");
                insertOrderDetailResponseDataList(cartItemResponseDataList);
            });
        });
    }

    private void insertOrdersFromOrderResponseDataList(List<OrderResponseData> responseDataList ) {
        List<Order> orders = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        for (OrderResponseData data: responseDataList) {
            if (data.getDeliveryAddressId() != null) {
                addresses.add(Address.fromOrderResponseData(data));
            }


            orders.add(Order.fromOrderResponseData(data));
        }
            addressDao.bulkInsert(addresses.toArray(new Address[addresses.size()]));
            orderDao.bulkInsert(orders.toArray(new Order[orders.size()]));
            Log.d(LOG_TAG, "New orders inserted");

    }

private  void insertOrderDetailResponseDataList(List<CartItemResponseData> responseDataList){
    List<CartItem> cartItems = new ArrayList<>();
    for (CartItemResponseData data: responseDataList) {
        cartItems.add(CartItem.fromCartItemResponseData(data));
    }

    cartItemDao.bulkInsert(cartItems.toArray(new CartItem[cartItems.size()]));
        Log.d(LOG_TAG, "New Cart items inserted");
}



    public synchronized static OrderRepository getInstance(OrderDao orderDao, AddressDao addressDao, CartItemDao cartItemDao,
                                                           OrderNetworkDataSource orderNetworkDataSource,
                                                           AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new OrderRepository(orderDao, addressDao, cartItemDao, orderNetworkDataSource, executors);
                    Log.d(LOG_TAG, "Made new repository");
                }
            }
        }
        return sInstance;
    }

    private synchronized void initializeData() {
        if (dataInitialized) return;
        dataInitialized = true;

        orderNetworkDataSource.scheduleRecurringFetchOrderSync();
        executors.diskIO().execute(() -> {
            if (isFetchNeeded()) {
                startFetchOrderService();
            }
        });

    }

    public LiveData<List<Order>> getAllOrders() {
        initializeData();
        return orderDao.getAll();
    }


    public LiveData<OrderWithCartItems> getOrderById(String id){
        initializeData();
        return orderDao.getOrderWithCartsById(id);

    }

    public LiveData<Integer> getOrderListFetchStatus() {
        return orderNetworkDataSource.getOrderListFetchStatus();
    }

    public LiveData<String> getOrderListFetchMsg() {
        return orderNetworkDataSource.getOrderListFetchMsg();
    }

    public LiveData<Integer> getOrderDetailsFetchStatus() {
        return orderNetworkDataSource.getOrderListFetchStatus();
    }

    public LiveData<String> getOrderDetailsFetchMsg() {
        return orderNetworkDataSource.getOrderListFetchMsg();
    }

    private void deleteOldData() {

    }

    private boolean isFetchNeeded() {
        return true;
    }

    public void startFetchOrderService() {
        orderNetworkDataSource.startOrderFetchService();
    }

    public void startFetchOrderDetailService() {orderNetworkDataSource.startOrderDetailFetchService();}


}
