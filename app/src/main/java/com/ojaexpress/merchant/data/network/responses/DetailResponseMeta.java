package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

import java.util.List;

public class DetailResponseMeta {

    @SerializedName("include")
    @Expose
    private List<String> include = null;

    @SerializedName("custom")
    @Expose
    private List<Object> custom = null;

    public List<String> getInclude() {
        return include;
    }

    public void setInclude(List<String> include) {
        this.include = include;
    }

    public List<Object> getCustom() {
        return custom;
    }

    public void setCustom(List<Object> custom) {
        this.custom = custom;
    }
}
