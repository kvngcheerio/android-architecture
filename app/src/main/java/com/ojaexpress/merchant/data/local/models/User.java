package com.ojaexpress.merchant.data.local.models;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class User {

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    private String email;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @ColumnInfo(name = "zip_code")
    private String zipCode;

    private String gender;

    private String birth;

    @ColumnInfo(name = "country_id")
    private Integer countryId;

    public User(String firstName, String lastName, String email, String phoneNumber, String zipCode,
                String gender, String birth, Integer countryId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.gender = gender;
        this.birth = birth;
        this.countryId = countryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public Integer getCountryId() {
        return countryId;
    }
}
