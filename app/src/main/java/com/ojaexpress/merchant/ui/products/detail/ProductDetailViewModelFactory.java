package com.ojaexpress.merchant.ui.products.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.local.repositories.ProductRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class ProductDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ProductRepository mRepository;
    private final String productId;

    public ProductDetailViewModelFactory(ProductRepository repository, String productId) {
        this.mRepository = repository;
        this.productId = productId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProductDetailViewModel(mRepository, productId);
    }
}
