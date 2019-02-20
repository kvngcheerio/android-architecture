package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class StoreDetailResponseBody {

    @SerializedName("data")
    @Expose
    private StoreResponseData data;
    @SerializedName("meta")
    @Expose
    private DetailResponseMeta meta;

    public StoreResponseData getData() {
        return data;
    }

    public void setData(StoreResponseData data) {
        this.data = data;
    }

    public DetailResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(DetailResponseMeta meta) {
        this.meta = meta;
    }
}
