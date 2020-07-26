package org.eson.sharepre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by xiaoyunfei on 2017/12/28.
 * <p>
 * SharedPreferences 工具类，需要继承
 */
public abstract class BasePreferences {
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public BasePreferences(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(getPreferencesName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    protected abstract String getPreferencesName();

    /**
     * @param key   key
     * @param value String value
     */
    protected void putString(@NonNull String key, @Nullable String value) {
        editor.putString(key, value).apply();
    }

    /**
     * @param key          key
     * @param defaultValue String value
     * @return String
     */
    protected String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * @param key   key
     * @param value int
     */
    protected void putInt(@NonNull String key, int value) {
        editor.putInt(key, value).apply();
    }

    /**
     * @param key          key
     * @param defaultValue value
     * @return int
     */
    protected int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * @param key   key
     * @param value long value
     */
    protected void putLong(@NonNull String key, long value) {
        editor.putLong(key, value).apply();
    }

    /**
     * @param key          key
     * @param defaultValue value
     * @return long
     */
    protected long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * @param key   key
     * @param value float value
     */
    protected void putFloat(@NonNull String key, float value) {
        editor.putFloat(key, value).apply();
    }

    /**
     * @param key          key
     * @param defaultValue value
     * @return float
     */
    protected float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * @param key   key
     * @param value boolean value
     */
    protected void putBoolean(@NonNull String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    /**
     * @param key          key
     * @param defaultValue value
     * @return boolean
     */
    protected boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * @param key key
     */
    protected void remove(@NonNull String key) {
        if (sharedPreferences != null && sharedPreferences.contains(key)) {
            editor.remove(key).apply();
        }
    }

    /**
     *
     */
    protected void clear() {
        editor.clear().apply();
    }


}
