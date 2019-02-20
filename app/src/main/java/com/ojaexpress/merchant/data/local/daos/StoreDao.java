package com.ojaexpress.merchant.data.local.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.ojaexpress.merchant.data.local.models.Store;
import com.ojaexpress.merchant.data.local.models.StoreWithProducts;

import java.util.List;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

@Dao
public interface StoreDao {

    @Query("SELECT * FROM stores")
    LiveData<List<Store>> getAll();

    @Query("SELECT * FROM stores WHERE id = :id")
    LiveData<Store> getStoreById(String id);

    @Transaction
    @Query("SELECT * FROM stores WHERE id = :id")
    LiveData<StoreWithProducts> getStoreWithProductsById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(Store... stores);

    @Delete
    void delete(Store store);
}
