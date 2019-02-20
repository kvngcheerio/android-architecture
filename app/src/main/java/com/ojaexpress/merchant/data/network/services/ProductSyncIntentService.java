package com.ojaexpress.merchant.data.network.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.ojaexpress.merchant.data.network.sources.ProductNetworkDataSource;
import com.ojaexpress.merchant.utils.InjectorUtils;

public class ProductSyncIntentService extends IntentService {

    public ProductSyncIntentService() {
        super("ProductSyncIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ProductNetworkDataSource dataSource = InjectorUtils.provideProductNetworkDataSource(this.getApplicationContext());
            dataSource.fetchProducts();
        }
    }

}
