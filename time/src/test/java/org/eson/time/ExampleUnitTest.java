package org.eson.time;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        String date1 = "2017-12-12";
        String date2 = "2017-12-20";

        int des = TimeUtils.getDaysBetween(date1, date2);

        int des2 = TimeUtils.compareDate(TimeFormatUtils.getDateByStr(date1, "yyyy-MM-dd"),
                TimeFormatUtils.getDateByStr(date2, "yyyy-MM-dd"));

        System.out.print("des = " + des + "; des2 = " + des2);
    }
}