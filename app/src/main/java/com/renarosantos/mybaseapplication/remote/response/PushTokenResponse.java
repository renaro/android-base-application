package com.renarosantos.mybaseapplication.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renarosantos on 06/03/16.
 */

public class PushTokenResponse {

    @SerializedName("status")
    private boolean status;

    public boolean status() {
        return status;
    }

}
