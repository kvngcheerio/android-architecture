package com.ojaexpress.merchant.data.local.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ojaexpress.merchant.data.local.models.User;

import java.util.List;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

@Dao
public interface UserDao {

//    @Query("SELECT * FROM users")
//    LiveData<List<User>> getAll();
//
//    @Query("SELECT * FROM users WHERE id = :id")
//    LiveData<User> getUserById(String id);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void bulkInsert(User... users);
//
//    @Delete
//    void delete(User user);
}
