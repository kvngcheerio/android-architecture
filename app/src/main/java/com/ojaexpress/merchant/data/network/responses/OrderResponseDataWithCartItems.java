package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class OrderResponseDataWithCartItems extends OrderResponseData {

    @SerializedName("cartItems")
    @Expose
    private CartItemListResponseBody cartItems;

    public CartItemListResponseBody getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItemListResponseBody cartItems) {
        this.cartItems = cartItems;
    }
}
