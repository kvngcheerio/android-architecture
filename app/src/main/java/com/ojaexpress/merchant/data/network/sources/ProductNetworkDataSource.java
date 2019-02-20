package com.ojaexpress.merchant.data.network.sources;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ojaexpress.merchant.data.network.clients.ProductNetworkDataClient;
import com.ojaexpress.merchant.data.network.responses.ProductDetailResponseBody;
import com.ojaexpress.merchant.data.network.responses.ProductListResponseBody;
import com.ojaexpress.merchant.data.network.responses.ProductResponseData;
import com.ojaexpress.merchant.data.network.services.ProductSyncIntentService;
import com.ojaexpress.merchant.utils.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by funso on 28/05/2018.
 * <p>
 * Peace
 */

public class ProductNetworkDataSource {

    private static final String LOG_TAG = ProductNetworkDataSource.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static volatile ProductNetworkDataSource sInstance;
    private ProductNetworkDataClient productNetworkDataClient;
    private Context mContext;

    private final MutableLiveData<List<ProductResponseData>> products;
    private final MutableLiveData<String> productListFetchMsg;
    private final MutableLiveData<Integer> productListFetchStatus;
    private final MutableLiveData<String> productDetailFetchMsg;
    private final MutableLiveData<Integer> productDetailFetchStatus;

    private ProductNetworkDataSource(Context context, ProductNetworkDataClient productNetworkDataClient) {
        this.mContext = context;
        this.productNetworkDataClient = productNetworkDataClient;
        this.products = new MutableLiveData<>();
        this.productListFetchMsg = new MutableLiveData<>();
        this.productListFetchStatus = new MutableLiveData<>();
        this.productDetailFetchMsg = new MutableLiveData<>();
        this.productDetailFetchStatus = new MutableLiveData<>();
    }

    public static ProductNetworkDataSource getInstance (Context context, ProductNetworkDataClient productNetworkDataClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new ProductNetworkDataSource(context, productNetworkDataClient);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<ProductResponseData>> getProducts() {
        return products;
    }

    public LiveData<String> getProductListFetchMsg() {
        return productListFetchMsg;
    }

    public LiveData<Integer> getProductListFetchStatus() {
        return productListFetchStatus;
    }

    public LiveData<String> getProductDetailFetchMsg() {
        return productDetailFetchMsg;
    }

    public LiveData<Integer> getProductDetailFetchStatus() {
        return productDetailFetchStatus;
    }

    public void scheduleRecurringFetchProductSync() {

    }

    public void startProductFetchService() {
        Intent intent = new Intent(mContext, ProductSyncIntentService.class);
        mContext.startService(intent);
        Log.e(LOG_TAG, "Product Fetch Service created!");
    }

    public void fetchProducts() {
        Log.e(LOG_TAG, "Fetching Products...");
        productListFetchStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        Call<ProductListResponseBody> call = productNetworkDataClient.getAllAdminProducts();
        call.enqueue(new Callback<ProductListResponseBody>() {
            @Override
            public void onResponse(Call<ProductListResponseBody> call, Response<ProductListResponseBody> response) {
                int statusCode = response.code();
                Log.e(LOG_TAG, "Product list url => " + call.request().url().toString() + " response returned with code ==> " + statusCode  );

                if (statusCode == 200) {
                    productListFetchMsg.postValue("Product List fetched successfully!");
                    productListFetchStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    ProductListResponseBody body = response.body();

                    if (body != null) {
                        products.postValue(body.getData());
                    }

                } else {
                    Log.e(LOG_TAG, "Products fetch returned with unexpected status code => " + statusCode);
                    productListFetchMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.GET_ALL_ADMIN_PRODUCTS));
                    productListFetchStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<ProductListResponseBody> call, Throwable t) {
                Log.e(LOG_TAG, "Error fetching products!", t);
                productListFetchMsg.postValue("Could not refresh product list");
                productListFetchStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }

    public void fetchProduct(String productId) {
        productListFetchStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        Call<ProductDetailResponseBody> call = productNetworkDataClient.getProductById(productId);
        call.enqueue(new Callback<ProductDetailResponseBody>() {
            @Override
            public void onResponse(Call<ProductDetailResponseBody> call, Response<ProductDetailResponseBody> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    productDetailFetchMsg.postValue("Product List fetched successfully!");
                    productDetailFetchStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    ProductDetailResponseBody body = response.body();
                    if (body != null) {
                        List<ProductResponseData> data = new ArrayList<>();
                        data.add(body.getData());
                        products.postValue(data);
                    }

                } else {
                    productDetailFetchMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.GET_PRODUCT_BY_ID));
                    productDetailFetchStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponseBody> call, Throwable t) {
                productDetailFetchMsg.postValue("Could not refresh product list");
                productDetailFetchStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }
}
