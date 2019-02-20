package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;



public class OrderWithCartItems {

    @Embedded
    private Order order;

    @Relation(entity = CartItem.class, entityColumn = "order_id", parentColumn = "id")
    private List<CartItem> cartItems;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
