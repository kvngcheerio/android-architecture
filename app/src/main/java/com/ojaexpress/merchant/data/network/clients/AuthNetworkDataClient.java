package com.ojaexpress.merchant.data.network.clients;

import com.ojaexpress.merchant.data.network.requests.LoginRequestBody;
import com.ojaexpress.merchant.data.network.requests.PasswordResetLinkRequestBody;
import com.ojaexpress.merchant.data.network.requests.SignUpRequestBody;
import com.ojaexpress.merchant.data.network.responses.LoginResponseBody;
import com.ojaexpress.merchant.data.network.responses.PasswordResetLinkResponseBody;
import com.ojaexpress.merchant.data.network.responses.SignUpResponseBody;
import com.ojaexpress.merchant.utils.ApiUtil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public interface AuthNetworkDataClient {

    @POST(ApiUtil.LOGIN)
    Call<LoginResponseBody> login(@Body LoginRequestBody requestBody);

    @POST(ApiUtil.REQUEST_PASSWORD_RESET_LINK)
    Call<PasswordResetLinkResponseBody> requestPasswordResetLink(@Body PasswordResetLinkRequestBody requestBody);

    @POST(ApiUtil.SIGN_UP)
    Call<SignUpResponseBody> signUp(@Body SignUpRequestBody requestBody);
}
