package com.renarosantos.mybaseapplication.register.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.renarosantos.mybaseapplication.R;
import com.renarosantos.mybaseapplication.base.BaseActivity;
import com.renarosantos.mybaseapplication.base.BasePresenter;
import com.renarosantos.mybaseapplication.login.bo.LoginBO;
import com.renarosantos.mybaseapplication.mechanism.AppTaskExecutor;
import com.renarosantos.mybaseapplication.mechanism.FacebookSdkLogin;
import com.renarosantos.mybaseapplication.register.bo.RegisterBO;
import com.renarosantos.mybaseapplication.register.view.RegisterView;
import com.renarosantos.mybaseapplication.user.dao.MockUserDAO;
import com.renarosantos.mybaseapplication.user.dao.UserPreferences;

public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener {

    EditText mEmail;

    EditText mName;

    EditText mPassword;

    EditText mPassword2;
    View mRegisterButton;
    CoordinatorLayout mCoordinatorLayout;
    private ProgressDialog mProgress;
    private Toolbar mToolbar;

    public static Intent createIntent(@NonNull final Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @NonNull
    @Override
    protected BasePresenter createPresenter(@NonNull final Activity activity) {
        return new RegisterPresenter(new LoginBO(new MockUserDAO(this), new UserPreferences(this)),
                new FacebookSdkLogin(new UserPreferences(this)), new AppTaskExecutor(this),
                new RegisterBO(new MockUserDAO(this), new UserPreferences(this)), this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mEmail = (EditText) findViewById(R.id.email);
        mName = (EditText) findViewById(R.id.name);
        mPassword = (EditText) findViewById(R.id.password);
        mPassword2 = (EditText) findViewById(R.id.confirm_password);
        mRegisterButton = findViewById(R.id.register);
        mRegisterButton.setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(R.string.register_activity_title);
        }
        mToolbar.setNavigationOnClickListener(new OnBackPressed());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProgress != null) {
            mProgress.dismiss();
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, getString(R.string.register_success_message), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showLoading() {
        if (mProgress == null) {
            mProgress = ProgressDialog.show(this, getString(R.string.loading), getString(R.string.please_wait), true);
        } else if (!mProgress.isShowing()) {
            mProgress = ProgressDialog.show(this, getString(R.string.loading), getString(R.string.please_wait), true);
        }
    }

    @Override
    public void hideLoading() {
        if (mProgress != null) {
            mProgress.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mRegisterButton.getId()) {
            hideKeyboard();
            //            mPresenter.register();
        }
    }

    @Override
    public void showRequiredFieldsError() {
        Snackbar.make(mCoordinatorLayout, getString(R.string.error_missing_fields_message), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showDifferentPasswordsError() {
        Snackbar.make(mCoordinatorLayout, getString(R.string.error_password_does_not_match), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onError() {
        Snackbar.make(mCoordinatorLayout, getString(R.string.error_general_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void openSuccessActivity() {
        //open the next acitivy here.
    }

    public String getName() {
        return mName.getText().toString();
    }

    public String getPassword() {
        return mPassword.getText().toString();
    }

    public String getPassword2() {
        return mPassword2.getText().toString();
    }

    public String getEmail() {
        return mEmail.getText().toString();
    }

    private class OnBackPressed implements View.OnClickListener {

        @Override
        public void onClick(final View v) {
            onBackPressed();
        }
    }
}
