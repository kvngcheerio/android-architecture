package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ProductListResponseBody {
    @SerializedName("data")
    @Expose
    private List<ProductResponseData> data;

    @SerializedName("meta")
    @Expose
    private ListResponseMeta meta;

    public List<ProductResponseData> getData() {
        return data;
    }

    public void setData(List<ProductResponseData> data) {
        this.data = data;
    }

    public ListResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(ListResponseMeta meta) {
        this.meta = meta;
    }
}