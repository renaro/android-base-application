package com.renarosantos.mybaseapplication.mechanism;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by renarosantos on 16/03/16.
 */
public interface FacebookLogin {

    void start(@NonNull final Activity activity);

    void start(@NonNull final Fragment fragment);

    void onActivityResult(final int requestCode, final int resultCode, final Intent data,
                          @NonNull final Listener listener);

    interface Listener {

        void onFacebookLoginSuccess();

        void onFacebookLoginError();

        void onFacebookLoginCancel();
    }
}
