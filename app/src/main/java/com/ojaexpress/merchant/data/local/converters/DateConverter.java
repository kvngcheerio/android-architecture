package com.ojaexpress.merchant.data.local.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by funso on 24/05/2018.
 * <p>
 * Peace
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
