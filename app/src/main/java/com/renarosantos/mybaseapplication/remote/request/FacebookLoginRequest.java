package com.renarosantos.mybaseapplication.remote.request;

import android.os.Build;
import android.support.v4.util.ArrayMap;

/**
 * Created by renarosantos on 05/03/16.
 */
public class FacebookLoginRequest {

    ArrayMap<String, String> mParameters;
    private static final String TYPE_ANDROID = "2";
    private final String FACEBOOK_TOKEN = "facebook_token";
    private final String DEVICE_TYPE = "device_type";
    private final String DEVICE_NAME = "device_name";

    private String facebookToken;
    private String deviceType;
    private String deviceName;

    public FacebookLoginRequest(final String facebookToken) {
        this.facebookToken = facebookToken;
        this.deviceType = TYPE_ANDROID;
        deviceName = android.os.Build.MANUFACTURER +" "+ Build.MODEL;
        setupParameters();
    }

    private void setupParameters() {
        mParameters = new ArrayMap<>();
        mParameters.put(FACEBOOK_TOKEN, facebookToken);
        mParameters.put(DEVICE_TYPE, deviceType);
        mParameters.put(DEVICE_NAME, deviceName);
    }

    public ArrayMap<String, String> parameters() {
        return mParameters;
    }
}
