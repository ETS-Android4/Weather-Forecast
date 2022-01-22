package com.vankhai.weather.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

public class WeatherData {
    private final Weather currentWeather;
    private final List<Forecast> forecast;

    public WeatherData(Weather currentWeather, List<Forecast> forecast) {
        this.currentWeather = currentWeather;
        this.forecast = forecast;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static WeatherData fromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        Weather currentWeather = Weather.fromJsonObject(jsonObject.getJSONObject("current"));
        JSONArray forecastArray = jsonObject
                .getJSONObject("forecast")
                .getJSONArray("forecastday");

        LinkedList<Forecast> forecasts = new LinkedList<>();
        for (int i = 0; i < forecastArray.length(); i++) {
            JSONObject forecast = forecastArray.getJSONObject(i);
            LocalDateTime time = LocalDateTime.ofEpochSecond(forecast.getInt("date_epoch"),
                    0, ZoneOffset.of("+07:00"));

            ForecastDay forecastDay = ForecastDay.fromJsonObject(forecast.getJSONObject("day"));

            JSONArray forecastHoursArray = forecast.getJSONArray("hour");
            LinkedList<ForecastHour> forecastHours = new LinkedList<>();
            for(int y = 0; y < forecastHoursArray.length(); y ++ ) {
                JSONObject fHourObject = forecastHoursArray.getJSONObject(y);
                ForecastHour forecastHour = ForecastHour.fromJsonObject(fHourObject);

                forecastHours.add(forecastHour);
            }
            forecasts.add(new Forecast(time, forecastDay, forecastHours));
        }

        Timber.i("Total forecast day we have: " + forecasts.size());
        return new WeatherData(currentWeather, forecasts);
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }
}