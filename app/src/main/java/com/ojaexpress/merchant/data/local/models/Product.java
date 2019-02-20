package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.network.responses.ProductResponseData;
import com.ojaexpress.merchant.utils.OjaExpressUtil;

import java.util.Date;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

@Entity(tableName = "products")
public class Product {

    @NonNull
    @PrimaryKey
    private String id;

    private String name;

    private Double price;

    @ColumnInfo(name = "unit_of_measure")
    private String unitOfMeasure;

    @ColumnInfo(name = "unit_of_measure_plural")
    private String unitOfMeasurePlural;

    @ColumnInfo(name = "tax_charge")
    private Double taxCharge;

    @ColumnInfo(name = "delivery_charge")
    private Double deliveryCharge;

    @ColumnInfo(name = "number_in_stock")
    private Integer numberInStock;

    @ColumnInfo(name = "min_number_for_restock")
    private Integer minNumberForRestock;

    @ColumnInfo(name = "number_sold")
    private Integer numberSold;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "store_id")
    private String storeId;

    private String description;

    @ColumnInfo(name = "is_active")
    private Boolean isActive;

    private Integer views;

    private Integer clicks;

    private Integer searches;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @ColumnInfo(name = "deleted_at")
    private Date deletedAt;

    public Product(@NonNull String id, String name, Double price, String unitOfMeasure,
                   String unitOfMeasurePlural, Double taxCharge, Double deliveryCharge, Integer numberInStock,
                   Integer minNumberForRestock, Integer numberSold, String imageUrl, String storeId,
                   String description, Boolean isActive, Integer views, Integer clicks, Integer searches,
                   Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.unitOfMeasurePlural = unitOfMeasurePlural;
        this.taxCharge = taxCharge;
        this.deliveryCharge = deliveryCharge;
        this.numberInStock = numberInStock;
        this.minNumberForRestock = minNumberForRestock;
        this.numberSold = numberSold;
        this.imageUrl = imageUrl;
        this.storeId = storeId;
        this.description = description;
        this.isActive = isActive;
        this.views = views;
        this.clicks = clicks;
        this.searches = searches;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getUnitOfMeasurePlural() {
        return unitOfMeasurePlural;
    }

    public Double getTaxCharge() {
        return taxCharge;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public Integer getNumberInStock() {
        return numberInStock;
    }

    public Integer getMinNumberForRestock() {
        return minNumberForRestock;
    }

    public Integer getNumberSold() {
        return numberSold;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Integer getViews() {
        return views;
    }

    public Integer getClicks() {
        return clicks;
    }

    public Integer getSearches() {
        return searches;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public static Product fromProductResponseData(ProductResponseData data) {
        Date createdAt = null;
        Date updatedAt = null;
        Date deletedAt = null;
        if (data.getCreatedAt() != null) {
            createdAt = OjaExpressUtil.getDateTimeFromString(data.getCreatedAt().getDate());
        }
        if (data.getUpdatedAt() != null) {
            updatedAt = OjaExpressUtil.getDateTimeFromString(data.getUpdatedAt().getDate());
        }
        if (data.getDeletedAt() != null) {
            deletedAt = OjaExpressUtil.getDateTimeFromString(data.getDeletedAt().getDate());
        }
        Boolean isActive = data.getIsActive() == 1;

        return new Product(data.getId(), data.getName(), data.getPrice(), data.getUnitOfMeasure(),
                data.getUnitOfMeasurePlural(), data.getTaxCharge(), data.getDeliveryCharge(),
                data.getNumberInStock(), data.getMinNumberForRestock(), data.getNumberSold(),
                data.getImageUrl(), data.getStoreId(), data.getDescription(), isActive,
                data.getViews(), data.getClicks(), data.getSearches(), createdAt, updatedAt, deletedAt);
    }
}
