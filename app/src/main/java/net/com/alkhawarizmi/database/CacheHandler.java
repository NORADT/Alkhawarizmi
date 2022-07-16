package net.com.alkhawarizmi.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import net.com.alkhawarizmi.models.AppUser;

import java.lang.reflect.Type;

public class CacheHandler {
    private SharedPreferences mSharedPreferences;

    public CacheHandler(Context context) {
        mSharedPreferences = context.getSharedPreferences("alkhawarizmi", Context.MODE_PRIVATE);
    }

    public void setLoggedIn(boolean login) {
        mSharedPreferences.edit().putBoolean("Login", login).apply();
    }

    public Boolean isLoggedIn() {
        return mSharedPreferences.getBoolean("Login", false);
    }


    public void saveCurrentUser(AppUser user) {
        String json = new Gson().toJson(user);
        mSharedPreferences.edit().putString("UserObject", json).apply();
    }

    public AppUser loadCurrentUser() {
        String json = mSharedPreferences.getString("UserObject", null);
        Type type = new TypeToken<AppUser>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    public void clearData() {
        mSharedPreferences.edit().putBoolean("Login", false).apply();
        mSharedPreferences.edit().putString("UserObject", null).apply();
    }
}
