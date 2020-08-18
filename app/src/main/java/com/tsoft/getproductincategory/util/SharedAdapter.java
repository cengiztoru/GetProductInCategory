package com.tsoft.getproductincategory.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by muratcan on 18.03.2019.
 */

public class SharedAdapter {

    private static final String PREF_NAME = "__Share";
    private static SharedAdapter ourInstance = null;
    private static Class classToInvestigate;
    private Context context = null;
    private SharedPreferences preferences = null;

    private SharedAdapter() {
    }

    public static void setInstance(Context context) {
        SharedAdapter share = new SharedAdapter();
        share.context = context;
        share.preferences = share.context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        ourInstance = share;
    }

    public static void put(String key, String val) {
        SharedPreferences.Editor prefEdit = ourInstance.preferences.edit();
        prefEdit.putString(key, val);
        prefEdit.apply();
    }

    public static void put(String key, Object val) {
        SharedPreferences.Editor prefEdit = ourInstance.preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(val);
        prefEdit.putString(key, json);
        prefEdit.apply();
    }

    public static void put(String key, Boolean val) {
        SharedPreferences.Editor prefEdit = ourInstance.preferences.edit();
        prefEdit.putBoolean(key, val);
        prefEdit.apply();
    }

    public static void put(String key, int val) {
        SharedPreferences.Editor prefEdit = ourInstance.preferences.edit();
        prefEdit.putInt(key, val);
        prefEdit.apply();
    }

    public static void put(String key, Float val) {
        SharedPreferences.Editor prefEdit = ourInstance.preferences.edit();
        prefEdit.putFloat(key, val);
        prefEdit.apply();
    }

    public static void put(String key, Long val) {
        SharedPreferences.Editor prefEdit = ourInstance.preferences.edit();
        prefEdit.putLong(key, val);
        prefEdit.apply();
    }

    public static String getString(String key, String defaultValue) {

        if (ourInstance.preferences.getString(key, defaultValue) != null) {
            return ourInstance.preferences.getString(key, defaultValue);
        } else {
            return defaultValue;
        }

    }

    public static Object getObject(String key, String defaultValue, Class sClassName) {
        Gson gson = new Gson();
        String json = ourInstance.preferences.getString(key, defaultValue);
        return gson.fromJson(json, sClassName);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return ourInstance.preferences.getBoolean(key, defaultValue);
    }

    public static Float getFloat(String key, float defaultValue) {
        return ourInstance.preferences.getFloat(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return ourInstance.preferences.getInt(key, defaultValue);
    }

    public static Long getLong(String key, long defaultValue) {
        return ourInstance.preferences.getLong(key, defaultValue);
    }

    public static void remove(String key) {
        ourInstance.preferences.edit().remove(key).apply();
        ourInstance.preferences.edit().apply();
    }

    public static void removeAll() {
        ourInstance.preferences.edit().clear().apply();
    }

}