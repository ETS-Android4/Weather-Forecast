package com.vankhai.weather.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class ForecastHour {
    public ForecastHour(LocalDateTime time, Double temperatureCelsius, Double temperatureFahrenheit, Boolean isDay, WeatherCondition weatherCondition, Double windSpeed, Integer humidity, Integer cloud, Double feelLikeC, Double feelLikeF, Double visibilityKm, Double uv, Integer chanceOfRain) {
        this.time = time;
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureFahrenheit = temperatureFahrenheit;
        this.isDay = isDay;
        this.weatherCondition = weatherCondition;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.cloud = cloud;
        this.feelLikeC = feelLikeC;
        this.feelLikeF = feelLikeF;
        this.visibilityKm = visibilityKm;
        this.uv = uv;
        this.chanceOfRain = chanceOfRain;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ForecastHour fromJsonObject(JSONObject hourObject) throws JSONException {
        LocalDateTime time = LocalDateTime.ofEpochSecond(hourObject.getInt("time_epoch"), 0, ZoneOffset.of("+07:00"));
        Double temperatureCel = hourObject.getDouble("temp_c");
        Double temperatureFah = hourObject.getDouble("temp_f");
        Boolean isDay = 1 == hourObject.getInt("is_day");

        JSONObject condition = hourObject.getJSONObject("condition");
        String condText = condition.getString("text");
        String icon = condition.getString("icon");
        Integer condCode = condition.getInt("code");
        WeatherCondition wCondition = new WeatherCondition(condText, icon, condCode);

        Double windSpeed = hourObject.getDouble("wind_kph");
        Integer humidity = hourObject.getInt("humidity");
        Integer cloud = hourObject.getInt("cloud");

        Double feelLikeC = hourObject.getDouble("feelslike_c");
        Double feelLikeF = hourObject.getDouble("feelslike_f");
        Double visibilityKm = hourObject.getDouble("vis_km");
        Double uv = hourObject.getDouble("uv");
        Integer chanceOfRain = hourObject.getInt("chance_of_rain");

        return new ForecastHour(time, temperatureCel, temperatureFah, isDay, wCondition,
                windSpeed, humidity, cloud, feelLikeC, feelLikeF, visibilityKm, uv, chanceOfRain);

    }

    private final LocalDateTime time;
    private final Double temperatureCelsius;
    private final Double temperatureFahrenheit;
    private final Boolean isDay;
    private final WeatherCondition weatherCondition;
    private final Double windSpeed;
    private final Integer humidity;
    private final Integer cloud;
    private final Double feelLikeC;
    private final Double feelLikeF;
    private final Double visibilityKm;
    private final Double uv;
    private final Integer chanceOfRain;

    public LocalDateTime getTime() {
        return time;
    }

    public Double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public Double getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public Boolean getDay() {
        return isDay;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getCloud() {
        return cloud;
    }

    public Double getFeelLikeC() {
        return feelLikeC;
    }

    public Double getFeelLikeF() {
        return feelLikeF;
    }

    public Double getVisibilityKm() {
        return visibilityKm;
    }

    public Double getUv() {
        return uv;
    }

    public Integer getChanceOfRain() {
        return chanceOfRain;
    }
}
