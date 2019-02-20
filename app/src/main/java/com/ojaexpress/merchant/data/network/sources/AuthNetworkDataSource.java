package com.ojaexpress.merchant.data.network.sources;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ojaexpress.merchant.data.network.clients.AuthNetworkDataClient;
import com.ojaexpress.merchant.data.network.requests.LoginRequestBody;
import com.ojaexpress.merchant.data.network.requests.PasswordResetLinkRequestBody;
import com.ojaexpress.merchant.data.network.requests.SignUpRequestBody;
import com.ojaexpress.merchant.data.network.responses.LoginResponseBody;
import com.ojaexpress.merchant.data.network.responses.PasswordResetLinkResponseBody;
import com.ojaexpress.merchant.data.network.responses.SignUpResponseBody;
import com.ojaexpress.merchant.utils.ApiUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class AuthNetworkDataSource {

    private static final String LOG_TAG = AuthNetworkDataSource.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static volatile AuthNetworkDataSource sInstance;
    private final AuthNetworkDataClient authNetworkDataClient;

    private final MutableLiveData<LoginResponseBody> authUserResponse;
    private final MutableLiveData<String> loginMsg;
    private final MutableLiveData<Integer> loginStatus;
    private final MutableLiveData<String> signUpMsg;
    private final MutableLiveData<Integer> signUpStatus;
    private final MutableLiveData<String> passwordRecoveryMsg;
    private final MutableLiveData<Integer> passwordRecoveryStatus;

    private AuthNetworkDataSource(AuthNetworkDataClient authNetworkDataClient) {
        this.authNetworkDataClient = authNetworkDataClient;
        this.authUserResponse = new MutableLiveData<>();
        this.loginMsg = new MutableLiveData<>();
        this.loginStatus = new MutableLiveData<>();
        this.signUpMsg = new MutableLiveData<>();
        this.signUpStatus = new MutableLiveData<>();
        this.passwordRecoveryMsg = new MutableLiveData<>();
        this.passwordRecoveryStatus = new MutableLiveData<>();
    }

    public static AuthNetworkDataSource getInstance(AuthNetworkDataClient authNetworkDataClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new AuthNetworkDataSource(authNetworkDataClient);
                }
            }
        }
        return sInstance;
    }

    public LiveData<String> getLoginMsg() {
        return loginMsg;
    }

    public LiveData<Integer> getLoginStatus() {
        return loginStatus;
    }

    public LiveData<String> getSignUpMsg() {
        return signUpMsg;
    }

    public LiveData<Integer> getSignUpStatus() {
        return signUpStatus;
    }

    public LiveData<String> getPasswordRecoveryMsg() {
        return passwordRecoveryMsg;
    }

    public LiveData<Integer> getPasswordRecoveryStatus() {
        return passwordRecoveryStatus;
    }

    public LiveData<LoginResponseBody> getAuthUserResponse() {
        return authUserResponse;
    }

    public void login(String email, String password) {
        Log.e(LOG_TAG, "Calling API...");
        loginStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        final LoginRequestBody loginRequestBody = new LoginRequestBody(email, password);

        Call<LoginResponseBody> call = this.authNetworkDataClient.login(loginRequestBody);
        call.enqueue(new Callback<LoginResponseBody>() {

            @Override
            public void onResponse(Call<LoginResponseBody> call, Response<LoginResponseBody> response) {
                int statusCode = response.code();
                Log.e(LOG_TAG, "Login url => " + call.request().url().toString() + " response returned with code ==> " + statusCode  );

                if (statusCode == 200) {
                    loginMsg.postValue("Welcome to OjaExpress!");
                    loginStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    authUserResponse.postValue(response.body());
                } else {
                    loginMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.LOGIN));
                    loginStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<LoginResponseBody> call, Throwable t) {
                Log.e(LOG_TAG, "Error upon login " + t.getMessage());

                loginMsg.postValue("Error upon login");
                loginStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }

    public void signUp(String firstName, String lastName, String email, String password) {
        signUpStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        final SignUpRequestBody requestBody = new SignUpRequestBody(firstName, lastName, email, password);
        Call<SignUpResponseBody> call = this.authNetworkDataClient.signUp(requestBody);
        call.enqueue(new Callback<SignUpResponseBody>() {
            @Override
            public void onResponse(Call<SignUpResponseBody> call, Response<SignUpResponseBody> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    signUpMsg.postValue("Welcome to OjaExpress!");
                    signUpStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                } else {
                    signUpMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.LOGIN));
                    signUpStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<SignUpResponseBody> call, Throwable t) {

                Log.e(LOG_TAG, "Error upon sign up " + t.getMessage());

                signUpMsg.postValue("Error upon sign up");
                signUpStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }

    public void getPasswordResetLink(String email) {
        passwordRecoveryStatus.postValue(ApiUtil.CALL_IN_PROGRESS);
        final PasswordResetLinkRequestBody requestBody = new PasswordResetLinkRequestBody(email);
        Call<PasswordResetLinkResponseBody> call = this.authNetworkDataClient.requestPasswordResetLink(requestBody);
        call.enqueue(new Callback<PasswordResetLinkResponseBody>() {
            @Override
            public void onResponse(Call<PasswordResetLinkResponseBody> call, Response<PasswordResetLinkResponseBody> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    signUpMsg.postValue("Password reset link has been to your email address!");
                    signUpStatus.postValue(ApiUtil.CALL_SUCCESSFUL);
                    // TODO: save User
                } else {
                    signUpMsg.postValue(ApiUtil.interpretErrorCode(statusCode, ApiUtil.LOGIN));
                    signUpStatus.postValue(ApiUtil.CALL_FAILED);
                }
            }

            @Override
            public void onFailure(Call<PasswordResetLinkResponseBody> call, Throwable t) {
                Log.e(LOG_TAG, "Error upon sign up " + t.getMessage());

                passwordRecoveryMsg.postValue("Error upon sign up");
                passwordRecoveryStatus.postValue(ApiUtil.CALL_FAILED);
            }
        });
    }
}
