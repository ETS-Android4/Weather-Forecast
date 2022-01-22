package com.vankhai.weather.service;

import com.vankhai.weather.base.Constants;

public class RequestUrl {
    private static final String _baseUrl = Constants.API_BASE;
    private static final String _apiKey = Constants.API_KEY;
    private static final String _langPreference = Constants.LANG_PREFERENCES;
    private static final int _totalDayForecast = Constants.TOTAL_DAY_FORECAST;

    public static String forecastApi(String queryParams) {
        return _baseUrl + "/forecast.json" + "?key=" + _apiKey + "&q=" + queryParams + "&lang=" + _langPreference + "&days=" + _totalDayForecast + "&aqi=no&alerts=no";
    }

    public static final String autocompleteApi(String queryParams) {
        return _baseUrl + "/search.json?key=" + _apiKey + "&q=" + queryParams;
    }
}
