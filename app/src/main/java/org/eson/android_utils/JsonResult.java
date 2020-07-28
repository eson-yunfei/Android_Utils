package org.eson.android_utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auth : xiao_yun_fei
 * Date : 2020/7/26 15:24
 * Package name : org.eson.android_utils
 * Des :
 */
public class JsonResult {
    private List<List<String>> result;

    public List<List<String>> getResult() {
        return result;
    }

    public void setResult(List<List<String>> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "result=" + result +
                '}';
    }


}
