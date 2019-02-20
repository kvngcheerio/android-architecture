package com.ojaexpress.merchant.data.local.repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.ojaexpress.merchant.AppExecutors;
import com.ojaexpress.merchant.data.local.daos.StoreDao;
import com.ojaexpress.merchant.data.local.models.Store;
import com.ojaexpress.merchant.data.network.responses.StoreResponseData;
import com.ojaexpress.merchant.data.network.sources.StoreNetworkDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by funso on 24/05/2018.
 * <p>
 * Peace
 */

public class StoreRepository {
    private static final String LOG_TAG = StoreRepository.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static volatile StoreRepository sInstance;
    private boolean dataInitialized = false;
    private final StoreDao storeDao;
    private final StoreNetworkDataSource storeNetworkDataSource;
    private final AppExecutors executors;

    private StoreRepository (StoreDao storeDao,
                             StoreNetworkDataSource storeNetworkDataSource,
                             AppExecutors executors) {
        this.storeDao = storeDao;
        this.storeNetworkDataSource = storeNetworkDataSource;
        this.executors = executors;

        LiveData<List<StoreResponseData>> networkData = storeNetworkDataSource.getStores();
        networkData.observeForever(storeResponseDataList -> {
            if (storeResponseDataList == null) return;
            executors.diskIO().execute(() -> {
                deleteOldData();
                Log.d(LOG_TAG, "Old stores deleted");
                insertStoresFromStoreResponseDataList(storeResponseDataList);
            });
        });
    }

    private void insertStoresFromStoreResponseDataList(List<StoreResponseData> responseDataList) {
        List<Store> stores = new ArrayList<>();
        for (StoreResponseData data: responseDataList) {
            stores.add(Store.fromStoreResponseData(data));
        }
        storeDao.bulkInsert(stores.toArray(new Store[stores.size()]));
        Log.d(LOG_TAG, "New values inserted");
    }

    public synchronized static StoreRepository getInstance (StoreDao storeDao,
                                               StoreNetworkDataSource storeNetworkDataSource,
                                               AppExecutors executors)
    {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new StoreRepository(storeDao, storeNetworkDataSource, executors);
                    Log.d(LOG_TAG, "Made new repository");
                }
            }
        }
        return sInstance;
    }

    private synchronized void initializeData() {
        if (dataInitialized) return;
        dataInitialized = true;

        storeNetworkDataSource.scheduleRecurringFetchStoreSync();
        executors.diskIO().execute(() -> {
            if (isFetchNeeded()) {
                startFetchStoreService();
            }
        });

    }

    public LiveData<List<Store>> getAllStores() {
        initializeData();
        return storeDao.getAll();
    }

    public LiveData<Integer> getStoreListFetchStatus() {
        return storeNetworkDataSource.getStoreListFetchStatus();
    }

    public LiveData<String> getStoreListFetchMsg() {
        return storeNetworkDataSource.getStoreListFetchMsg();
    }

    public LiveData<Integer> getStoreDetailFetchStatus() {
        return storeNetworkDataSource.getStoreDetailFetchStatus();
    }

    public LiveData<String> getStoreDetailFetchMsg() {
        return storeNetworkDataSource.getStoreDetailFetchMsg();
    }

    public LiveData<Store> getStoreById(String id) {
        initializeData();
        return storeDao.getStoreById(id);
    }

    private void deleteOldData() {

    }

    private boolean isFetchNeeded() {
        return true;
    }

    public void startFetchStoreService() {
        storeNetworkDataSource.startStoreFetchService();
    }
}
