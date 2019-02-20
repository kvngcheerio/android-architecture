package com.ojaexpress.merchant.data.network.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.ojaexpress.merchant.data.network.sources.OrderNetworkDataSource;
import com.ojaexpress.merchant.utils.InjectorUtils;

public class OrderSyncIntentService extends IntentService {

    private static final String LOG_TAG = OrderSyncIntentService.class.getSimpleName();

    public OrderSyncIntentService() {
        super("OrderSyncIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d(LOG_TAG, "Handling Order Sync Intent...");
            OrderNetworkDataSource dataSource = InjectorUtils.provideOrderNetworkDataSource(this.getApplicationContext());
            dataSource.fetchOrders();


        }
    }
}
