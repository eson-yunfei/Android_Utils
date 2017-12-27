package org.eson.android_utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.eson.log.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.init("Main");
        LogUtils.e("test_log");
    }
}
