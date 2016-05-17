package com.renarosantos.mybaseapplication.mechanism;

import android.support.annotation.NonNull;

/**
 * Created by renarosantos on 17/05/16.
 */
public interface TaskExecutor {

    <T> void async(@NonNull final AppTask<T> task);
}
