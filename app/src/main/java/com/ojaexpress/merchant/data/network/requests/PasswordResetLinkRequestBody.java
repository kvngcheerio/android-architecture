package com.ojaexpress.merchant.data.network.requests;

import com.google.gson.annotations.SerializedName;
import com.ojaexpress.merchant.utils.ApiUtil;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class PasswordResetLinkRequestBody {

    @SerializedName(ApiUtil.EMAIL)
    String email;

    public PasswordResetLinkRequestBody(String email)
    {
        this.email = email;
    }
}
