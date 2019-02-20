package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class ProductResponseData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("unit_of_measure")
    @Expose
    private String unitOfMeasure;
    @SerializedName("unit_of_measure_plural")
    @Expose
    private String unitOfMeasurePlural;
    @SerializedName("tax_charge")
    @Expose
    private Double taxCharge;
    @SerializedName("delivery_charge")
    @Expose
    private Double deliveryCharge;
    @SerializedName("number_in_stock")
    @Expose
    private Integer numberInStock;
    @SerializedName("min_number_for_restock")
    @Expose
    private Integer minNumberForRestock;
    @SerializedName("number_sold")
    @Expose
    private Integer numberSold;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("is_in_cart")
    @Expose
    private Boolean isInCart;
    @SerializedName("cart_count")
    @Expose
    private Integer cartCount;
    @SerializedName("is_favourite")
    @Expose
    private Boolean isFavourite;
    @SerializedName("cart_item_id")
    @Expose
    private Object cartItemId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("clicks")
    @Expose
    private Integer clicks;
    @SerializedName("searches")
    @Expose
    private Integer searches;
    @SerializedName("created_at")
    @Expose
    private DatetimeResponseObject createdAt;
    @SerializedName("updated_at")
    @Expose
    private DatetimeResponseObject updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private DatetimeResponseObject deletedAt;
//    @SerializedName("categories")
//    @Expose
//    private List<ProductResponseData> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getUnitOfMeasurePlural() {
        return unitOfMeasurePlural;
    }

    public void setUnitOfMeasurePlural(String unitOfMeasurePlural) {
        this.unitOfMeasurePlural = unitOfMeasurePlural;
    }

    public Double getTaxCharge() {
        return taxCharge;
    }

    public void setTaxCharge(Double taxCharge) {
        this.taxCharge = taxCharge;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Integer getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(Integer numberInStock) {
        this.numberInStock = numberInStock;
    }

    public Integer getMinNumberForRestock() {
        return minNumberForRestock;
    }

    public void setMinNumberForRestock(Integer minNumberForRestock) {
        this.minNumberForRestock = minNumberForRestock;
    }

    public Integer getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(Integer numberSold) {
        this.numberSold = numberSold;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Boolean getIsInCart() {
        return isInCart;
    }

    public void setIsInCart(Boolean isInCart) {
        this.isInCart = isInCart;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Object getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Object cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getSearches() {
        return searches;
    }

    public void setSearches(Integer searches) {
        this.searches = searches;
    }

    public DatetimeResponseObject getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DatetimeResponseObject createdAt) {
        this.createdAt = createdAt;
    }

    public DatetimeResponseObject getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DatetimeResponseObject updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DatetimeResponseObject getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(DatetimeResponseObject deletedAt) {
        this.deletedAt = deletedAt;
    }
}
