package org.eson.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * 重新修改的Toast工具类
 * <p>
 * 1、调用时不需要再传入 context 对象
 * 2、新增在 其他线程 弹出 toast 的功能
 */
public class ToastX {
    @SuppressLint("StaticFieldLeak")
    private static ToastX toastX;


    public static void init(Context context) {
        if (toastX == null) {
            synchronized (ToastX.class) {
                if (toastX == null) {
                    toastX = new ToastX(context);
                }
            }
        }
    }

    public static ToastX getInstance() {
        if (toastX == null) {
            throw new NullPointerException("You Must init ToastX first");
        }
        return toastX;
    }

    private final Context context;
    private final Handler mainTread;
    private Toast toast;

    private ToastX(Context context) {
        this.context = context;
        mainTread = new Handler(Looper.getMainLooper());
    }


    /**
     * @param msg 资源id
     */
    @SuppressWarnings("unused")
    public void showLong(@StringRes int msg) {
        showLong(context.getString(msg));
    }

    /**
     * @param msg 资源id
     */
    @SuppressWarnings("unused")
    public void showShort(@StringRes int msg) {
        showShort(context.getString(msg));
    }

    /**
     * @param msg msg
     */
    public void showLong(String msg) {
        showToast(Toast.LENGTH_LONG, msg);
    }

    /**
     * @param msg msg
     */
    public void showShort(String msg) {
        showToast(Toast.LENGTH_SHORT, msg);
    }


    /**
     * 子线程显示 toast
     * @param msg 资源id
     */
    @SuppressWarnings("unused")
    public void showLongSafely(final @StringRes int msg) {
        showLongSafely(context.getString(msg));
    }

    /**
     * 子线程显示 toast
     * @param msg 资源id
     */
    @SuppressWarnings("unused")
    public void showShortSafely(final @StringRes int msg) {
        showShortSafely(context.getString(msg));
    }


    /**
     * 子线程显示 toast
     * @param msg msg
     */
    public void showLongSafely(final String msg) {
        mainTread.post(new Runnable() {
            @Override
            public void run() {
                showToast(Toast.LENGTH_LONG, msg);
            }
        });

    }

    /**
     * 子线程显示 toast
     *
     * @param msg msg
     */
    public void showShortSafely(final String msg) {
        mainTread.post(new Runnable() {
            @Override
            public void run() {
                showToast(Toast.LENGTH_SHORT, msg);
            }
        });

    }


    /**
     * 显示 toast
     *
     * @param duration 时间
     * @param message  msg
     */
    @SuppressLint("ShowToast")
    private void showToast(int duration, String message) {
        // 如果之前的还在显示，先取消
        if (toast != null) {
            toast.setText(message);
            toast.cancel();
        }
        toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
