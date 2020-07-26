package com.shon.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Auth : xiao_yun_fei
 * Date : 2020/7/26 10:18
 * Package name : com.shon.gson
 * Des :
 */
public class GsonUtil {

    private static Gson gson;

    private static Gson getGson() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static String objectToGson(Object o) {
        return getGson().toJson(o);
    }

    public static <T> String arryToGson(T t) {
        Type type = new TypeToken<T>() {
        }.getType();
        return getGson().toJson(t, type);
    }

    public static <T> T fromGsonToClass(String json, Class<T> classOfT) {

        return getGson().fromJson(json, classOfT);
    }

    public static <T> T fromGsonToArray(String json) {
        Type type = new TypeToken<T>() {
        }.getType();
        return getGson().fromJson(json, type);
    }


}
