package com.renarosantos.mybaseapplication.login.bo;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.renarosantos.mybaseapplication.remote.request.FacebookLoginRequest;
import com.renarosantos.mybaseapplication.remote.response.LoginResponse;
import com.renarosantos.mybaseapplication.user.dao.AppUserDAO;
import com.renarosantos.mybaseapplication.user.dao.FacebookUserCredentials;
import com.renarosantos.mybaseapplication.user.dao.LoggedUser;
import com.renarosantos.mybaseapplication.user.dao.UserPreferences;

/**
 * Created by renarosantos on 17/05/16.
 */
public class LoginBO {
    private final UserPreferences mUserPreferences;
    private final AppUserDAO mUserDAO;

    public LoginBO(@NonNull final AppUserDAO userDAO, @NonNull final UserPreferences userPreferences) {
        mUserDAO = userDAO;
        mUserPreferences = userPreferences;
    }

    @WorkerThread
    public LoggedUser login(String email, String password) {
        final LoginResponse result = mUserDAO.login(email, password);
        if (result != null && result.getStatus()) {
            final LoggedUser loggedUser = LoggedUser.from(result);
            mUserPreferences.setLoggedUser(loggedUser);
            return loggedUser;
        } else {
            return null;
        }
    }

    public LoggedUser getLoggedUser() {
        return mUserPreferences.getLoggedUser();
    }

    public LoggedUser loginWithFacebook() {
        final FacebookUserCredentials facebookUserCredentials = mUserPreferences.getFacebookUserCredentials();
        if (facebookUserCredentials != null) {
            final FacebookLoginRequest loginRequest =
                    new FacebookLoginRequest(facebookUserCredentials.token());
            final LoginResponse loginResponse = mUserDAO.loginWithFacebook(loginRequest);
            if (loginResponse != null && loginResponse.getStatus()) {
                final LoggedUser loggedUser = LoggedUser.from(loginResponse);
                mUserPreferences.setLoggedUser(loggedUser);
                return loggedUser;
            }
        } else {
            return null;
        }
        return null;
    }

}
