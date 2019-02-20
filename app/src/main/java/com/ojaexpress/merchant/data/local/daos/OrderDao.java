package com.ojaexpress.merchant.data.local.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.ojaexpress.merchant.data.local.models.CartItem;
import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.data.local.models.OrderCartItem;
import com.ojaexpress.merchant.data.local.models.OrderWithCartItems;

import java.util.List;



@Dao
public interface OrderDao {

    @Query("SELECT * FROM orders")
    LiveData<List<Order>> getAll();

    @Query("SELECT * FROM orders WHERE store_id = :storeId")
    LiveData<List<Order>> getAllOrdersByStoreId(String storeId);

    @Query("SELECT * FROM orders WHERE id = :id")
    LiveData<Order> getOrderById(String id);

    @Transaction
    @Query("SELECT * FROM orders WHERE id = :id")
    LiveData<OrderWithCartItems> getOrderWithCartsById(String id);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(Order... orders);
}
