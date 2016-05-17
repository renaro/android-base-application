package com.renarosantos.mybaseapplication.login.presenter;

/**
 * Created by renarosantos on 15/09/15.
 */
public class LoginPresenter {
//
//    private final LoginBO mLoginBO;
//    private final LoginView mView;
//    private final TaskExecutor mTaskExecutor;
//    private final FacebookLogin mFacebookLogin;
//    private final RegisterBO mRegisterBO;
//
//    public LoginPresenter(@NonNull final FacebookLogin facebookLogin, @NonNull final TaskExecutor taskExecutor,
//                          @NonNull final RegisterBO registerBO, @NonNull final LoginBO loginBO,
//                          @NonNull final LoginView view) {
//        mFacebookLogin = facebookLogin;
//        mLoginBO = loginBO;
//        mView = view;
//        mTaskExecutor = taskExecutor;
//        mRegisterBO = registerBO;
//    }
//
//    public void login(@NonNull final String email, @NonNull final String password) {
//        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//            mView.showRequiredFieldsError();
//        } else {
//            mView.showLoading();
//            mTaskExecutor.async(new LoginTask(email, password));
//        }
//    }
//
//    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        mFacebookLogin.onActivityResult(requestCode, resultCode, data, new FacebookLoginListener());
//    }
//
//    public boolean isLogged() {
//        return mLoginBO.getLoggedUser() != null;
//    }
//
//    private class LoginTask implements AppTask<LoggedUser> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        public LoginTask(final String email, final String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        public LoggedUser execute() {
//            return mLoginBO.login(mEmail, mPassword);
//        }
//
//        @Override
//        public void onPostExecute(@Nullable final LoggedUser result) {
//            mView.hideLoading();
//            if (result != null) {
//                loginSuccess();
//                mView.openHomeActivity();
//                mServiceStarter.startSyncService();
//            } else {
//                mView.onLoginFailed();
//            }
//        }
//    }
//
//    private void loginSuccess() {
//        if (!mRegisterBO.isPushTokenRegistered()) {
//            mTaskExecutor.async(new RegisterPushToken());
//        }
//    }
//
//    private class FacebookLoginListener implements FacebookLogin.Listener {
//
//        @Override
//        public void onFacebookLoginSuccess() {
//            mView.showLoading();
//            loginSuccess();
//            mTaskExecutor.async(new LoginWithFacebookData());
//        }
//
//        @Override
//        public void onFacebookLoginError() {
//            mView.hideLoading();
//            mView.onLoginFailed();
//        }
//
//        @Override
//        public void onFacebookLoginCancel() {
//            mView.hideLoading();
//            mView.onLoginFailed();
//        }
//    }
//
//    private class LoginWithFacebookData implements AppTask<LoggedUser> {
//
//        @Override
//        public LoggedUser execute() {
//            return mLoginBO.loginWithFacebook();
//        }
//
//        @Override
//        public void onPostExecute(@Nullable final LoggedUser result) {
//            mView.hideLoading();
//            if (result != null) {
//                loginSuccess();
//                mView.openHomeActivity();
//                mServiceStarter.startSyncService();
//            } else {
//                mView.onLoginFailed();
//            }
//        }
//    }
//
//    private class RegisterPushToken implements AppTask<Boolean> {
//
//        @Override
//        public Boolean execute() {
//            final String pushToken = mRegisterBO.getPushToken();
//            if (pushToken != null) {
//                mRegisterBO.registerToken(pushToken);
//                return mRegisterBO.isPushTokenRegistered();
//            }
//            return false;
//        }
//
//        @Override
//        public void onPostExecute(@Nullable final Boolean result) {
//
//        }
//    }
}
