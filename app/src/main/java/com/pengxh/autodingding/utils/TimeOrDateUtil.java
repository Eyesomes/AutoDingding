package com.pengxh.autodingding.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.pengxh.app.multilib.widget.EasyToast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: Pengxh
 * @email: 290677893@qq.com
 * @description: TODO
 * @date: 2020/1/17 12:57
 */
@SuppressLint("SimpleDateFormat")
public class TimeOrDateUtil {
    private static final String TAG = "TimeOrDateUtil";
    private static final SimpleDateFormat allDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 时间戳转日期
     */
    public static String rTimestampToDate(long millSeconds) {
        return dateFormat.format(new Date(millSeconds));
    }

    /**
     * 时间戳转时间
     */
    public static String timestampToTime(long millSeconds) {
        return timeFormat.format(new Date(millSeconds));
    }

    /**
     * 时间戳转详细日期时间
     */
    public static String timestampToDate(long millSeconds) {
        return allDateFormat.format(new Date(millSeconds));
    }

    /**
     * 计算时间差
     *
     * @param fixedTime 结束时间
     */
    public static long deltaTime(long fixedTime) {
        long currentTime = (System.currentTimeMillis() / 1000);
        if (fixedTime > currentTime) {
            return (fixedTime - currentTime);
        } else {
            EasyToast.showToast("时间设置异常", EasyToast.WARING);
        }
        return 0L;
    }

    /**
     * 时间转时间戳
     */
    public static long dateToTimestamp(String time) {
        try {
            return allDateFormat.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取当前日期
     */
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();
        return rTimestampToDate(today);
    }

    public static String getRandomTime(long minMillis, long maxMillis) {
        long nextLong = ThreadLocalRandom.current().nextLong(minMillis, maxMillis);
        Log.d(TAG, "getRandomMillis: " + nextLong);
        return timestampToTime(nextLong);
    }
}