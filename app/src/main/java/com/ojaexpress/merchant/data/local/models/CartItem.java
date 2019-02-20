package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.network.responses.CartItemResponseData;
import com.ojaexpress.merchant.utils.OjaExpressUtil;

import java.util.Date;



@Entity(tableName = "cart_items")
public class CartItem {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "order_id")
    private String orderId;

//    @Embedded(prefix = "order")
//    private Order order;

    @ColumnInfo(name = "product_id")
    private String productId;

//    @Embedded(prefix = "product")
//    private Product product;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "product_price")
    private Double productPrice;

    @ColumnInfo(name = "product_image_url")
    private String productImage;

    @ColumnInfo(name = "store_id")
    private String storeId;

    private Integer count;

    @ColumnInfo(name = "is_checked_out")
    private Boolean isCheckedOut;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    public CartItem(@NonNull int id, String userId, String orderId, String productId, String productName, Double productPrice, String productImage, String storeId, Integer count,
                    Boolean isCheckedOut, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
//        this.order = order;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.storeId = storeId;
        this.count = count;
        this.isCheckedOut = isCheckedOut;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }
//
     public String getProductName() {return productName;}

     public Double getProductPrice() {return productPrice;}

     public String getProductImage() {return productImage;}

    public String getOrderId() {
        return orderId;
    }
//
//    public Order getOrder() {return order;}

    public String getStoreId() {
        return storeId;
    }

    public Integer getCount() {
        return count;
    }

    public Boolean getCheckedOut() {
        return isCheckedOut;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public static CartItem fromCartItemResponseData(CartItemResponseData data) {

        Date createdAt = null;
        Date updatedAt = null;


        if (data.getCreatedAt() != null) {
            createdAt = OjaExpressUtil.getDateTimeFromString(data.getCreatedAt().getDate());
        }

        if (data.getUpdatedAt() != null) {
            updatedAt = OjaExpressUtil.getDateTimeFromString(data.getUpdatedAt().getDate());
        }

        int cart_id = 0;


        return new CartItem(cart_id, data.getUserId(), data.getId(), data.getProductId(), data.getProductName(), data.getProductPrice(), data.getProductImageUrl(),data.getStoreId(), data.getCount(), data.getIsCheckedOut(),createdAt, updatedAt );
    }

}
