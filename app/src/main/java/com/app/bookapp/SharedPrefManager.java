package com.app.bookapp;

import android.content.Context;
import android.content.SharedPreferences;

    public class SharedPrefManager {
        private static final String SHARED_PREF_NAME = "MySharedPref";
        private static final String KEY_USERNAME = "username";

        private static SharedPrefManager instance;
        private SharedPreferences sharedPreferences;

        private SharedPrefManager(Context context) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (instance == null) {
                instance = new SharedPrefManager(context);
            }
            return instance;
        }

        public void setUsername(String username) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USERNAME, username);
            editor.apply();
        }

        public String getUsername() {
            return sharedPreferences.getString(KEY_USERNAME, null);
        }

        public void clear() {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }

