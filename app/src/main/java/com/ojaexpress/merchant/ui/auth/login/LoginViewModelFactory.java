package com.ojaexpress.merchant.ui.auth.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ojaexpress.merchant.data.local.repositories.AuthRepository;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AuthRepository authRepository;

    public LoginViewModelFactory(AuthRepository authRepository)
    {
        this.authRepository = authRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new LoginViewModel(authRepository);
    }
}
