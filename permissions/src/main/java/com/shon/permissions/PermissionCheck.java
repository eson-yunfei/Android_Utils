package com.shon.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Auth : xiao_yun_fei
 * Date : 2020/7/26 11:27
 * Package name : com.shon.permissions
 * Des :
 */
public class PermissionCheck {

    public static boolean hasPermission(Context context, String permission) {
        if (context == null || TextUtils.isEmpty(permission)) {
            return true;
        }
        int result = ContextCompat.checkSelfPermission(context, permission);
        return PackageManager.PERMISSION_GRANTED == result;
    }

    public static void hasPerMissions(Context context, String[] permissions, @NonNull OnPermissionCallback onPermissionCallback) {

        if (context == null || permissions == null || permissions.length == 0) {
            onPermissionCallback.onRequest(true, null);
            return;
        }

        List<String> needRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (TextUtils.isEmpty(permission)) {
                continue;
            }
            if (hasPermission(context, permission)) {
                continue;
            }
            needRequest.add(permission);

        }
        if (needRequest.size() == 0) {
            onPermissionCallback.onRequest(true,null);
        }else {
            String[] reRequestPermissions = new String[needRequest.size()];
            onPermissionCallback.onRequest(false,needRequest.toArray(reRequestPermissions));
        }
    }
}
