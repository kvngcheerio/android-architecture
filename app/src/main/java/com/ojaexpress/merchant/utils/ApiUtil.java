package com.ojaexpress.merchant.utils;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class ApiUtil {
    public static String BASE_URL = "https://api.staging.ojaexpress.com/v1/";

    // user-related
    public static final String LOGIN = "clients/web/admin/login";
    public static final String SIGN_UP = "register";
    public static final String REQUEST_PASSWORD_RESET_LINK = "user/password/reset-link";

    // stores
    public static final String GET_ALL_ADMIN_STORES = "admin/stores";
    public static final String GET_STORE_BY_ID = "stores/{id}";

    // products
    public static final String GET_ALL_ADMIN_PRODUCTS = "admin/products?orderBy=price&sortedBy=asc&include=categories";
    public static final String GET_PRODUCT_BY_ID = "products/{id}";

    // orders
    public static final String GET_ALL_ADMIN_ORDERS = "admin/orders?orderBy=created_at&sortedBy=desc";
    public static final String GET_ORDER_BY_ID = "orders/{id}?include=cartItems,deliveryAddress";

    // keys
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String OLD_PASSWORD = "password";
    public static final String NEW_PASSWORD = "password";
    public static final String GENDER = "gender";
    public static final String APP_VERSION = "app_version";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PHONE_NUM = "phone_num";

    public static final int CALL_IN_PROGRESS = 0;
    public static final int CALL_SUCCESSFUL = 1;
    public static final int CALL_FAILED = 2;


    public static String interpretErrorCode(int code, String apiCallUrl)
    {
        // TODO: return specific helpful error messages for each status code
        if (code == 401) {
            return "Authentication failed";
        }

        return "Something went wrong with the server";
    }
}
