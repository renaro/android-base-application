package com.renarosantos.mybaseapplication.remote.request;

import android.os.Build;
import android.support.v4.util.ArrayMap;

/**
 * Created by renarosantos on 15/09/15.
 */
public class LoginRequest {

    private static final String TYPE_ANDROID = "2";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String DEVICE_TYPE = "device_type";
    private final String DEVICE_NAME = "device_name";

    ArrayMap<String, String> mParameters;

    public LoginRequest(String pEmail, String pPassword) {
        mParameters = new ArrayMap<>();
        mParameters.put(EMAIL, pEmail);
        mParameters.put(PASSWORD, pPassword);
        mParameters.put(DEVICE_TYPE, TYPE_ANDROID);
        mParameters.put(DEVICE_NAME, android.os.Build.MANUFACTURER + " " + Build.MODEL);
    }

    public ArrayMap<String, String> parameters() {
        return mParameters;
    }
}
