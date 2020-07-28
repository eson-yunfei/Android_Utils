package org.eson.android_utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Auth : xiao_yun_fei
 * Date : 2020/7/28 22:17
 * Package name : org.eson.android_utils
 * Des :
 */
public class AppExecutors {

    private final Executor mDiskIO;

    private final Executor mNetworkIO;

    private final MainThreadExecutor mMainThread;

    private static AppExecutors appExecutors;

    public static AppExecutors getInstance() {
        if (appExecutors == null) {
            synchronized (AppExecutors.class) {
                if (appExecutors == null) {
                    appExecutors = new AppExecutors();
                }
            }
        }
        return appExecutors;
    }

    private AppExecutors(Executor diskIO, Executor networkIO, MainThreadExecutor mainThread) {
        this.mDiskIO = diskIO;
        this.mNetworkIO = networkIO;
        this.mMainThread = mainThread;
    }

    private AppExecutors() {
        this(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                new MainThreadExecutor());
    }

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor networkIO() {
        return mNetworkIO;
    }

    public MainThreadExecutor mainThread() {
        return mMainThread;
    }

    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler;

        private MainThreadExecutor(){
            mainThreadHandler = new Handler(Looper.getMainLooper());
        }
        public void executeDelay(final Runnable command, long delayMillis) {
            mainThreadHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    execute(command);
                }
            }, delayMillis);
        }

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
