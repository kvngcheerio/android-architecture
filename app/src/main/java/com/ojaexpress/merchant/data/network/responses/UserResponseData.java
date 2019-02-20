package com.ojaexpress.merchant.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class UserResponseData {

    @SerializedName("object")
    @Expose
    private String object;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("confirmed")
    @Expose
    private Boolean confirmed;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("birth")
    @Expose
    private String birth;

    @SerializedName("country_id")
    @Expose
    private Integer countryId;

    @SerializedName("zip_code")
    @Expose
    private String zipCode;

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("created_at")
    @Expose
    private DatetimeResponseObject createdAt;

    @SerializedName("updated_at")
    @Expose
    private DatetimeResponseObject updatedAt;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}
