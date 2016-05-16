package com.renarosantos.mybaseapplication.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by renarosantos on 16/05/16.
 */
public abstract class BasePresenter {

        @NonNull
        public static BasePresenter nullPresenter(@NonNull final Context context) {
            return new BasePresenter() {};
        }

        @CallSuper
        public void onCreate(@Nullable final Bundle savedInstanceState) {}


        @CallSuper
        public void onResume() {

        }

        @CallSuper
        public void onPause() {

        }

        @CallSuper
        public void onStop() {}

        @CallSuper
        public void onSaveInstanceState(@NonNull final Bundle outState) {}

        @CallSuper
        public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {}

        @CallSuper
        public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                               @NonNull final int[] grantResults) {}

    }
