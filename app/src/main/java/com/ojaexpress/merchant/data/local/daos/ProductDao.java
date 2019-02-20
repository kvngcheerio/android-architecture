package com.ojaexpress.merchant.data.local.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ojaexpress.merchant.data.local.models.Product;

import java.util.List;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAll();

    @Query("SELECT * FROM products WHERE store_id = :storeId")
    LiveData<List<Product>> getAllProductsByStoreId(String storeId);

    @Query("SELECT * FROM products WHERE id = :id")
    LiveData<Product> getProductById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(Product... products);

    @Delete
    void delete(Product product);
}
