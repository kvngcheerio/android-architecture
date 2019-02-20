package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 *
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class UserResponseBody {

    @SerializedName("data")
    @Expose
    private UserResponseData data;

    @SerializedName("meta")
    @Expose
    private DetailResponseMeta meta;

    public UserResponseData getData() {
        return data;
    }

    public void setData(UserResponseData data) {
        this.data = data;
    }

    public DetailResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(DetailResponseMeta meta) {
        this.meta = meta;
    }
}
