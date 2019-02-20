package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;



public class StoreWithProducts {

    @Embedded
    private Store store;

    @Relation(entity = Product.class, parentColumn = "id", entityColumn = "store_id")
    private List<Product> products;



    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
