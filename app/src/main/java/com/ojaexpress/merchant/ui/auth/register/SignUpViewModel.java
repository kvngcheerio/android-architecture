package com.ojaexpress.merchant.ui.auth.register;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ojaexpress.merchant.data.local.repositories.AuthRepository;

/**
 * Created by funso on 24/05/2018.
 * <p>
 * Peace
 */

public class SignUpViewModel extends ViewModel {

    private LiveData<String> signUpMsg;
    private LiveData<Integer> signUpStatus;

    private final AuthRepository authRepository;

    public SignUpViewModel (AuthRepository authRepository) {
        this.authRepository = authRepository;
        this.signUpStatus = authRepository.getSignUpStatus();
        this.signUpMsg = authRepository.getSignUpMsg();
    }

    void signUp(String firstName, String lastName, String email, String password)
    {
        authRepository.signUp(firstName, lastName, email, password);
    }

    LiveData<Integer> getSignUpStatus() {
        return signUpStatus;
    }

    LiveData<String> getSignUpMsg(){
        return signUpMsg;
    }
}
