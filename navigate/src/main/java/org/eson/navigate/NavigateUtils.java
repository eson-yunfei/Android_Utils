package org.eson.navigate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by xiaoyunfei on 2017/12/28.
 */

public class NavigateUtils {

    public static final String PARCELABLE_EXTRA_KEY = "parcel_model";

    /**
     * 页面跳转，不销毁当前页面
     *
     * @param context     context
     * @param targetClazz targetClazz
     */
    public static void overlay(Context context,
                               Class<? extends Activity> targetClazz) {
        Intent intent = new Intent(context, targetClazz);
        context.startActivity(intent);
    }


    /**
     * 页面跳转，销毁当前页面
     *
     * @param context     context
     * @param targetClazz targetClazz
     */
    protected static void forward(Context context,
                                  Class<? extends Activity> targetClazz) {
        Intent intent = new Intent(context, targetClazz);
        context.startActivity(intent);
        if (isActivity(context)) {
            ((Activity) context).finish();
        }
    }

    /**
     * 带有参数的跳转，不销毁当前页面
     *
     * @param context     context
     * @param targetClazz targetClazz
     * @param params      params
     */
    protected static void overlay(Context context,
                                  Class<? extends Activity> targetClazz,
                                  Map<String, String> params) {
        Intent intent = new Intent(context, targetClazz);
        setIntentInfo(intent, params);
        context.startActivity(intent);
    }

    /**
     * 带有参数的跳转，销毁当前页面
     *
     * @param context     context
     * @param targetClazz targetClazz
     * @param params      params
     */
    protected static void forward(Context context,
                                  Class<? extends Activity> targetClazz,
                                  Map<String, String> params) {
        Intent intent = new Intent(context, targetClazz);
        setIntentInfo(intent, params);
        context.startActivity(intent);
        if (isActivity(context)) {
            ((Activity) context).finish();
        }
    }

    /**
     * 带有参数的跳转，不销毁当前页面
     *
     * @param context     context
     * @param targetClazz targetClazz
     * @param parcelable  parcelable
     */
    public static void overlay(Context context, Class<? extends Activity> targetClazz,
                               Parcelable parcelable) {
        Intent intent = new Intent(context, targetClazz);
        putParcelableExtra(intent, parcelable);
        context.startActivity(intent);
    }

    /**
     * 带有参数的跳转，销毁当前页面
     *
     * @param context     context
     * @param targetClazz targetClazz
     * @param parcelable  parcelable
     */
    public static void forward(Context context, Class<? extends Activity> targetClazz,
                               Parcelable parcelable) {
        Intent intent = new Intent(context, targetClazz);
        putParcelableExtra(intent, parcelable);
        context.startActivity(intent);
        if (isActivity(context)) {
            ((Activity) context).finish();
        }
    }

    /**
     * @param intent     intent
     * @param parcelable parcelable
     */
    private static void putParcelableExtra(Intent intent, Parcelable parcelable) {
        if (parcelable == null) {
            return;
        }
        intent.putExtra(PARCELABLE_EXTRA_KEY, parcelable);
    }


    /**
     * 设置传递的参数
     *
     * @param intent    intent
     * @param paramsMap params
     */
    private static void setIntentInfo(Intent intent,
                                      Map<String, String> paramsMap) {
        //解析封装参数
        if (paramsMap == null || paramsMap.size() == 0) {
            return;
        }
        for (String key : paramsMap.keySet()) {
            intent.putExtra(key, paramsMap.get(key));
        }
    }

    /**
     * 是否为 Activity
     *
     * @param context context
     * @return boolean
     */
    private static boolean isActivity(Context context) {

        return (context instanceof Activity);
    }

}
