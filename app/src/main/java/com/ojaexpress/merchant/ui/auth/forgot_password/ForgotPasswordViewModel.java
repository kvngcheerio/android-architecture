package com.ojaexpress.merchant.ui.auth.forgot_password;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.repositories.AuthRepository;

/**
 * Created by funso on 24/05/2018.
 * <p>
 * Peace
 */

public class ForgotPasswordViewModel extends ViewModel {

    private LiveData<String> passwordRecoveryMsg;
    private LiveData<Integer> passwordRecoveryStatus;

    private final AuthRepository authRepository;

    public ForgotPasswordViewModel (AuthRepository authRepository) {
        this.authRepository = authRepository;
        this.passwordRecoveryStatus = authRepository.getLoginStatus();
        this.passwordRecoveryMsg = authRepository.getLoginMsg();
    }

    public void getPasswordResetLink(String email)
    {
        authRepository.getPasswordResetLink(email);
    }

    LiveData<Integer> getPasswordRecoveryStatus() {
        return passwordRecoveryStatus;
    }

    LiveData<String> getPasswordRecoveryMsg(){
        return passwordRecoveryMsg;
    }
}
