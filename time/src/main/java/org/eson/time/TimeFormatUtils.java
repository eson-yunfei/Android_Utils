package org.eson.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaoyunfei on 2017/12/27.
 */

public class TimeFormatUtils {

    private static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 获取当时间的 时间戳
     *
     * @return 时间戳
     */
    public static int getUnix() {
        Date date = new Date();
        return getUnixByDate(date);
    }

    /**
     * 将日期格式的字符串转为时间戳
     *
     * @param formatTime : 需要转换的时间字符串
     * @param format     ： 字符串的格式
     * @return 时间戳
     */
    public static int getUnixByStr(String formatTime, String format) {
        int re_time = 0;
        try {
            Date d = getFormat(format).parse(formatTime);
            re_time = getUnixByDate(d);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 获取指定时间的 时间戳
     *
     * @param date date
     * @return 时间戳
     */
    public static int getUnixByDate(Date date) {
        int time = (int) ((date.getTime()) / 1000);
        return time;
    }

    /**
     * 获取当前时间的字符串
     *
     * @param format :字符串的格式
     * @return String
     */
    public static String getNow(String format) {
        Date currentTime = new Date();
        return getStrByDate(currentTime, format);
    }

    /**
     * 把时间戳转换成字符串
     *
     * @param unixTime : 需要转换的时间戳
     * @param format   : 转换过来的时间格式
     * @return String
     */
    public static String getStrByUnix(int unixTime, String format) {
        Date date = getDateByUnix(unixTime);
        return getStrByDate(date, format);
    }

    /**
     * 把一种格式的时间字符串转换成另一种格式的字符串
     *
     * @param time         需要转换的字符串
     * @param beforeFormat 转换前的格式
     * @param toFormat     转换后的格式
     * @return
     */
    public static String stringToString(String time, String beforeFormat,
                                        String toFormat) {
        Date date = getDateByStr(time, beforeFormat);
        return getStrByDate(date, toFormat);
    }

    /**
     * 日期格式化为字符串
     *
     * @param date   系统时间
     * @param format 格式化字符串，如"yyyy-MM-dd HH:mm:ss"
     * @return 格式化后的时间串.
     */
    public static String getStrByDate(Date date, String format) {
        return getFormat(format).format(date);
    }


    /**
     * 得到现在时间
     *
     * @return Date
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 通过时间戳得到date
     *
     * @param unixTime 时间戳
     * @return Date
     */
    public static Date getDateByUnix(int unixTime) {
        return new Date(unixTime * 1000L);
    }


    /**
     * 字符串转换成统时间.
     *
     * @param formatStr 协议中的时间串.
     * @param format    : 字符格式
     * @return ： 时间
     */
    public static Date getDateByStr(String formatStr, String format) {
        Date dt = new Date();
        try {
            dt = getFormat(format).parse(formatStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

}
