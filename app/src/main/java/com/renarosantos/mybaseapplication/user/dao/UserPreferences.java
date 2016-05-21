package com.renarosantos.mybaseapplication.user.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.login.LoginManager;

/**
 * Created by renarosantos on 17/05/16.
 */
public class UserPreferences {

    private static final String PREF_NAME = "SessionPrefs";
    private static final String IS_NEGATIVE_STOCK_ALLOWED = "negativeStock";
    private static final String USER_PUSH_TOKEN = "USER_PUSH_TOKEN";
    private static final String USER_TOKEN = "com.srnegocio.app.user_token";
    private static final String USER_ID = "com.srnegocio.app.user_id";
    private static final String FACEBOOK_ID = "FACEBOOK_ID";
    private static final String FACEBOOK_TOKEN = "FACEBOOK_TOKEN";
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String EMAIL = "EMAIL";
    private static final String IS_PUSH_TOKEN_REGISTERED = "IS_PUSH_TOKEN_REGISTERED";
    private final SharedPreferences mSharedPreferences;
    private final Context mContext;

    public UserPreferences(@NonNull final Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isNegativeStockAllowed() {
        return mSharedPreferences.getBoolean(IS_NEGATIVE_STOCK_ALLOWED, false);
    }

    public void setNegativeStockAllowed(final boolean isAllowed) {
        final SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_NEGATIVE_STOCK_ALLOWED, isAllowed);
        editor.apply();
    }

    public void setLoggedUser(@NonNull final LoggedUser loggedUser) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_TOKEN, loggedUser.userToken());
        editor.putString(USER_ID, loggedUser.userId());
        editor.commit();
    }

    @Nullable
    public LoggedUser getLoggedUser() {
        final String token = mSharedPreferences.getString(USER_TOKEN, null);
        final String id = mSharedPreferences.getString(USER_ID, null);
        if (token == null || id == null) {
            return null;
        } else {
            return new LoggedUser(token, id);
        }
    }

    public void saveFacebookUserCredentials(@NonNull final FacebookUserCredentials facebookUserCredentials) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(FACEBOOK_ID, facebookUserCredentials.facebookUserId());
        editor.putString(FACEBOOK_TOKEN, facebookUserCredentials.token());
        editor.commit();
    }

    public void saveFacebookProfileData(final String firstName, final String lastName, final String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(FIRST_NAME, firstName);
        editor.putString(LAST_NAME, lastName);
        editor.putString(EMAIL, email);
        editor.commit();
    }

    @Nullable
    public FacebookUserCredentials getFacebookUserCredentials() {
        final String facebookId = mSharedPreferences.getString(FACEBOOK_ID, null);
        final String facebookToken = mSharedPreferences.getString(FACEBOOK_TOKEN, null);
        if (!TextUtils.isEmpty(facebookId) && !TextUtils.isEmpty(facebookToken)) {
            return new FacebookUserCredentials(facebookId, facebookToken);
        } else {
            return null;
        }
    }

    public boolean isPushTokenRegistered() {
        return mSharedPreferences.getBoolean(IS_PUSH_TOKEN_REGISTERED, false);
    }

    public void registerPushToken(@NonNull final String pushToken) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_PUSH_TOKEN, pushToken);
        editor.putBoolean(IS_PUSH_TOKEN_REGISTERED, true);
        editor.commit();
    }

    public String getPushToken() {
        return mSharedPreferences.getString(USER_PUSH_TOKEN, null);
    }

    public void logout() {
        deleteDatabase();
        LoginManager.getInstance().logOut();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void deleteDatabase() {
//            Delete.tables(SyncEntity.class, ProductEntity.class, ServiceEntity.class, SaleEntity.class,
//                    SaleItemEntity.class, ParcelEntity.class, LocalContactEntity.class);
    }


}
