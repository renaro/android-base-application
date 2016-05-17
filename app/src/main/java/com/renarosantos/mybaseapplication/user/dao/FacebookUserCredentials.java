package com.renarosantos.mybaseapplication.user.dao;

/**
 * Created by renarosantos on 18/03/16.
 */
public class FacebookUserCredentials {

    private final String mFacebookUserId;
    private final String mToken;

    public FacebookUserCredentials(final String facebookUserId, final String token) {
        mFacebookUserId = facebookUserId;
        mToken = token;
    }

    public String facebookUserId() {
        return mFacebookUserId;
    }

    public String token() {
        return mToken;
    }

}
