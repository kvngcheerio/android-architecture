package com.ojaexpress.merchant.ui.orders.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.local.repositories.OrderRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class OrderDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final OrderRepository mRepository;
    private final String orderId;

    public OrderDetailViewModelFactory(OrderRepository repository, String orderId) {
        this.mRepository = repository;
        this.orderId = orderId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new OrderDetailViewModel(mRepository, orderId);
    }
}
