package com.renarosantos.mybaseapplication.remote.request;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

/**
 * Created by renarosantos on 05/03/16.
 */

public class SyncDownRequest {

    private static final String VERSION = "version";
    ArrayMap<String, String> mParameters;


    public SyncDownRequest(@NonNull final long lastUpdate) {
        mParameters = new ArrayMap<>();
        mParameters.put(VERSION, String.valueOf(lastUpdate));
    }

    public ArrayMap<String, String> parameters() {
        return mParameters;
    }
}
