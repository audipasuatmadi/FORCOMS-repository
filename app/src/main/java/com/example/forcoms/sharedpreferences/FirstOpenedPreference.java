package com.example.forcoms.sharedpreferences;

import android.content.Context;

public class FirstOpenedPreference {
    private SharedPreferencesHandler sharedPreferencesHandler;
    private final String FIRST_OPENED_KEY = "is_first_opened";

    public FirstOpenedPreference(Context context) {
        sharedPreferencesHandler = SharedPreferencesHandler.getSharedPreferencesHandler(context);
    }

    public boolean isFirstOpened() {
        return this.sharedPreferencesHandler.getBoolValueFromSP(FIRST_OPENED_KEY);
    }

    public void setIsFirstOpened(boolean value) {
        this.sharedPreferencesHandler.setBoolValueToSP(FIRST_OPENED_KEY, value);
    }

}
