package com.example.forcoms.sharedpreferences;

import android.content.Context;

public class UserDataPreference {
    private final SharedPreferencesHandler sharedPreferencesHandler;
    private final String KEY_IS_LOGGED_IN = "com.forcoms.is_logged_in";
    private final String KEY_LOGGED_IN_ID = "com.forcoms.logged_in_id";

    public UserDataPreference(Context context) {
        sharedPreferencesHandler = SharedPreferencesHandler.getSharedPreferencesHandler(context);
    }

    public boolean isLoggedIn() {
        return sharedPreferencesHandler.getBoolValueFromSP(KEY_IS_LOGGED_IN);
    }

    public int getLoggedId() {
        return sharedPreferencesHandler.getIntegerValueFromSP(KEY_LOGGED_IN_ID);
    }

    public void setIsLoggedIn(boolean value) {
        sharedPreferencesHandler.setBoolValueToSP(KEY_IS_LOGGED_IN, value);
    }

    public void setLoggedInId(int value) {
        sharedPreferencesHandler.setIntegerValueToSP(KEY_LOGGED_IN_ID, value);
    }
}
