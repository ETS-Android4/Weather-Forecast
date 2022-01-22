package com.vankhai.weather.model;

public class WeatherCondition {
    public WeatherCondition(String text, String icon, int code) {
        this.text = text;
        this.icon = icon;
        this.code = code;
    }

    private final String text;
    private final String icon;
    private final int code;

    public int getCode() {
        return code;
    }

    public String getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }
}
