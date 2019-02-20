package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.network.responses.OrderResponseData;
import com.ojaexpress.merchant.utils.OjaExpressUtil;

import java.util.Date;
import java.util.List;


@Entity(tableName = "orders",
        indices = {@Index(value = "delivery_address_id")},
        foreignKeys = {
        @ForeignKey(entity = Address.class, parentColumns = "id", childColumns = "delivery_address_id")
//                ,
//        @ForeignKey(entity = CartItem.class, parentColumns = "id", childColumns = "order_id")
})
public class Order {



    @NonNull
    @PrimaryKey
    private String id;

    private String state;

    @ColumnInfo(name = "store_id")
    private String storeId;

    @ColumnInfo(name = "delivery_address_id")
    private String deliveryAddressId;

    @ColumnInfo(name = "user_id")
    private String userId;

    @Embedded(prefix = "buyer_")
    private User user;



    @ColumnInfo(name = "grand_quote_id")
    private String grandQuoteId;

    @ColumnInfo(name = "products_grand_total_price")
    private Double productsGrandTotalPrice;

    @ColumnInfo(name = "taxes_grand_total")
    private Double taxesGrandTotal;

    @ColumnInfo(name = "delivery_charges_grand_total")
    private Double deliveryChargesGrandTotal;

    @ColumnInfo(name = "discount_grand_total")
    private Double discountGrandTotal;

    @ColumnInfo(name = "view_status")
    private Boolean viewStatus;

    @ColumnInfo(name = "grand_net_cost")
    private Double grandNetCost;

    @ColumnInfo(name = "delivery_id")
    private String deliveryId;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    public Order (@NonNull String id, String state, String storeId, String deliveryAddressId, String userId, User user,
                   String grandQuoteId, Double productsGrandTotalPrice, Double taxesGrandTotal,
                  Double deliveryChargesGrandTotal, Double discountGrandTotal, Boolean viewStatus,
                  Double grandNetCost, String deliveryId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.state = state;
        this.storeId = storeId;
        this.deliveryAddressId = deliveryAddressId;
        this.userId = userId;
        this.user = user;
        this.grandQuoteId = grandQuoteId;
        this.productsGrandTotalPrice = productsGrandTotalPrice;
        this.taxesGrandTotal = taxesGrandTotal;
        this.deliveryChargesGrandTotal = deliveryChargesGrandTotal;
        this.discountGrandTotal = discountGrandTotal;
        this.viewStatus = viewStatus;
        this.grandNetCost = grandNetCost;
        this.deliveryId = deliveryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public String getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public String getGrandQuoteId() {
        return grandQuoteId;
    }

    public Double getProductsGrandTotalPrice() {
        return productsGrandTotalPrice;
    }

    public Double getTaxesGrandTotal() {
        return taxesGrandTotal;
    }

    public Double getDeliveryChargesGrandTotal() {
        return deliveryChargesGrandTotal;
    }

    public Double getDiscountGrandTotal() {
        return discountGrandTotal;
    }

    public Boolean getViewStatus() {return viewStatus;}

    public Double getGrandNetCost() {
        return grandNetCost;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }



    public static Order fromOrderResponseData(OrderResponseData data) {
        User user = new User(data.getUserFirstName(), data.getUserLastName(), data.getUserEmail(),
                data.getUserPhoneNumber(), null, null, null, null);

        Date createdAt = null;
        Date updatedAt = null;


        if (data.getCreatedAt() != null) {
            createdAt = OjaExpressUtil.getDateTimeFromString(data.getCreatedAt().getDate());
        }

        if (data.getUpdatedAt() != null) {
            updatedAt = OjaExpressUtil.getDateTimeFromString(data.getUpdatedAt().getDate());
        }

        Boolean viewStatus = false ;


        return new Order(data.getId(), data.getState(), data.getStoreId(), data.getDeliveryAddressId(), data.getUserId(),
                user,  data.getGrandQuoteId(), data.getProductsGrandTotalPrice(), data.getTaxesGrandTotal(),
                data.getDeliveryChargesGrandTotal(), data.getDiscountGrandTotal(), viewStatus,data.getGrandNetCost(),
                data.getDeliveryId(), createdAt, updatedAt);
    }
}
