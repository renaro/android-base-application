package com.renarosantos.mybaseapplication.user.dao;

/**
 * Created by renarosantos on 05/03/16.
 */
public class LoggedUser {

    private final String mUserToken;
    private final String mUserId;

    public LoggedUser(final String userToken, final String userId) {
        mUserToken = userToken;
        mUserId = userId;
    }

//    public static LoggedUser from(@NonNull final LoginResponse result) {
//        return new LoggedUser(result.getSession(), result.getUserId());
//    }

    public String userToken() {
        return mUserToken;
    }

    public String userId() {
        return mUserId;
    }
}
