package com.ojaexpress.merchant.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.ojaexpress.merchant.data.local.converters.DateConverter;
import com.ojaexpress.merchant.data.local.daos.AddressDao;
import com.ojaexpress.merchant.data.local.daos.CartItemDao;
import com.ojaexpress.merchant.data.local.daos.OrderDao;
import com.ojaexpress.merchant.data.local.daos.ProductDao;
import com.ojaexpress.merchant.data.local.daos.StoreDao;
import com.ojaexpress.merchant.data.local.models.Address;
import com.ojaexpress.merchant.data.local.models.CartItem;
import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.data.local.models.OrderCartItem;
import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.data.local.models.Store;


//
//@Database(
//        entities = {
//                Address.class, Store.class, Product.class,
//                Order.class, CartItem.class, OrderCartItem.class
//        }, version = 1, exportSchema = false)


@Database(
        entities = {
                Address.class, Store.class, Product.class,
                Order.class, CartItem.class
        }, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class OjaExpressMerchantDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "ojaexpress-merchant-db";

    private static final Object LOCK = new Object();
    private static volatile OjaExpressMerchantDatabase sInstance;

    public static OjaExpressMerchantDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            OjaExpressMerchantDatabase.class,
                            OjaExpressMerchantDatabase.DATABASE_NAME
                    ).build();
                }
            }
        }

        return sInstance;
    }

    public abstract AddressDao getAddressDao();
    public abstract StoreDao getStoreDao();
    public abstract ProductDao getProductDao();
    public abstract OrderDao getOrderDao();
    public abstract CartItemDao getCartItemDao();



}
