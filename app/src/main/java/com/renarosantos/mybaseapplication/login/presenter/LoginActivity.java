package com.renarosantos.mybaseapplication.login.presenter;

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

import com.renarosantos.mybaseapplication.R;
import com.renarosantos.mybaseapplication.base.BaseActivity;
import com.renarosantos.mybaseapplication.base.BasePresenter;
import com.renarosantos.mybaseapplication.login.view.LoginView;

/**
 * Created by renarosantos on 23/10/15.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    EditText mEmail;
    EditText mPassword;
    View mLoginView;
    LoginPresenter mPresenter;
    CoordinatorLayout mCoordinatorLayout;
    private ProgressDialog mProgress;
    private Toolbar mToolbar;

    public static Intent createIntent(@NonNull final Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @NonNull
    @Override
    protected BasePresenter createPresenter(@NonNull final Activity activity) {
//        return new LoginPresenter(new FacebookSdkLogin(new UserPreferences(this)),
//                new AppTaskExecutor(this), new RegisterBO(new UserDAO(this), new UserPreferences(this)),
//                new LoginBO(new UserDAO(this), new UserPreferences(this)), this);
        return null;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mPresenter.isLogged()) {
            openNextActivity();
        } else {
            setContentView(R.layout.login_activity);
            mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
            mEmail = (EditText) findViewById(R.id.email);
            mPassword = (EditText) findViewById(R.id.password);
            mLoginView = findViewById(R.id.start);
            mLoginView.setOnClickListener(new LoginClicked());

            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            final ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setTitle(R.string.login_activity_title);
            }
            mToolbar.setNavigationOnClickListener(new OnBackPressed());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProgress != null) {
            mProgress.dismiss();
        }
    }

    @Override
    public void onLoginFailed() {
        Snackbar.make(mCoordinatorLayout, getResources().getString(R.string.error_wrong_credentials_message), Snackbar.LENGTH_LONG).show();
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

    private void hidekeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showRequiredFieldsError() {
        Snackbar.make(mCoordinatorLayout, R.string.error_login_required_fields, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Add here the next activity you want to open
     */
    @Override
    public void openNextActivity() {
//        startActivity(HomeActivity.createIntentWithFlags(this));
//        finish();
    }

//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mPresenter.onActivityResult(requestCode, resultCode, data);
//    }

    private class OnBackPressed implements View.OnClickListener {

        @Override
        public void onClick(final View v) {
            onBackPressed();
        }
    }

    private class LoginClicked implements View.OnClickListener {

        @Override
        public void onClick(final View v) {
            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();
            hidekeyboard();
            mPresenter.login(email, password);
        }
    }
}
