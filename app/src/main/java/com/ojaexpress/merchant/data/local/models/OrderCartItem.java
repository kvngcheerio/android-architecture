package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import java.util.List;

public class OrderCartItem {

    @Embedded
    private Order order;

    @Relation(entity = CartItem.class, parentColumn = "id", entityColumn = "order_id")
    private List<CartItem> cartitems;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<CartItem> getCartItem() {
        return cartitems;
    }

    public void setCartitems(List<CartItem> cartitems) {
        this.cartitems = cartitems;
    }

}