package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class LinksResponseObject {
    @SerializedName("next")
    @Expose
    private String next;

    @SerializedName("prev")
    @Expose
    private String prev;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }
}
