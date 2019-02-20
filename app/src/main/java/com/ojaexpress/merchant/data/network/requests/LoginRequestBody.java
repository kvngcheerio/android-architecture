package com.ojaexpress.merchant.data.network.requests;

import com.google.gson.annotations.SerializedName;
import com.ojaexpress.merchant.utils.ApiUtil;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class LoginRequestBody {
    @SerializedName(ApiUtil.EMAIL)
    private String email;

    @SerializedName(ApiUtil.PASSWORD)
    private String password;

    public LoginRequestBody(String email, String password){
        this.email = email;
        this.password = password;
    }
}
