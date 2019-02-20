package com.ojaexpress.merchant.ui.orders.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.models.CartItem;
import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.data.local.models.OrderCartItem;
import com.ojaexpress.merchant.data.local.models.OrderWithCartItems;
import com.ojaexpress.merchant.data.local.repositories.OrderRepository;

import java.util.List;


public class OrderDetailViewModel extends ViewModel {

    private final OrderRepository orderRepository;
    private final String orderId;
    private final LiveData<OrderWithCartItems> ordercart;
    //private final LiveData<List<CartItem>> cartitem;


    public OrderDetailViewModel(OrderRepository orderRepository, String orderId) {
        this.orderRepository = orderRepository;
        this.orderId = orderId;
        this.ordercart = this.orderRepository.getOrderById(orderId);

    }



    public  LiveData<OrderWithCartItems> getOrderCart(){
        return ordercart;
    }



//    void fetchOrderDetail() {
//        orderRepository.startFetchOrderDetailService(orderId);
//    }



    LiveData<Integer> getFetchStatus() {
        return orderRepository.getOrderDetailsFetchStatus();
    }

    LiveData<String> getFetchMsg() {
        return orderRepository.getOrderDetailsFetchMsg();
    }
}
