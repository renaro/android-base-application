package com.renarosantos.mybaseapplication.remote.rest;

import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by renarosantos on 15/09/15.
 */
public class AppClient {

    private final AppRequestInterface mService;

    public AppClient(@NonNull final String endPoint) {
        final OkHttpClient httpClient = new OkHttpClient();

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);

        Retrofit.Builder restBuilder = new Retrofit.Builder();
        restBuilder.baseUrl(endPoint);
        restBuilder.client(httpClient);
        restBuilder.addConverterFactory(GsonConverterFactory.create());

        final Retrofit mAdapter = restBuilder.build();
        mService = mAdapter.create(AppRequestInterface.class);
    }

    public AppRequestInterface service() {
        return mService;
    }
}
