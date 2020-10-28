package com.example.forcoms.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHandler {
    private SharedPreferences sharedPreferences;
    private SharedPreferencesHandler INSTANCE;
    private static final String GLOBAL_PREF_NAME = "com.example.forcoms.main_shared_preferences";


    public SharedPreferencesHandler getSharedPreferencesHandler(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPreferencesHandler(context);
        }
        return INSTANCE;
    }

    public void setStringValueToSP(String key, String value) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(key, value).apply();
    }

    public void setBoolValueToSP(String key, boolean value) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putBoolean(key, value).apply();
    }

    public String getStringValueFromSP(String key) {
        return sharedPreferences.getString(key, "null");
    }

    public boolean getBoolValueFromSP(String key) {
        return sharedPreferences.getBoolean(key, true);
    }

    private SharedPreferencesHandler(Context context) {
        sharedPreferences = context.getSharedPreferences(GLOBAL_PREF_NAME, Context.MODE_PRIVATE);
    }
}
