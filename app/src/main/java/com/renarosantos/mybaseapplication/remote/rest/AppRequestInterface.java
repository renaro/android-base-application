package com.renarosantos.mybaseapplication.remote.rest;

import android.support.v4.util.ArrayMap;

import com.renarosantos.mybaseapplication.remote.response.LoginResponse;
import com.renarosantos.mybaseapplication.remote.response.PushTokenResponse;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by renarosantos on 15/09/15.
 */
public interface AppRequestInterface {

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> login(@FieldMap ArrayMap<String, String> parameters);

    @FormUrlEncoded
    @POST("user/register")
    Call<LoginResponse> register(@FieldMap ArrayMap<String, String> parameters);

    @FormUrlEncoded
    @POST("user/login_facebook")
    Call<LoginResponse> facebookLogin(@FieldMap ArrayMap<String, String> parameters);

    @FormUrlEncoded
    @POST("user/update_push_notification")
    Call<PushTokenResponse> registerPushToken(@Header("X-auth-token") String key,
                                              @FieldMap ArrayMap<String, String> parameters);


}
