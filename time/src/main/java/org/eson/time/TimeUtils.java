package org.eson.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaoyunfei on 2017/12/27.
 */

public class TimeUtils {


    /**
     * 计算 两个日期相差的 天数
     *
     * @param small  较小的日期
     * @param big    较大的日期
     * @param format 日期格式
     * @return 天数
     * @throws ParseException
     */
    public static int getDaysBetween(String small, String big, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date smallDate = sdf.parse(small);
        Date bigDate = sdf.parse(big);
        return getDaysBetween(smallDate, bigDate);
    }


    /**
     * 计算 两个日期相差的 天数
     *
     * @param smallDate 较小的时间
     * @param bigDate   较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int getDaysBetween(Date smallDate, Date bigDate) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(smallDate);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(bigDate);

        return getDaysBetween(cal1, cal2);
    }

    /**
     * 计算 两个日期相差的 天数
     *
     * @param cl1 较小的时间
     * @param cl2 较大的时间
     * @return 相差天数
     */
    public static int getDaysBetween(Calendar cl1, Calendar cl2) {

        long time1 = cl1.getTimeInMillis();
        long time2 = cl2.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param time1
     * @param time2
     * @param format
     * @return
     */
    public static boolean isSameDay(String time1, String time2, String format) {
        int des = 0;
        try {
            des = getDaysBetween(time1, time2, format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return des == 0;
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        int des = getDaysBetween(date1, date2);
        return des == 0;
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param cl1
     * @param cl2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean isSameDay(Calendar cl1, Calendar cl2) {

        int result = cl1.compareTo(cl2);
        return result == 0;
    }


    /**
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameYear(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        return isSameYear(cal1, cal2);
    }

    /**
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameYear(Calendar cal1, Calendar cal2) {
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
    }

    /**
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return isSameMonth(cal1, cal2);
    }


    /**
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
            if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                return true;
            }
        }
        return false;
    }


    private static final int SELECT_DAY = 0, SELECT_WEEK = 1, SELECT_MONTH = 2, SELECT_YEAR = 3;


    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && Calendar.DECEMBER == cal2.get(Calendar.MONTH)) {

            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && Calendar.DECEMBER == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }


    /**
     * 获取 与 指定日期 相差 指定类型、指定数据的一个日期
     *
     * @param calendar 指定的日期
     * @param isBefore true 向前即昨天等
     * @param type     年、月、日、周
     * @param value    具体的数据
     * @return
     */
    public static Calendar getCalendar(Calendar calendar, boolean isBefore, int type, int value) {
        if (isBefore) {
            value = -1 * value;
        }
        switch (type) {
            case SELECT_DAY:
                calendar.add(Calendar.DAY_OF_YEAR, value);
                break;
            case SELECT_MONTH:
                calendar.add(Calendar.MONTH, value);
                break;
            case SELECT_YEAR:
                calendar.add(Calendar.YEAR, value);
                break;
            case SELECT_WEEK:
                calendar.add(Calendar.WEEK_OF_YEAR, value);
                break;
            default:
                break;
        }
        return calendar;
    }

}
