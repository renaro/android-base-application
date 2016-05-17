package com.renarosantos.mybaseapplication.mechanism;

import android.support.annotation.Nullable;

/**
 * Created by renarosantos on 17/05/16.
 */
public interface AppTask<T> {

    T execute();

    void onPostExecute(@Nullable final T result);
}
