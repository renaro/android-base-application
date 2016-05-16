package com.renarosantos.mybaseapplication.register.view;

public interface RegisterView {

    void onRegisterSuccess();

    void showLoading();

    void hideLoading();

    String getEmail();

    String getPassword2();

    String getName();

    String getPassword();

    void showRequiredFieldsError();

    void showDifferentPasswordsError();

    void onError();

    void openSuccessActivity();

}
