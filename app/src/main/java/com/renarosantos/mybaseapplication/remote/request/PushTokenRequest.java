package com.renarosantos.mybaseapplication.remote.request;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

/**
 * Created by renarosantos on 15/09/15.
 */
public class PushTokenRequest {

    ArrayMap<String, String> mParameters;
    private final String PUSH_TOKEN = "push_notification";

    public PushTokenRequest(@NonNull final String token) {
        mParameters = new ArrayMap<>();
        mParameters.put(PUSH_TOKEN, token);
    }

    public ArrayMap<String, String> parameters() {
        return mParameters;
    }
}
