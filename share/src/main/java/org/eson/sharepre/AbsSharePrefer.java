package org.eson.sharepre;

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
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AbsSharePrefer(@NonNull Context context, @NonNull String shareName) {
        sharedPreferences = context.getSharedPreferences(shareName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    protected void putString(@NonNull String key, @Nullable String value) {
        editor.putString(key, value).apply();
    }


    protected void putInt(@NonNull String key, int value) {
        editor.putInt(key, value).apply();
    }

    protected void putLong(@NonNull String key, long value) {
        editor.putLong(key, value).apply();
    }

    protected void putFloat(@NonNull String key, float value) {
        editor.putFloat(key, value).apply();
    }

    protected void putBoolean(@NonNull String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    protected void remove(@NonNull String key) {
        if (sharedPreferences != null && sharedPreferences.contains(key)) {
            editor.remove(key).apply();
        }
    }

    protected void clear() {
        editor.clear().apply();
    }


}
