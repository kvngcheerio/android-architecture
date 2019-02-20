package com.ojaexpress.merchant.ui.auth.register;

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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.ui.auth.login.LoginActivity;
import com.ojaexpress.merchant.ui.main.MainActivity;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.CustomPreferenceManager;
import com.ojaexpress.merchant.utils.InjectorUtils;


public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText;
    private View mProgressView;
    private View mSignUpFormView;
    private boolean inProgress = false;
    SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CustomPreferenceManager.getInstance(this).isLoggedIn()) {
            startMainActivity();
            finish();
            return;
        }
        setContentView(R.layout.activity_sign_up);
        initializeUIComponents();
        SignUpViewModelFactory factory = InjectorUtils.provideSignUpViewModelFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel.class);
        observeSignUpStatus();
        observeSignUpMsg();
    }

    private void observeSignUpStatus(){
        viewModel.getSignUpStatus().observe(this, status -> {
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

    private void observeSignUpMsg() {
        viewModel.getSignUpMsg().observe(this, msg -> {
            if (msg == null) return;
            showMsg(msg);
        });
    }

    private void showMsg(String msg) {
        Snackbar.make(mSignUpFormView, msg, Snackbar.LENGTH_SHORT);
    }


    private void initializeUIComponents() {
        firstNameEditText = (EditText) findViewById(R.id.first_name_edit_tv);
        lastNameEditText = (EditText) findViewById(R.id.last_name_edit_tv);
        emailEditText = (AutoCompleteTextView) findViewById(R.id.email_auto_complete_tv);
        passwordEditText = (EditText) findViewById(R.id.password_edit_tv);

        Button signUpButton = (Button) findViewById(R.id.register_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });

        TextView loginOptionTextView = (TextView) this.findViewById(R.id.already_registered_login_tv);

        loginOptionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });

        mSignUpFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.sign_up_progress);
    }

    private void startMainActivity() {
        startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
    }

    private void startLoginActivity() {
        startActivity(new Intent(this.getApplicationContext(), LoginActivity.class));
    }

    private void resetInputErrors() {
        firstNameEditText.setError(null);
        lastNameEditText.setError(null);
        emailEditText.setError(null);
        passwordEditText.setError(null);
    }

    private void attemptSignUp() {

        if (inProgress) return;

        resetInputErrors();

        //getting the user values
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();


        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("Please enter Your First Name");
            firstNameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Please enter Your Last Name");
            lastNameEditText.requestFocus();
            return;
        }

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

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Enter a password");
            passwordEditText.requestFocus();
            return;
        }

        showProgress(true);
        viewModel.signUp(firstName, lastName, email, password);
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

            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignUpFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
