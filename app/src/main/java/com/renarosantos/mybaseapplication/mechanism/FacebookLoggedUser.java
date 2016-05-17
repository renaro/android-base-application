package com.renarosantos.mybaseapplication.mechanism;

import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by renarosantos on 17/03/16.
 */
public class FacebookLoggedUser {

    private static final String KEY_FACEBOOK_FIRST_NAME = "first_name";
    private static final String KEY_FACEBOOK_LAST_NAME = "last_name";
    private static final String KEY_FACEBOOK_EMAIL = "email";

    private final String mFirstName;
    private final String mLastName;
    private final String mEmail;

    public FacebookLoggedUser(final String firstName, final String lastName, final String email) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
    }

    public String firstName() {
        return mFirstName;
    }

    public String lastName() {
        return mLastName;
    }

    public String email() {
        return mEmail;
    }

    @Nullable
    public static FacebookLoggedUser fromJson(@Nullable final JSONObject jsonObject) {
        FacebookLoggedUser user = null;
        if (jsonObject != null) {
            String firstName = null;
            String lastName = null;
            String email = null;
            try {
                firstName = jsonObject.getString(KEY_FACEBOOK_FIRST_NAME);
                lastName = jsonObject.getString(KEY_FACEBOOK_LAST_NAME);
                email = jsonObject.getString(KEY_FACEBOOK_EMAIL);
            } catch (JSONException ignored) {
            } finally {
                user = new FacebookLoggedUser(firstName, lastName, email);
            }
        }
        return user;
    }
}
