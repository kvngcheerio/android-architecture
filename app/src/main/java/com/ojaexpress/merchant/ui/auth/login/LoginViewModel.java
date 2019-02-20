package com.ojaexpress.merchant.ui.auth.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.models.User;
import com.ojaexpress.merchant.data.local.repositories.AuthRepository;
import com.ojaexpress.merchant.data.network.responses.LoginResponseBody;
import com.ojaexpress.merchant.utils.InjectorUtils;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class LoginViewModel extends ViewModel {

    private LiveData<String> loginMsg;
    private LiveData<Integer> loginStatus;

    private final AuthRepository authRepository;

    public LoginViewModel (AuthRepository authRepository) {
        this.authRepository = authRepository;
        this.loginStatus = authRepository.getLoginStatus();
        this.loginMsg = authRepository.getLoginMsg();
    }

    public void login(String email, String password)
    {
        authRepository.login(email, password);
    }

    LiveData<Integer> getLoginStatus() {
        return loginStatus;
    }

    LiveData<String> getLoginMsg(){
        return loginMsg;
    }

    LiveData<LoginResponseBody> getAuthResponseBody() {
        return authRepository.getAuthUserResponse();
    }
}
