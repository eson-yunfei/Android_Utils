package org.eson.android_utils;

import android.app.Application;

import org.eson.slog.SLogConfig;
import org.eson.slog.SLogManager;
import org.eson.toast.ToastX;

public class AndroidUtils extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ToastX.init(this);
        SLogConfig sLogConfig = new SLogConfig();
        sLogConfig.setStackTraceDepth(1);
        sLogConfig.setIncludeThread(true);
        sLogConfig.setSaveFile(true,getExternalFilesDir("log").getPath(),5*24*60*60*1000);
        SLogManager.Companion.getSLogManager().setConfig(sLogConfig);

    }
}
