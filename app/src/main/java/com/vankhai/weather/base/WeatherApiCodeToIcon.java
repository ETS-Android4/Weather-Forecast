package com.vankhai.weather.base;

public class WeatherApiCodeToIcon {
    private final Integer code;
    private final String day;
    private final String night;
    private final int smallIconDay;
    private final int largeIconDay;
    private final int smallIconNight;
    private final int largeIconNight;

    public WeatherApiCodeToIcon(Integer code, String day, String night, int smallIconDay, int largeIconDay, int smallIconNight, int largeIconNight) {
        this.code = code;
        this.day = day;
        this.night = night;
        this.smallIconDay = smallIconDay;
        this.largeIconDay = largeIconDay;
        this.smallIconNight = smallIconNight;
        this.largeIconNight = largeIconNight;
    }

    public Integer getCode() {
        return code;
    }

    public int getSmallIconDay() {
        return smallIconDay;
    }

    public int getLargeIconDay() {
        return largeIconDay;
    }

    public int getSmallIconNight() {
        return smallIconNight;
    }

    public int getLargeIconNight() {
        return largeIconNight;
    }
}