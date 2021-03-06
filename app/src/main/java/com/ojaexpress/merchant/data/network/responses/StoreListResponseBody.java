package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ojaexpress.merchant.data.local.models.Store;

import java.util.List;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class StoreListResponseBody {
    @SerializedName("data")
    @Expose
    private List<StoreResponseData> data;
    @SerializedName("meta")
    @Expose
    private ListResponseMeta meta;

    public List<StoreResponseData> getData() {
        return data;
    }

    public void setData(List<StoreResponseData> data) {
        this.data = data;
    }

    public ListResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(ListResponseMeta meta) {
        this.meta = meta;
    }
}
