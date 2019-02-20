package com.ojaexpress.merchant.ui.orders.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.local.repositories.OrderRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class OrderListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final OrderRepository repository;

    public OrderListViewModelFactory(OrderRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new OrderListViewModel(repository);
    }
}
