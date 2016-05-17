package com.renarosantos.mybaseapplication.mechanism;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.renarosantos.mybaseapplication.user.dao.FacebookUserCredentials;
import com.renarosantos.mybaseapplication.user.dao.UserPreferences;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by renarosantos on 17/05/16.
 */
public class FacebookSdkLogin implements FacebookLogin {

    private static final String KEY_FACEBOOK_FIELDS = "fields";
    private static final String KEY_FACEBOOK_ALL_FIELDS = "id,first_name,last_name,email";

    private final AtomicBoolean mIsLoginRunning;
    private final UserPreferences mUserPreferences;
    private Listener mListener;
    private CallbackManager mCallbackManager;

    public FacebookSdkLogin(@NonNull final UserPreferences userPreferences) {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookLoginCallback());
        mIsLoginRunning = new AtomicBoolean(false);
        mUserPreferences = userPreferences;
    }

    @Override
    public void start(@NonNull Activity activity) {
        if (!mIsLoginRunning.getAndSet(true)) {
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
        }
    }

    @Override
    public void start(@NonNull Fragment fragment) {
        if (!mIsLoginRunning.getAndSet(true)) {
            LoginManager.getInstance().logInWithReadPermissions(fragment, Arrays.asList("public_profile"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull Listener listener) {
        mListener = listener;
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private class FacebookLoginCallback implements FacebookCallback<LoginResult> {

        @Override
        public void onSuccess(@NonNull final LoginResult loginResult) {
            final AccessToken accessToken = loginResult.getAccessToken();
            saveFacebookUserCredentials(accessToken);
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                    new FacebookJsonCallback());

            final Bundle facebookParameters = new Bundle();
            facebookParameters.putString(KEY_FACEBOOK_FIELDS, KEY_FACEBOOK_ALL_FIELDS);
            request.setParameters(facebookParameters);
            request.executeAsync();
        }

        private void saveFacebookUserCredentials(final AccessToken accessToken) {
            final String token = accessToken.getToken();
            final String facebookUserId = accessToken.getUserId();
            FacebookUserCredentials user = new FacebookUserCredentials(facebookUserId, token);
            mUserPreferences.saveFacebookUserCredentials(user);
        }

        @Override
        public void onError(final FacebookException e) {
            mIsLoginRunning.set(false);
            mListener.onFacebookLoginError();
        }

        @Override
        public void onCancel() {
            mIsLoginRunning.set(false);
            mListener.onFacebookLoginCancel();
        }

        private class FacebookJsonCallback implements GraphRequest.GraphJSONObjectCallback {


            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                FacebookLoggedUser user = FacebookLoggedUser.fromJson(jsonObject);
                if (user != null) {
                    mUserPreferences.saveFacebookProfileData(user.firstName(), user.lastName(), user.email());
                    mListener.onFacebookLoginSuccess();
                } else {
                    mListener.onFacebookLoginError();
                }
            }
        }
    }
}