package org.eson.sharepre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

/**
 * Created by xiaoyunfei on 2017/12/28.
 * <p>
 * SharedPreferences 工具类，需要继承
 */

public abstract class AbsSharePrefer {
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public AbsSharePrefer(@NonNull Context context, @NonNull String shareName) {
        sharedPreferences = context.getSharedPreferences(shareName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * @param key
     * @param value
     */
    protected void putString(@NonNull String key, @Nullable String value) {
        editor.putString(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    protected String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    protected void putInt(@NonNull String key, int value) {
        editor.putInt(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    protected int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    protected void putLong(@NonNull String key, long value) {
        editor.putLong(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    protected long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    protected void putFloat(@NonNull String key, float value) {
        editor.putFloat(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    protected float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    protected void putBoolean(@NonNull String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    protected boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * @param key
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
