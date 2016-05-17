package com.renarosantos.mybaseapplication.login.view;

/**
 * Created by renarosantos on 17/05/16.
 */
public interface LoginView {

    void onLoginFailed();

    void showLoading();

    void hideLoading();

    void showRequiredFieldsError();

    void openNextActivity();

}
