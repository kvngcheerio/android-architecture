package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

public class StoreResponseData {

    @SerializedName("object")
    @Expose
    private String object;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("admin_id")
    @Expose
    private String adminId;

    @SerializedName("admin_name")
    @Expose
    private String adminName;

    @SerializedName("admin_email")
    @Expose
    private String adminEmail;

    @SerializedName("admin_first_name")
    @Expose
    private String adminFirstName;

    @SerializedName("admin_last_name")
    @Expose
    private String adminLastName;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("tax_charge")
    @Expose
    private Double taxCharge;

    @SerializedName("percentage_tax_charge")
    @Expose
    private Double percentageTaxCharge;

    @SerializedName("tax_charge_mode")
    @Expose
    private String taxChargeMode;

    @SerializedName("max_tax_charge")
    @Expose
    private Double maxTaxCharge;

    @SerializedName("delivery_charge")
    @Expose
    private Double deliveryCharge;

    @SerializedName("percentage_delivery_charge")
    @Expose
    private Double percentageDeliveryCharge;

    @SerializedName("delivery_charge_mode")
    @Expose
    private String deliveryChargeMode;

    @SerializedName("max_delivery_charge")
    @Expose
    private Double maxDeliveryCharge;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getTaxCharge() {
        return taxCharge;
    }

    public void setTaxCharge(Double taxCharge) {
        this.taxCharge = taxCharge;
    }

    public Double getPercentageTaxCharge() {
        return percentageTaxCharge;
    }

    public void setPercentageTaxCharge(Double percentageTaxCharge) {
        this.percentageTaxCharge = percentageTaxCharge;
    }

    public String getTaxChargeMode() {
        return taxChargeMode;
    }

    public void setTaxChargeMode(String taxChargeMode) {
        this.taxChargeMode = taxChargeMode;
    }

    public Double getMaxTaxCharge() {
        return maxTaxCharge;
    }

    public void setMaxTaxCharge(Double maxTaxCharge) {
        this.maxTaxCharge = maxTaxCharge;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Double getPercentageDeliveryCharge() {
        return percentageDeliveryCharge;
    }

    public void setPercentageDeliveryCharge(Double percentageDeliveryCharge) {
        this.percentageDeliveryCharge = percentageDeliveryCharge;
    }

    public String getDeliveryChargeMode() {
        return deliveryChargeMode;
    }

    public void setDeliveryChargeMode(String deliveryChargeMode) {
        this.deliveryChargeMode = deliveryChargeMode;
    }

    public Double getMaxDeliveryCharge() {
        return maxDeliveryCharge;
    }

    public void setMaxDeliveryCharge(Double maxDeliveryCharge) {
        this.maxDeliveryCharge = maxDeliveryCharge;
    }
}
