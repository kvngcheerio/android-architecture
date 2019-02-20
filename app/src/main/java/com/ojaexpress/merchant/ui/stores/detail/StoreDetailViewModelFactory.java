package com.ojaexpress.merchant.ui.stores.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.local.repositories.StoreRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class StoreDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final StoreRepository mRepository;
    private final String storeId;

    public StoreDetailViewModelFactory(StoreRepository repository, String storeId) {
        this.mRepository = repository;
        this.storeId = storeId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new StoreDetailViewModel(mRepository, storeId);
    }
}
