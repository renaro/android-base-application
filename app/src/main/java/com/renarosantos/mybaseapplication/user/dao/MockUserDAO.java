package com.renarosantos.mybaseapplication.user.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.renarosantos.mybaseapplication.R;
import com.renarosantos.mybaseapplication.remote.request.FacebookLoginRequest;
import com.renarosantos.mybaseapplication.remote.response.LoginResponse;

import java.io.IOException;

/**
 * Created by renarosantos on 25/05/16. This is a class to test, once you have your server to validate user
 * login/register do not use this class anymore
 */
public class MockUserDAO implements AppUserDAO {

    private final Context mContext;

    public MockUserDAO(@NonNull final Context context) {
        mContext = context;
    }

    /**
     * Try to login a user in your server with a given email and password.
     *
     * @param email
     * @param password
     *
     * @return a LoginResponse object in case of success.
     */
    @Nullable
    @Override
    public LoginResponse login(@NonNull final String email, @NonNull final String password) {
        return LoginResponse.mock();
    }

    /**
     * Register a user in your server with its email, name and password
     *
     * @param email
     * @param name
     * @param password
     *
     * @return a LoginResponse object in case of success
     */
    @Nullable
    @Override
    public LoginResponse registerUser(@NonNull final String email, @NonNull final String name,
                                      @NonNull final String password) {
        return LoginResponse.mock();
    }

    /**
     * @param loginRequest,
     *         send to your server to validade facebook login
     *
     * @return
     */
    @Override
    public LoginResponse loginWithFacebook(@NonNull final FacebookLoginRequest loginRequest) {
        return LoginResponse.mock();
    }

    /**
     * @return device push token or a mocked one
     */
    @Override
    public String getPushToken() {
        InstanceID instanceID = InstanceID.getInstance(mContext);
        try {
            return instanceID.getToken(mContext.getString(R.string.gcm_sender_id),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        } catch (IOException e) {
            return "mocked-push-token-123";
        }
    }

    /**
     * @param pushToken
     * @param loggedUser
     *
     * @return true if the facebook token was registered successfuly in your server
     */
    @Override
    public boolean registerToken(final String pushToken, final LoggedUser loggedUser) {
        return false;
    }
}
