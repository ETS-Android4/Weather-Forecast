package com.vankhai.weather.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/*
 * Keep information of the current weather
 */
public class Weather {
    public Weather(LocalDateTime lastUpdate, Double temperatureCelsius, Double temperatureFahrenheit, Boolean isDay, WeatherCondition weatherCondition, Double windSpeed, Integer humidity, Integer cloud, Double feelLikeC, Double feelLikeF, Double visibilityKm, Double uv) {
        this.lastUpdate = lastUpdate;
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
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Weather fromJsonObject(JSONObject weatherObject) throws JSONException {
        LocalDateTime lastUpdate = LocalDateTime.ofEpochSecond(weatherObject.getInt("last_updated_epoch"), 0, ZoneOffset.of("+07:00"));
        Double temperatureCel = weatherObject.getDouble("temp_c");
        Double temperatureFah = weatherObject.getDouble("temp_f");
        Boolean isDay = 1 == weatherObject.getInt("is_day");

        JSONObject condition = weatherObject.getJSONObject("condition");
        String condText = condition.getString("text");
        String icon = condition.getString("icon");
        Integer condCode = condition.getInt("code");
        WeatherCondition wCondition = new WeatherCondition(condText, icon, condCode);

        Double windSpeed = weatherObject.getDouble("wind_kph");
        Integer humidity = weatherObject.getInt("humidity");
        Integer cloud = weatherObject.getInt("cloud");

        Double feelLikeC = weatherObject.getDouble("feelslike_c");
        Double feelLikeF = weatherObject.getDouble("feelslike_f");
        Double visibilityKm = weatherObject.getDouble("vis_km");
        Double uv = weatherObject.getDouble("uv");

        return new Weather(lastUpdate, temperatureCel, temperatureFah, isDay, wCondition,
                windSpeed, humidity, cloud, feelLikeC, feelLikeF, visibilityKm, uv);

    }

    private final LocalDateTime lastUpdate;
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
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
}
