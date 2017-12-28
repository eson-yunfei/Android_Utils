package org.eson.android_utils;

import android.content.Context;
import android.support.annotation.NonNull;

import org.eson.sharepre.AbsSharePrefer;

/**
 * Created by xiaoyunfei on 2017/12/28.
 */

public class TestShare extends AbsSharePrefer {
    private static String name = "test_share";

    public TestShare(@NonNull Context context) {
        super(context, name);

    }
}
