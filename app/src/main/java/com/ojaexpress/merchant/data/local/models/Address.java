package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.network.responses.OrderResponseData;

/**
 * Created by funso on 25/05/2018.
 * <p>
 * Peace
 */

@Entity(tableName = "addresses")
public class Address {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "street_address")
    private String streetAddress;

    @ColumnInfo(name = "extended_address")
    private String extendedAddress;

    private String locality;

    @ColumnInfo(name = "zip_code")
    private String zipCode;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @ColumnInfo(name = "country_id")
    private Integer countryId;

    @ColumnInfo(name = "country_name")
    private String countryName;

    private Double latitude;

    private Double longitude;

    public Address (@NonNull String id, String streetAddress, String extendedAddress, String locality,
                    String zipCode, String phoneNumber, Integer countryId, String countryName,
                    Double latitude, Double longitude) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.extendedAddress = extendedAddress;
        this.locality = locality;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.countryName = countryName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getExtendedAddress() {
        return extendedAddress;
    }

    public String getLocality() {
        return locality;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public static Address fromOrderResponseData(OrderResponseData data) {
        return new Address(data.getDeliveryAddressId(), data.getDeliveryStreetAddress(),
                data.getDeliveryExtendedAddress(), data.getDeliveryAddressLocality(),
                data.getDeliveryAddressZipCode(), data.getDeliveryAddressPhoneNumber(),
                data.getDeliveryAddressCountryId(), data.getDeliveryAddressCountryName(),
                data.getDeliveryAddressLatitude(), data.getDeliveryAddressLongitude());
    }
}
