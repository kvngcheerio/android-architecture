package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class OrderDetailResponseBody {

    @SerializedName("data")
    @Expose
    private OrderResponseData data;

    @SerializedName("meta")
    @Expose
    private DetailResponseMeta meta;

    public OrderResponseData getData() {
        return data;
    }

    public DetailResponseMeta getMeta() {
        return meta;
    }
}
