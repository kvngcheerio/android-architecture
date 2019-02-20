package com.ojaexpress.merchant.data.network.requests;

import com.google.gson.annotations.SerializedName;
import com.ojaexpress.merchant.utils.ApiUtil;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class SignUpRequestBody {

    @SerializedName(ApiUtil.FIRST_NAME)
    String firstName;

    @SerializedName(ApiUtil.LAST_NAME)
    String lastName;

    @SerializedName(ApiUtil.EMAIL)
    String email;

    @SerializedName(ApiUtil.PASSWORD)
    String password;

    public SignUpRequestBody(String firstName, String lastName, String email, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
