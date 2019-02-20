package com.ojaexpress.merchant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by funso on 24/05/2018.
 * <p>
 * Peace
 */

public class OjaExpressUtil {

    public static String getFullName(String firstName, String lastName){
        String name = "";
        if ((firstName != null && !firstName.equalsIgnoreCase("null"))
                || (lastName != null && !lastName.equalsIgnoreCase("null"))) {

            if (firstName == null) {
                name = lastName;
            } else {
                name = firstName;
                if (lastName != null) {
                    name = String.format("%s %s", firstName, lastName);
                }
            }
        }
        return name;
    }

    public static Date getDateTimeFromString(String dateTimeString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        try {
            return simpleDateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
