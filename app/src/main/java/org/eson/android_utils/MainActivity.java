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

        AppExecutors.getInstance().mainThread().executeDelay(new Runnable() {
            @Override
            public void run() {

            }
        },1_000);
        testGson2();
//        testGsonUtil();
    }

    private void testGson2() {

        //{"result":[["耳机套","82374.11887681902"],["耳机无线","86146.65959726802"],["耳机壳","29659.190473044397"],["耳机有线","31467.040049025163"],["耳机套 airpods","62803.631097124875"],["耳机壳 airpods","26503.839737582006"],["耳机保护套","56081.049394610076"],["耳机头戴式","23108.616998649763"],["耳机蓝牙","92494.7920789168"],["耳机入耳式","80630.11148359127"]]}
        String jsonString = "{\"result\":[[\"耳机套\",\"82374.11887681902\"],[\"耳机无线\",\"86146.65959726802\"],[\"耳机壳\",\"29659.190473044397\"],[\"耳机有线\",\"31467.040049025163\"],[\"耳机套 airpods\",\"62803.631097124875\"],[\"耳机壳 airpods\",\"26503.839737582006\"],[\"耳机保护套\",\"56081.049394610076\"],[\"耳机头戴式\",\"23108.616998649763\"],[\"耳机蓝牙\",\"92494.7920789168\"],[\"耳机入耳式\",\"80630.11148359127\"]]}";

//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            JSONArray jsonArray = jsonObject.getJSONArray("result");
//
//            ArrayList<HashMap<String,String >> hashMapArrayList = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
//                HashMap<String, String> hashMap = new HashMap<>();
//                String key = jsonArray1.getString(0);
//                String value = jsonArray1.getString(1);
//                hashMap.put(key,value);
//                hashMapArrayList.add(hashMap);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        JsonResult jsonResult = GsonUtil.fromGsonToClass(jsonString, JsonResult.class);
        LogUtils.e("jsonResult " + jsonResult.toString());

        List<List<String>> list = jsonResult.getResult();

        for (List<String> strings : list) {
            String key = strings.get(0);
            String value = strings.get(1);
            LogUtils.e("key :"+ key + " ; value :" + value);
        }

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
