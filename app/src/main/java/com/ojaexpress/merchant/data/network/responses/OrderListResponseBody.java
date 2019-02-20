package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class OrderListResponseBody {
    @SerializedName("data")
    @Expose
    private List<OrderResponseData> data;
    @SerializedName("meta")
    @Expose
    private ListResponseMeta meta;

    public List<OrderResponseData> getData() {
        return data;
    }

    public void setData(List<OrderResponseData> data) {
        this.data = data;
    }

    public ListResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(ListResponseMeta meta) {
        this.meta = meta;
    }
}
