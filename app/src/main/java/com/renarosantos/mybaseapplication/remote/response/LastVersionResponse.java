package com.renarosantos.mybaseapplication.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renarosantos on 14/03/16.
 */
public class LastVersionResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("version")
    private long version;

    public long version() {
        return version;
    }

    public boolean isStatus() {
        return status;
    }
}
