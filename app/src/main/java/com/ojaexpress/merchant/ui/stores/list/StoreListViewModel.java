package com.ojaexpress.merchant.ui.stores.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.models.Store;
import com.ojaexpress.merchant.data.local.repositories.StoreRepository;

import java.util.List;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class StoreListViewModel extends ViewModel {

    private final StoreRepository storeRepository;
    private final LiveData<List<Store>> stores;

    public StoreListViewModel(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
        this.stores = storeRepository.getAllStores();
    }

    LiveData<List<Store>> getStores() {
        return stores;
    }

    void fetchLatestStores(){
        storeRepository.startFetchStoreService();
    }

    LiveData<Integer> getFetchStatus() {
        return storeRepository.getStoreListFetchStatus();
    }

    LiveData<String> getFetchMsg() {
        return storeRepository.getStoreListFetchMsg();
    }
}
