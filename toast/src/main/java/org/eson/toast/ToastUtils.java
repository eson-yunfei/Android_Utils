package org.eson.toast;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by xiaoyunfei on 2017/12/27.
 * <p>
 * 防止弹出多个 Toast 且长时间不消失
 */

public class ToastUtils {
    private static Toast toast;

    /**
     * @param context
     * @param content
     */
    public static void showShort(Context context, String content) {
        show(context, content, Toast.LENGTH_SHORT);
    }

    /**
     * @param context
     * @param content
     */
    public static void showLong(Context context, String content) {
        show(context, content, Toast.LENGTH_LONG);
    }

    /**
     * @param context
     * @param res
     */
    public static void showShort(Context context, @StringRes int res) {
        show(context, getString(context, res), Toast.LENGTH_SHORT);
    }

    /**
     * @param context
     * @param res
     */
    public static void showLong(Context context, @StringRes int res) {
        show(context, getString(context, res), Toast.LENGTH_LONG);
    }

    /**
     *
     * @param context
     * @param content
     * @param duration
     */
    public static void show(Context context, String content, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, content, duration);
        } else {
            toast.setText(content);
        }
        toast.show();
    }


    /**
     * 隐藏toast
     */
    public static void hideToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
    /**
     * 根据 StringRes 获取相应的内容
     *
     * @param context
     * @param res
     * @return
     */
    private static String getString(Context context, @StringRes int res) {
        if (res == 0) {
            return "";
        }
        return context.getString(res);
    }
}
