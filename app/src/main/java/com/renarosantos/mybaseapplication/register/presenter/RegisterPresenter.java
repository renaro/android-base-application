package com.renarosantos.mybaseapplication.register.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.renarosantos.mybaseapplication.base.BasePresenter;
import com.renarosantos.mybaseapplication.login.bo.LoginBO;
import com.renarosantos.mybaseapplication.mechanism.AppTask;
import com.renarosantos.mybaseapplication.mechanism.FacebookLogin;
import com.renarosantos.mybaseapplication.mechanism.TaskExecutor;
import com.renarosantos.mybaseapplication.register.bo.RegisterBO;
import com.renarosantos.mybaseapplication.register.view.RegisterView;
import com.renarosantos.mybaseapplication.user.dao.LoggedUser;

/**
 * Created by renarosantos on 06/03/16.
 */
public class RegisterPresenter extends BasePresenter{

    private final RegisterView mView;
    private final RegisterBO mRegisterBO;
    private final TaskExecutor mTaskExecutor;
    private final FacebookLogin mFacebookLogin;
    private final LoginBO mLoginBO;

    public RegisterPresenter(@NonNull final LoginBO loginBO,  @NonNull final FacebookLogin facebookLogin,
                             @NonNull final TaskExecutor taskExecutor, @NonNull final RegisterBO registerBO,
                             @NonNull final RegisterView view) {
        mLoginBO = loginBO;
        mView = view;
        mRegisterBO = registerBO;
        mTaskExecutor = taskExecutor;
        mFacebookLogin = facebookLogin;
        final LoggedUser logged = mRegisterBO.isLogged();
        if (logged != null) {
            mView.openSuccessActivity();
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookLogin.onActivityResult(requestCode, resultCode, data, new FacebookLoginListener());
    }

    public void register() {

        String password = mView.getPassword();
        String password2 = mView.getPassword2();
        String name = mView.getName();
        String email = mView.getEmail();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(password2)) {
            mView.showRequiredFieldsError();
        } else if (!password.equals(password2)) {
            mView.showDifferentPasswordsError();
        } else {
            mView.showLoading();
            mTaskExecutor.async(new RegisterTask(email, name, password));
        }
    }

    private class RegisterTask implements AppTask<LoggedUser> {

        private final String mName;
        private final String mEmail;
        private final String mPassword;

        public RegisterTask(final String email, final String name, final String password) {
            mEmail = email;
            mName = name;
            mPassword = password;
        }

        @Override
        public LoggedUser execute() {
            return mRegisterBO.register(mEmail, mName, mPassword);
        }

        @Override
        public void onPostExecute(@Nullable final LoggedUser result) {
            mView.hideLoading();
            if (result != null) {
                registerSuccess();
                mView.onRegisterSuccess();
            } else {
                mView.onError();
            }
        }
    }

    private void registerSuccess() {
        if (!mRegisterBO.isPushTokenRegistered()) {
            mTaskExecutor.async(new RegisterPushToken());
        }
    }

    private class RegisterPushToken implements AppTask<Boolean> {

        @Override
        public Boolean execute() {
            final String pushToken = mRegisterBO.getPushToken();
            if (pushToken != null) {
                mRegisterBO.registerToken(pushToken);
                return mRegisterBO.isPushTokenRegistered();
            }
            return false;
        }

        @Override
        public void onPostExecute(@Nullable final Boolean result) {

        }
    }

    private class FacebookLoginListener implements FacebookLogin.Listener {

        @Override
        public void onFacebookLoginSuccess() {
            mView.showLoading();
            registerSuccess();
            mTaskExecutor.async(new LoginWithFacebookData());
        }

        @Override
        public void onFacebookLoginError() {
            mView.hideLoading();
            mView.onError();
        }

        @Override
        public void onFacebookLoginCancel() {
            mView.hideLoading();
            mView.onError();
        }
    }

    private class LoginWithFacebookData implements AppTask<LoggedUser> {

        @Override
        public LoggedUser execute() {
            return mLoginBO.loginWithFacebook();
        }

        @Override
        public void onPostExecute(@Nullable final LoggedUser result) {
            mView.hideLoading();
            if (result != null) {
                registerSuccess();
                mView.openSuccessActivity();
            } else {
                mView.onError();
            }
        }
    }
}
