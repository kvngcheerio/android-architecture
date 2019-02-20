package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class ProductDetailResponseBody {

    @SerializedName("data")
    @Expose
    private ProductResponseData data;

    @SerializedName("meta")
    @Expose
    private DetailResponseMeta meta;

    public ProductResponseData getData() {
        return data;
    }

    public void setData(ProductResponseData data) {
        this.data = data;
    }

    public DetailResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(DetailResponseMeta meta) {
        this.meta = meta;
    }
}
