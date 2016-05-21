package com.renarosantos.mybaseapplication.remote.request;

import android.os.Build;
import android.support.v4.util.ArrayMap;

/**
 * Created by renarosantos on 19/09/15.
 */
public class RegisterRequest {

    private static final String TYPE_ANDROID = "2";

    ArrayMap<String, String> mParameters;
    private final String NAME = "name";
    private final String BUSINESS_NAME = "business_name";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String DEVICE_TYPE = "device_type";
    private final String DEVICE_NAME = "device_name";

    public RegisterRequest(String pEmail, String pName, String pPassword) {
        mParameters = new ArrayMap<>();
        mParameters.put(NAME, pName);
        mParameters.put(EMAIL, pEmail);
        mParameters.put(PASSWORD, pPassword);
        mParameters.put(DEVICE_TYPE, TYPE_ANDROID);
        mParameters.put(DEVICE_NAME, android.os.Build.MANUFACTURER + " " + Build.MODEL);
        mParameters.put(BUSINESS_NAME, "-");
    }

    public ArrayMap<String, String> parameters() {
        return mParameters;
    }
}
