package com.ojaexpress.merchant.ui.stores.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.models.Store;
import com.ojaexpress.merchant.data.local.repositories.StoreRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class StoreDetailViewModel extends ViewModel {

    private final StoreRepository storeRepository;
    private final String storeId;
    private final LiveData<Store> store;

    public StoreDetailViewModel(StoreRepository storeRepository, String storeId) {
        this.storeRepository = storeRepository;
        this.storeId = storeId;
        this.store = storeRepository.getStoreById(storeId);
    }

    public LiveData<Store> getStore() {
        return store;
    }

    LiveData<Integer> getFetchStatus() {
        return storeRepository.getStoreDetailFetchStatus();
    }

    LiveData<String> getFetchMsg() {
        return storeRepository.getStoreDetailFetchMsg();
    }
}
