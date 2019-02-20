package com.ojaexpress.merchant.ui.products.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.data.local.repositories.ProductRepository;

/**
 * Created by funso on 29/05/2018.
 * <p>
 * Peace
 */

public class ProductDetailViewModel extends ViewModel {

    private final ProductRepository productRepository;
    private final String productId;
    private final LiveData<Product> product;

    public ProductDetailViewModel(ProductRepository productRepository, String productId) {
        this.productRepository = productRepository;
        this.productId = productId;
        this.product = productRepository.getProductById(productId);
    }

    public LiveData<Product> getProduct() {
        return product;
    }

    LiveData<Integer> getFetchStatus() {
        return productRepository.getProductDetailFetchStatus();
    }

    LiveData<String> getFetchMsg() {
        return productRepository.getProductListFetchMsg();
    }
}
