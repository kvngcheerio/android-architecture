package com.ojaexpress.merchant.ui.products.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.local.repositories.ProductRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class ProductListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ProductRepository mRepository;

    public ProductListViewModelFactory(ProductRepository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProductListViewModel(mRepository);
    }
}
