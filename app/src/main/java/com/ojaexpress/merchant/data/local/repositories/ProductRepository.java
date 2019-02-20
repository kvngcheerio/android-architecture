package com.ojaexpress.merchant.data.local.repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.ojaexpress.merchant.AppExecutors;
import com.ojaexpress.merchant.data.local.daos.ProductDao;
import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.data.network.responses.ProductResponseData;
import com.ojaexpress.merchant.data.network.sources.ProductNetworkDataSource;

import java.util.ArrayList;
import java.util.List;


public class ProductRepository {

    private static final String LOG_TAG = ProductRepository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static volatile ProductRepository sInstance;
    private boolean dataInitialized = false;
    private final ProductDao productDao;
    private final ProductNetworkDataSource productNetworkDataSource;
    private final AppExecutors executors;

    private ProductRepository (ProductDao productDao,
                               ProductNetworkDataSource productNetworkDataSource,
                               AppExecutors executors) {
        this.productDao = productDao;
        this.productNetworkDataSource = productNetworkDataSource;
        this.executors = executors;

        LiveData<List<ProductResponseData>> networkData = productNetworkDataSource.getProducts();
        networkData.observeForever(productResponseDataList -> {
            if (productResponseDataList == null) return;
            executors.diskIO().execute(() -> {
                deleteOldData();
                Log.d(LOG_TAG, "Old products deleted");
                insertProductsFromProductResponseDataList(productResponseDataList);
            });
        });
    }

    private void insertProductsFromProductResponseDataList(List<ProductResponseData> responseDataList) {
        List<Product> products = new ArrayList<>();
        for (ProductResponseData data: responseDataList) {
            products.add(Product.fromProductResponseData(data));
//            Log.e(LOG_TAG, "Data" + data);
        }
        productDao.bulkInsert(products.toArray(new Product[products.size()]));
        Log.d(LOG_TAG, "New products inserted");
    }

    public synchronized static ProductRepository getInstance (ProductDao productDao,
                                                            ProductNetworkDataSource productNetworkDataSource,
                                                            AppExecutors executors)
    {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new ProductRepository(productDao, productNetworkDataSource, executors);
                    Log.d(LOG_TAG, "Made new repository");
                }
            }
        }
        return sInstance;
    }

    private synchronized void initializeData() {
        if (dataInitialized) return;
        dataInitialized = true;

        productNetworkDataSource.scheduleRecurringFetchProductSync();
        executors.diskIO().execute(() -> {
            if (isFetchNeeded()) {
                startFetchProductService();
            }
        });

    }

    public LiveData<List<Product>> getAllProducts() {
        initializeData();
        return productDao.getAll();
    }

    public LiveData<Product> getProductById(String id) {
        initializeData();
        return productDao.getProductById(id);
    }

    public LiveData<Integer> getProductListFetchStatus() {
        return productNetworkDataSource.getProductListFetchStatus();
    }

    public LiveData<String> getProductListFetchMsg() {
        return productNetworkDataSource.getProductListFetchMsg();
    }

    public LiveData<Integer> getProductDetailFetchStatus() {
        return productNetworkDataSource.getProductDetailFetchStatus();
    }

    public LiveData<String> getProductDetailFetchMsg() {
        return productNetworkDataSource.getProductDetailFetchMsg();
    }

    private void deleteOldData() {

    }

    private boolean isFetchNeeded() {
        return true;
    }

    public void startFetchProductService() {
        productNetworkDataSource.startProductFetchService();
    }
}
