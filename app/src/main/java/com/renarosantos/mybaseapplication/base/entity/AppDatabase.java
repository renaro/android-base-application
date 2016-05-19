package com.renarosantos.mybaseapplication.base.entity;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by renarosantos on 18/05/16.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase{

    public static final String NAME = "databaseName";
    public static final int VERSION = 1;

}
