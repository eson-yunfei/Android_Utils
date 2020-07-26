package org.eson.android_utils;

import android.content.Context;

import androidx.annotation.NonNull;

import org.eson.sharepre.BasePreferences;

/**
 * Created by xiaoyunfei on 2017/12/28.
 */

public class TestShare extends BasePreferences {

    public TestShare(@NonNull Context context) {
        super(context);
    }

    @Override
    protected String getPreferencesName() {
        return "test_share";
    }
}
