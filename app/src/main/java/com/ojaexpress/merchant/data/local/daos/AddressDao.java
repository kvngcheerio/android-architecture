package com.ojaexpress.merchant.data.local.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ojaexpress.merchant.data.local.models.Address;

/**
 * Created by funso on 28/05/2018.
 * <p>
 * Peace
 */

@Dao
public interface AddressDao {

    @Query("SELECT * FROM addresses WHERE id = :id")
    LiveData<Address> getAddressById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(Address... addresses);

    @Delete
    void delete(Address address);
}
