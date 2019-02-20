package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.network.responses.StoreResponseData;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

@Entity(tableName = "stores")
public class Store {

    @NonNull
    @PrimaryKey
    private String id;

    private String name;

    private String adminId;

    @Embedded(prefix = "admin_")
    private User admin;

    private String avatar;

    private Double taxCharge;

    private Double percentageTaxCharge;

    private String taxChargeMode;

    private Double maxTaxCharge;

    private Double deliveryCharge;

    private Double percentageDeliveryCharge;

    private String deliveryChargeMode;

    private Double maxDeliveryCharge;

    public Store(@NonNull String id, String name, String adminId, User admin, String avatar,
                 Double taxCharge, Double percentageTaxCharge, String taxChargeMode,
                 Double maxTaxCharge, Double deliveryCharge, Double percentageDeliveryCharge,
                 String deliveryChargeMode, Double maxDeliveryCharge) {
        this.id = id;
        this.name = name;
        this.adminId = adminId;
        this.admin = admin;
        this.avatar = avatar;
        this.taxCharge = taxCharge;
        this.percentageTaxCharge = percentageTaxCharge;
        this.taxChargeMode = taxChargeMode;
        this.maxTaxCharge = maxTaxCharge;
        this.deliveryCharge = deliveryCharge;
        this.percentageDeliveryCharge = percentageDeliveryCharge;
        this.deliveryChargeMode = deliveryChargeMode;
        this.maxDeliveryCharge = maxDeliveryCharge;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdminId() {
        return adminId;
    }

    public User getAdmin() {
        return admin;
    }

    public String getAvatar() {
        return avatar;
    }

    public Double getTaxCharge() {
        return taxCharge;
    }

    public Double getPercentageTaxCharge() {
        return percentageTaxCharge;
    }

    public String getTaxChargeMode() {
        return taxChargeMode;
    }

    public Double getMaxTaxCharge() {
        return maxTaxCharge;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public Double getPercentageDeliveryCharge() {
        return percentageDeliveryCharge;
    }

    public String getDeliveryChargeMode() {
        return deliveryChargeMode;
    }

    public Double getMaxDeliveryCharge() {
        return maxDeliveryCharge;
    }

    @Ignore
    public static Store fromStoreResponseData(StoreResponseData data){
        User admin = new User(data.getAdminFirstName(), data.getAdminLastName(), data.getAdminEmail(),
                null, null, null, null, null);

        return new Store(data.getId(), data.getName(), data.getAdminId(), admin, data.getAvatar(),
                data.getTaxCharge(), data.getPercentageTaxCharge(), data.getTaxChargeMode(),
                data.getMaxTaxCharge(), data.getDeliveryCharge(), data.getPercentageDeliveryCharge(),
                data.getDeliveryChargeMode(), data.getMaxDeliveryCharge());
    }
}
