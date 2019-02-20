package com.ojaexpress.merchant.ui.products.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.data.local.repositories.ProductRepository;

import java.util.List;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class ProductListViewModel extends ViewModel {

    private final ProductRepository productRepository;
    private final LiveData<List<Product>> products;

    public ProductListViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.products = productRepository.getAllProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    void fetchLatestProducts() {
        productRepository.startFetchProductService();
    }

    LiveData<Integer> getFetchStatus () {
        return productRepository.getProductListFetchStatus();
    }

    LiveData<String> getFetchMsg () {
        return productRepository.getProductListFetchMsg();
    }
}
