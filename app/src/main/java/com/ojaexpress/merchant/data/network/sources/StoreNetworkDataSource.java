package com.ojaexpress.merchant.data.network.sources;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ojaexpress.merchant.data.network.clients.StoreNetworkDataClient;
import com.ojaexpress.merchant.data.network.responses.StoreDetailResponseBody;
import com.ojaexpress.merchant.data.network.responses.StoreListResponseBody;
import com.ojaexpress.merchant.data.network.responses.StoreResponseData;
import com.ojaexpress.merchant.data.network.services.StoreSyncIntentService;
import com.ojaexpress.merchant.utils.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by funso on 24/05/2018.
 * <p>
 * Peace
 */

public class StoreNetworkDataSource {

    private static final String LOG_TAG = StoreNetworkDataSource.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static volatile StoreNetworkDataSource sInstance;
    private StoreNetworkDataClient storeNetworkDataClient;
    private Context mContext;

    private final MutableLiveData<List<StoreResponseData>> stores;
    private final MutableLiveData<String> storeListFetchMsg;
    private final MutableLiveData<Integer> storeListFetchStatus;
    private final MutableLiveData<String> storeDetailFetchMsg;
    private final MutableLiveData<Integer> storeDetailFetchStatus;

    private StoreNetworkDataSource(Context context, StoreNetworkDataClient storeNetworkDataClient) {
        this.storeNetworkDataClient = storeNetworkDataClient;
        this.mContext = context;

        this.stores = new MutableLiveData<>();

        this.storeListFetchMsg = new MutableLiveData<>();
        this.storeListFetchStatus = new MutableLiveData<>();
        this.storeDetailFetchMsg = new MutableLiveData<>();
        this.storeDetailFetchStatus = new MutableLiveData<>();
    }

    public static StoreNetworkDataSource getInstance (Context context, StoreNetworkDataClient storeNetworkDataClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new StoreNetworkDataSource(context, storeNetworkDataClient);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<StoreResponseData>> getStores() {
        return stores;
    }

    public LiveData<String> getStoreListFetchMsg() {
        return storeListFetchMsg;
    }

    public LiveData<Integer> getStoreListFetchStatus() {
        return storeListFetchStatus;
    }

    public LiveData<String> getStoreDetailFetchMsg() {
        return storeDetailFetchMsg;
    }

    public LiveData<Integer> getStoreDetailFetchStatus() {
        return storeDetailFetchStatus;
    }

    public void scheduleRecurringFetchStoreSync() {
        // TODO: create a Job here
    }

    public void startStoreFetchService() {
        Intent intent = new Intent(mContext, StoreSyncIntentService.class);
        mContext.startService(intent);
        Log.e(LOG_TAG, "Store Fetch Service created!");
    }

    public void fetchStores() {
        Log.e(LOG_TAG, "Fetching Stores...");
        storeListFetchStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        Call<StoreListResponseBody> call = storeNetworkDataClient.getAllAdminStores();
        call.enqueue(new Callback<StoreListResponseBody>() {
            @Override
            public void onResponse(Call<StoreListResponseBody> call, Response<StoreListResponseBody> response) {
                int statusCode = response.code();
                Log.e(LOG_TAG, "Store list url => " + call.request().url().toString() + " response returned with code ==> " + statusCode  );

                if (statusCode == 200) {
                    storeListFetchMsg.postValue("Store List fetched successfully!");
                    storeListFetchStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    StoreListResponseBody body = response.body();
                    if (body != null) {
                        stores.postValue(body.getData());
                    }

                } else {
                    Log.e(LOG_TAG, "Store list fetch returns unexpected status code => " + statusCode);
                    storeListFetchMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.GET_ALL_ADMIN_STORES));
                    storeListFetchStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<StoreListResponseBody> call, Throwable t) {
                Log.e(LOG_TAG, "Error fetching Store list!", t);
                storeListFetchMsg.postValue("Could not refresh stores list!");
                storeListFetchStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }

    public void fetchStoreById(String storeId) {
        storeDetailFetchStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        Call<StoreDetailResponseBody> call = storeNetworkDataClient.getStoreById(storeId);
        call.enqueue(new Callback<StoreDetailResponseBody>() {
            @Override
            public void onResponse(Call<StoreDetailResponseBody> call, Response<StoreDetailResponseBody> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    storeDetailFetchMsg.postValue("Store details fetched successfully!");
                    storeDetailFetchStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    StoreDetailResponseBody body = response.body();
                    if (body != null) {
                        List<StoreResponseData> data = new ArrayList<>();
                        data.add(body.getData());
                        stores.postValue(data);
                    }
                } else {
                    storeDetailFetchMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.GET_STORE_BY_ID));
                    storeDetailFetchStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<StoreDetailResponseBody> call, Throwable t) {
                storeDetailFetchMsg.postValue("Could not refresh selected store's details");
                storeDetailFetchStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }
}
