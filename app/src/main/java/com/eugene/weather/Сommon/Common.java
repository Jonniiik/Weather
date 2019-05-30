package com.eugene.weather.Сommon;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String APP_ID = "4e59726f61f8b17b06e8daa7605a24b3";
    public static final String DarkSky_ID = "3d019c84c092a3f07e669516d3fb6c1f";
    public static Location current_location = null;

    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE dd.MM.yyyy");
        String formatted = simpleDateFormat.format(date);
        return formatted;
    }
    public static String convertUnixToDate2(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:00 dd.MM");
        String formatted = simpleDateFormat.format(date);
        return formatted;
    }

    public static String convertUnixToDate00(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:00 EEE dd MM yyyy");
        String formatted = simpleDateFormat.format(date);
        return formatted;
    }

    public static String convertUnixToHour(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String formatted = simpleDateFormat.format(date);
        return formatted;
    }
}
