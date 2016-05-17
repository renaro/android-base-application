package com.renarosantos.mybaseapplication.user.dao;

/**
 * Created by renarosantos on 17/05/16.
 */
public class UserDAO {
//        private final AppService mService;
//        private final Context mContext;
//
//        public UserDAO(@NonNull final Context context) {
//                mContext = context;
//                final UserClient client = new UserClient(context.getString(R.string.api_endpoint));
//                mService = client.service();
//        }
//
//        @WorkerThread
//        @Nullable
//        public LoginResponse login(@NonNull final String email, @NonNull final String password) {
//                try {
//                        return mService.login(new LoginRequest(email, password).parameters()).execute().body();
//                } catch (IOException e) {
//                        return null;
//                }
//        }
//
//        @WorkerThread
//        @Nullable
//        public LoginResponse registerUser(@NonNull final String email, @NonNull final String name,
//                                          @NonNull final String password) {
//                try {
//                        return mService.register(new RegisterRequest(email, name, password).parameters()).execute().body();
//                } catch (IOException e) {
//                        return null;
//                }
//        }
//
//        public LoginResponse loginWithFacebook(@NonNull final FacebookLoginRequest loginRequest) {
//                try {
//                        return mService.facebookLogin(loginRequest.parameters()).execute().body();
//                } catch (IOException e) {
//                        return null;
//                }
//        }
//
//        public String getPushToken() {
//                InstanceID instanceID = InstanceID.getInstance(mContext);
//                try {
//                        return instanceID.getToken(mContext.getString(R.string.gcm_sender_id),
//                                GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
//                } catch (IOException e) {
//                        return null;
//                }
//        }
//
//        public boolean registerToken(final String pushToken, final LoggedUser loggedUser) {
//                try {
//                        final PushTokenResponse body =
//                                mService.registerPushToken(loggedUser.userToken(), new PushTokenRequest(pushToken).parameters())
//                                        .execute()
//                                        .body();
//                        return body.status();
//                } catch (IOException e) {
//                        return false;
//                }
//        }
}
