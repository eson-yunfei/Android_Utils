package org.eson.android_utils;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shon.gson.GsonUtil;
import com.shon.permissions.OnPermissionCallback;
import com.shon.permissions.PermissionCheck;
import com.shon.permissions.PermissionRequest;

import org.eson.log.LogUtils;
import org.eson.toast.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.init("Main");
        LogUtils.e("test_log");

        testGsonUtil();
    }

    @Override
    protected void onResume() {
        super.onResume();
        testPermissions();
    }

    private void testPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (PermissionCheck.hasPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
               LogUtils.e("以获取相应权限，无需申请");
                return;
            }
            PermissionRequest request = new PermissionRequest.Builder(this)
                    .addPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .setCallback(new OnPermissionCallback() {
                        @Override
                        public void onRequest(boolean granted, @Nullable String[] reRequest) {
                            if (granted) {
                                ToastUtils.showShort(MainActivity.this, "申请权限成功");
                            } else {
                                ToastUtils.showShort(MainActivity.this, "申请权限失败");
                            }
                        }
                    })
                    .build();
            request.requestPermissions();
        }
    }

    private void testGsonUtil() {
        List<String> test = new ArrayList<>();
        test.add("llll");
        test.add("ewfe");
        test.add("efwe");
        test.add("grheh");


        String jsonString = GsonUtil.arryToGson(test);
        LogUtils.e("jsonString : " + jsonString);
        List<String> result = GsonUtil.fromGsonToArray(jsonString);
        if (result != null) {
            for (String s : result) {
                LogUtils.e("s : " + s);
            }
        }


        HashMap<String, String> map = new HashMap<>();
        map.put("张三", "10");
        map.put("老师", "20");
        map.put("问我", "50");
        map.put("方法", "100");

        String mapString = GsonUtil.arryToGson(map);
        LogUtils.e("mapString :" + mapString);

        HashMap mapResult = GsonUtil.fromGsonToClass(mapString, HashMap.class);
        for (Object o : mapResult.entrySet()) {
            LogUtils.e("o : " + o);
        }
    }


}
