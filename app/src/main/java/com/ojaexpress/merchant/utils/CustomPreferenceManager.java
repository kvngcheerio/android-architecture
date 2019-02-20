package com.ojaexpress.merchant.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by funso on 16/05/2018.
 * <p>
 * Peace
 */

public class CustomPreferenceManager {

    private static final String SHARED_PREF_NAME = "ojaExpress-merchant-shared-pref";
    private static final String KEY_IS_APP_FIRST_LAUNCH = "is_app_first_launch";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_TOKEN_TYPE = "token_type";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_FIRST_NAME = "user_first_name";
    private static final String KEY_USER_LAST_NAME = "user_last_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_IMAGE_URL = "user_image_url";
    private static final String KEY_USER_GENDER = "user_gender";
    private static final String KEY_USER_PHONE_NUMBER = "user_phone_number";
    private static final String KEY_USER_CREATED_AT = "user_created_at";
    private static final String KEY_USER_UPDATED_AT = "user_updated_at";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_USER_LAST_LOGIN_TIME = "user_last_login_time";

    private static final Object LOCK = new Object();
    private static volatile CustomPreferenceManager sInstance;
    private SharedPreferences preferences;

    private CustomPreferenceManager(Context context) {
        preferences = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE
        );
    }

    public static synchronized CustomPreferenceManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new CustomPreferenceManager(context);
                }
            }
        }
        return sInstance;
    }


    public boolean isAppFirstLaunch(){
        return this.preferences.getBoolean(KEY_IS_APP_FIRST_LAUNCH, true);
    }

    public void setIsAppFirstLaunch(boolean isAppFirstLaunch){
        this.preferences.edit().putBoolean(KEY_IS_APP_FIRST_LAUNCH, isAppFirstLaunch).apply();
    }

    public String getUserId(){
        return this.preferences.getString(KEY_USER_ID, null);
    }

    public void setUserId(String userId){
        this.preferences.edit().putString(KEY_USER_ID, userId).apply();
    }

    public String getUserEmail(){
        return this.preferences.getString(KEY_USER_EMAIL, "");
    }

    public void setUserEmail(String email){
        this.preferences.edit().putString(KEY_USER_EMAIL, email).apply();
    }

    public String getUserGender(){
        return this.preferences.getString(KEY_USER_GENDER, "");
    }

    public void setUserGender(String gender){
        this.preferences.edit().putString(KEY_USER_GENDER, gender).apply();
    }

    public String getUserFirstName(){
        return this.preferences.getString(KEY_USER_FIRST_NAME, null);
    }

    public void setUserPhoneNumber(String userPhoneNumber){
        this.preferences.edit().putString(KEY_USER_PHONE_NUMBER, userPhoneNumber).apply();
    }

    public String getUserPhoneNumber(){
        return this.preferences.getString(KEY_USER_PHONE_NUMBER, null);
    }

    public String getUserCreatedAt(){
        return this.preferences.getString(KEY_USER_CREATED_AT, null);
    }

    public void setUserCreatedAt(String userCreatedAt){
        this.preferences.edit().putString(KEY_USER_CREATED_AT, userCreatedAt).apply();
    }

    public String getUserUpdatedAt(){
        return this.preferences.getString(KEY_USER_UPDATED_AT, null);
    }

    public void setUserUpdatedAt(String userModifiedAt){
        this.preferences.edit().putString(KEY_USER_UPDATED_AT, userModifiedAt).apply();
    }

    public void setUserFirstName(String firstName){
        this.preferences.edit().putString(KEY_USER_FIRST_NAME, firstName).apply();
    }

    public String getUserLastName(){
        return this.preferences.getString(KEY_USER_LAST_NAME, null);
    }

    public void setUserLastName(String lastName){
        this.preferences.edit().putString(KEY_USER_LAST_NAME, lastName).apply();
    }

    public String getUserImageUrl(){
        return this.preferences.getString(KEY_USER_IMAGE_URL, null);
    }

    public void setUserImageUrl(String userImageUrl){
        this.preferences.edit().putString(KEY_USER_IMAGE_URL, userImageUrl).apply();
    }

    public boolean isLoggedIn(){
        return this.preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean loggedIn){
        this.preferences.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply();

    }

    public String getAccessToken(){
        return this.preferences.getString(KEY_ACCESS_TOKEN, null);
    }

    public void setAccessToken(String accessToken){
        this.preferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public String getTokenType(){
        return this.preferences.getString(KEY_TOKEN_TYPE, null);
    }

    public void setTokenType(String accessToken){
        this.preferences.edit().putString(KEY_TOKEN_TYPE, accessToken).apply();
    }

    public void setLastLoginDatetime(String lastLoginDatetime){
        this.preferences.edit().putString(KEY_USER_LAST_LOGIN_TIME, lastLoginDatetime).apply();
    }

    public String getLastLoginDatetime(){
        return this.preferences.getString(KEY_USER_LAST_LOGIN_TIME, null);
    }

    public String getAppVersion(){
        return "1.0.0";
    }
}
