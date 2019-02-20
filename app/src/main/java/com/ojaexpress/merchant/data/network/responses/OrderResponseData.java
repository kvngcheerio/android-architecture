package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderResponseData {

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("delivery_address_id")
    @Expose
    private String deliveryAddressId;
    @SerializedName("delivery_street_address")
    @Expose
    private String deliveryStreetAddress;
    @SerializedName("delivery_extended_address")
    @Expose
    private String deliveryExtendedAddress;
    @SerializedName("delivery_address_locality")
    @Expose
    private String deliveryAddressLocality;
    @SerializedName("delivery_address_zip_code")
    @Expose
    private String deliveryAddressZipCode;
    @SerializedName("delivery_address_phone_number")
    @Expose
    private String deliveryAddressPhoneNumber;
    @SerializedName("delivery_address_country_id")
    @Expose
    private Integer deliveryAddressCountryId;
    @SerializedName("delivery_address_country_name")
    @Expose
    private String deliveryAddressCountryName;
    @SerializedName("delivery_address_latitude")
    @Expose
    private Double deliveryAddressLatitude;
    @SerializedName("delivery_address_longitude")
    @Expose
    private Double deliveryAddressLongitude;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_first_name")
    @Expose
    private String userFirstName;
    @SerializedName("user_last_name")
    @Expose
    private String userLastName;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_phone_number")
    @Expose
    private String userPhoneNumber;
    @SerializedName("user_avatar")
    @Expose
    private String userAvatar;
    @SerializedName("grand_quote_id")
    @Expose
    private String grandQuoteId;
    @SerializedName("products_grand_total_price")
    @Expose
    private Double productsGrandTotalPrice;
    @SerializedName("taxes_grand_total")
    @Expose
    private Double taxesGrandTotal;
    @SerializedName("delivery_charges_grand_total")
    @Expose
    private Double deliveryChargesGrandTotal;
    @SerializedName("discount_grand_total")
    @Expose
    private Double discountGrandTotal;
    @SerializedName("grand_net_cost")
    @Expose
    private Double grandNetCost;
    @SerializedName("delivery_id")
    @Expose
    private String deliveryId;
    @SerializedName("created_at")
    @Expose
    private DatetimeResponseObject createdAt;
    @SerializedName("updated_at")
    @Expose
    private DatetimeResponseObject updatedAt;

    @SerializedName("data")
    @Expose
    private CartItemListResponseBody cartItemList;


    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryStreetAddress() {
        return deliveryStreetAddress;
    }

    public void setDeliveryStreetAddress(String deliveryStreetAddress) {
        this.deliveryStreetAddress = deliveryStreetAddress;
    }

    public String getDeliveryExtendedAddress() {
        return deliveryExtendedAddress;
    }

    public void setDeliveryExtendedAddress(String deliveryExtendedAddress) {
        this.deliveryExtendedAddress = deliveryExtendedAddress;
    }

    public String getDeliveryAddressLocality() {
        return deliveryAddressLocality;
    }

    public void setDeliveryAddressLocality(String deliveryAddressLocality) {
        this.deliveryAddressLocality = deliveryAddressLocality;
    }

    public String getDeliveryAddressZipCode() {
        return deliveryAddressZipCode;
    }

    public void setDeliveryAddressZipCode(String deliveryAddressZipCode) {
        this.deliveryAddressZipCode = deliveryAddressZipCode;
    }

    public String getDeliveryAddressPhoneNumber() {
        return deliveryAddressPhoneNumber;
    }

    public void setDeliveryAddressPhoneNumber(String deliveryAddressPhoneNumber) {
        this.deliveryAddressPhoneNumber = deliveryAddressPhoneNumber;
    }

    public Integer getDeliveryAddressCountryId() {
        return deliveryAddressCountryId;
    }

    public void setDeliveryAddressCountryId(Integer deliveryAddressCountryId) {
        this.deliveryAddressCountryId = deliveryAddressCountryId;
    }

    public String getDeliveryAddressCountryName() {
        return deliveryAddressCountryName;
    }

    public void setDeliveryAddressCountryName(String deliveryAddressCountryName) {
        this.deliveryAddressCountryName = deliveryAddressCountryName;
    }

    public Double getDeliveryAddressLatitude() {
        return deliveryAddressLatitude;
    }

    public void setDeliveryAddressLatitude(Double deliveryAddressLatitude) {
        this.deliveryAddressLatitude = deliveryAddressLatitude;
    }

    public Double getDeliveryAddressLongitude() {
        return deliveryAddressLongitude;
    }

    public void setDeliveryAddressLongitude(Double deliveryAddressLongitude) {
        this.deliveryAddressLongitude = deliveryAddressLongitude;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getGrandQuoteId() {
        return grandQuoteId;
    }

    public void setGrandQuoteId(String grandQuoteId) {
        this.grandQuoteId = grandQuoteId;
    }

    public Double getProductsGrandTotalPrice() {
        return productsGrandTotalPrice;
    }

    public void setProductsGrandTotalPrice(Double productsGrandTotalPrice) {
        this.productsGrandTotalPrice = productsGrandTotalPrice;
    }

    public Double getTaxesGrandTotal() {
        return taxesGrandTotal;
    }

    public void setTaxesGrandTotal(Double taxesGrandTotal) {
        this.taxesGrandTotal = taxesGrandTotal;
    }

    public Double getDeliveryChargesGrandTotal() {
        return deliveryChargesGrandTotal;
    }

    public void setDeliveryChargesGrandTotal(Double deliveryChargesGrandTotal) {
        this.deliveryChargesGrandTotal = deliveryChargesGrandTotal;
    }

    public Double getDiscountGrandTotal() {
        return discountGrandTotal;
    }

    public void setDiscountGrandTotal(Double discountGrandTotal) {
        this.discountGrandTotal = discountGrandTotal;
    }

    public Double getGrandNetCost() {
        return grandNetCost;
    }

    public void setGrandNetCost(Double grandNetCost) {
        this.grandNetCost = grandNetCost;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
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

    public CartItemListResponseBody getCartItemListResponseBody() {
        return cartItemList;
    }

    public void setCartItemListResponseBody (CartItemListResponseBody cartItemList) {
        this.cartItemList = cartItemList;
    }
}
