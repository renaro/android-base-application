package com.renarosantos.mybaseapplication.user.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.renarosantos.mybaseapplication.remote.request.FacebookLoginRequest;
import com.renarosantos.mybaseapplication.remote.response.LoginResponse;

/**
 * Created by renarosantos on 25/05/16.
 */
public interface AppUserDAO {

    @WorkerThread
    @Nullable
    LoginResponse login(@NonNull final String email, @NonNull final String password);

    @WorkerThread
    @Nullable
    LoginResponse registerUser(@NonNull final String email, @NonNull final String name,
                                      @NonNull final String password);

    @WorkerThread
    LoginResponse loginWithFacebook(@NonNull final FacebookLoginRequest loginRequest);

    @WorkerThread
    String getPushToken();

    @WorkerThread
    boolean registerToken(final String pushToken, final LoggedUser loggedUser);

}
