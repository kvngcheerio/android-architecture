package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class ListResponseMeta extends DetailResponseMeta {

    @SerializedName("pagination")
    @Expose
    private PaginationResponseObject pagination;

    public PaginationResponseObject getPagination() {
        return pagination;
    }

    public void setPagination(PaginationResponseObject pagination) {
        this.pagination = pagination;
    }
}
