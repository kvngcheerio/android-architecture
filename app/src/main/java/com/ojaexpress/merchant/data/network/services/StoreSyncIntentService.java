package com.ojaexpress.merchant.data.network.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.ojaexpress.merchant.data.network.sources.StoreNetworkDataSource;
import com.ojaexpress.merchant.utils.InjectorUtils;

public class StoreSyncIntentService extends IntentService {

    public StoreSyncIntentService() {
        super("StoreSyncIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            StoreNetworkDataSource dataSource = InjectorUtils.provideStoreNetworkDataSource(this.getApplicationContext());
            dataSource.fetchStores();
        }
    }
}
