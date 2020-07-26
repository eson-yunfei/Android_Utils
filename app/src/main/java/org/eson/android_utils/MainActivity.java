package org.eson.android_utils;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shon.gson.GsonUtil;

import org.eson.log.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.init("Main");
        LogUtils.e("test_log");

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

        HashMap<String,String> mapResult = GsonUtil.fromGsonToClass(mapString,HashMap.class);
        for (Object o : mapResult.entrySet()) {
            LogUtils.e("o : " + o);
        }
    }

}
