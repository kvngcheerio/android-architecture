package com.ojaexpress.merchant.ui.stores.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.local.repositories.StoreRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class StoreListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final StoreRepository mRepository;

    public StoreListViewModelFactory(StoreRepository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new StoreListViewModel(mRepository);
    }
}
