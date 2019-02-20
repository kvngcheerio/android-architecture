package com.ojaexpress.merchant.ui.orders.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.data.local.repositories.OrderRepository;

import java.util.List;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class OrderListViewModel extends ViewModel {

    private final OrderRepository orderRepository;
    private final LiveData<List<Order>> orders;

    public OrderListViewModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        orders = orderRepository.getAllOrders();
    }

    public LiveData<List<Order>> getOrders() {
        return orders;
    }

    void fetchLatestOrders() {
        orderRepository.startFetchOrderService();
    }

    LiveData<Integer> getFetchStatus() {
        return orderRepository.getOrderListFetchStatus();
    }

    LiveData<String> getFetchMsg() {
        return orderRepository.getOrderListFetchMsg();
    }
}
