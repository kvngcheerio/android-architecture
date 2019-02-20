package com.ojaexpress.merchant.data.local.daos;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.ojaexpress.merchant.data.local.models.CartItem;

import java.util.List;

/**
 * Created by funso on 28/05/2018.
 * <p>
 * Peace
 */

@Dao
public interface CartItemDao {

    @Query("SELECT * FROM cart_items")
    LiveData<List<CartItem>> getAllCartItems();

    @Query("SELECT * FROM cart_items WHERE id = :id")
    LiveData<CartItem> getCartItemById(String id);

    @Query("SELECT * FROM cart_items WHERE order_id = :orderId")
    LiveData<List<CartItem>> getAllCartitemsbyorderId(String orderId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(CartItem... cartItems);

    @Delete
    void delete(CartItem cartItem);
}
