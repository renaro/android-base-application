package com.renarosantos.mybaseapplication.register.bo;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.renarosantos.mybaseapplication.remote.response.LoginResponse;
import com.renarosantos.mybaseapplication.user.dao.AppUserDAO;
import com.renarosantos.mybaseapplication.user.dao.LoggedUser;
import com.renarosantos.mybaseapplication.user.dao.UserPreferences;

/**
 * Created by renarosantos on 17/05/16.
 */
public class RegisterBO {

    private final AppUserDAO mUserDAO;
    private final UserPreferences mUserPreferences;

    public RegisterBO(@NonNull final AppUserDAO dao, @NonNull UserPreferences preferences) {
        mUserDAO = dao;
        mUserPreferences = preferences;
    }


    @WorkerThread
    public LoggedUser register(String email, String name, String password) {
        final LoginResponse result = mUserDAO.registerUser(email, name, password);
        if ( result!= null && result.getStatus()) {
            final LoggedUser loggedUser = LoggedUser.from(result);
            mUserPreferences.setLoggedUser(loggedUser);
            return loggedUser;
        } else {
            return null;
        }
    }

    public LoggedUser isLogged() {
        return mUserPreferences.getLoggedUser();
    }

    @UiThread
    public boolean isPushTokenRegistered() {
        return mUserPreferences.isPushTokenRegistered();
    }

    public String getPushToken() {
        return mUserDAO.getPushToken();
    }

    public void registerToken(@NonNull final String pushToken) {
        final LoggedUser loggedUser = mUserPreferences.getLoggedUser();
        if (loggedUser != null) {
            final boolean result = mUserDAO.registerToken(pushToken, loggedUser);
            if (result) {
                mUserPreferences.registerPushToken(pushToken);
            }
        }
    }
}
