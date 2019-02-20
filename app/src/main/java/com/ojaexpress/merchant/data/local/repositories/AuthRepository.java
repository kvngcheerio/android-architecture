package com.ojaexpress.merchant.data.local.repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.ojaexpress.merchant.AppExecutors;
import com.ojaexpress.merchant.data.network.responses.LoginResponseBody;
import com.ojaexpress.merchant.data.network.sources.AuthNetworkDataSource;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class AuthRepository {

    private static final String LOG_TAG = AuthRepository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static volatile AuthRepository sInstance;

    private AuthNetworkDataSource authNetworkDataSource;
    private AppExecutors executors;

    private AuthRepository(AuthNetworkDataSource authNetworkDataSource,
                          AppExecutors executors) {
        this.authNetworkDataSource = authNetworkDataSource;
        this.executors = executors;
    }

    public static AuthRepository getInstance(AuthNetworkDataSource authNetworkDataSource,
                                             AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new AuthRepository(authNetworkDataSource, executors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<LoginResponseBody> getAuthUserResponse() {
        return authNetworkDataSource.getAuthUserResponse();
    }

    public void login(String email, String password)
    {
        authNetworkDataSource.login(email, password);
    }

    public void signUp(String firstName, String lastName, String email, String password){
        authNetworkDataSource.signUp(firstName, lastName, email, password);
    }

    public void getPasswordResetLink(String email) {
        authNetworkDataSource.getPasswordResetLink(email);
    }

    public LiveData<Integer> getLoginStatus(){
        return authNetworkDataSource.getLoginStatus();
    }

    public LiveData<String> getLoginMsg() {
        return authNetworkDataSource.getLoginMsg();
    }

    public LiveData<Integer> getSignUpStatus(){
        return authNetworkDataSource.getSignUpStatus();
    }

    public LiveData<String> getSignUpMsg() {
        return authNetworkDataSource.getSignUpMsg();
    }

    public LiveData<Integer> getPasswordRecoveryStatus(){
        return authNetworkDataSource.getPasswordRecoveryStatus();
    }

    public LiveData<String> getPasswordRecoveryMsg() {
        return authNetworkDataSource.getPasswordRecoveryMsg();
    }
}
