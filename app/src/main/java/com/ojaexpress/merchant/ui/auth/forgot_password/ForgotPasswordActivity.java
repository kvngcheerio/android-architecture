package com.ojaexpress.merchant.ui.auth.forgot_password;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.ui.auth.login.LoginActivity;
import com.ojaexpress.merchant.ui.main.MainActivity;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.CustomPreferenceManager;
import com.ojaexpress.merchant.utils.InjectorUtils;

public class ForgotPasswordActivity extends AppCompatActivity {

    private View forgotPasswordFormView;
    private View mProgressView;
    private EditText emailEditText;
    private boolean inProgress;
    ForgotPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (CustomPreferenceManager.getInstance(this).isLoggedIn()) {
            finish();
            startMainActivity();
            return;
        }
        setContentView(R.layout.activity_forgot_password);
        initializeUIComponents();
        ForgotPasswordViewModelFactory factory = InjectorUtils.provideForgotPasswordViewModelFactory(this.getApplicationContext());
        viewModel = ViewModelProviders.of(this, factory).get(ForgotPasswordViewModel.class);
        observePasswordRecoveryStatus();
        observePasswordRecoveryMsg();
    }

    private void observePasswordRecoveryStatus() {
        viewModel.getPasswordRecoveryStatus().observe(this, status -> {
            if (status == null) return;
            switch (status){
                case ApiUtil.CALL_IN_PROGRESS:
                    showProgress(true);
                    break;
                case ApiUtil.CALL_FAILED:
                    showProgress(false);
                    break;
                case ApiUtil.CALL_SUCCESSFUL:
                    showProgress(false);
                    finish();
                    startLoginActivity();
            }
        });
    }

    private void observePasswordRecoveryMsg() {
        viewModel.getPasswordRecoveryMsg().observe(this, msg -> {
            if (msg == null) return;
            showMsg(msg);
        });
    }

    private void showMsg(String msg) {
        Snackbar.make(forgotPasswordFormView, msg, Snackbar.LENGTH_SHORT);
    }

    private void initializeUIComponents() {
        Button recoverPasswordBtn = (Button) findViewById(R.id.recover_password_btn);
        emailEditText = (EditText) findViewById(R.id.email);
        TextView backToLoginText = (TextView) this.findViewById(R.id.back_to_login_tv);

        backToLoginText.setOnClickListener(view -> startLoginActivity());

        recoverPasswordBtn.setOnClickListener(view -> {
            attemptPasswordRecovery();
        });
        forgotPasswordFormView = findViewById(R.id.forgot_password_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void startMainActivity() {
        startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
    }

    private void startLoginActivity() {
        startActivity(new Intent(this.getApplicationContext(), LoginActivity.class));
    }

    private void attemptPasswordRecovery() {
        if (inProgress) return;

        String email = emailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Please enter your email");
            emailEditText.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email");
            emailEditText.requestFocus();
            return;
        }

        showProgress(true);
        viewModel.getPasswordResetLink(email);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        inProgress = show;
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            forgotPasswordFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            forgotPasswordFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    forgotPasswordFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
