package com.vankhai.weather.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ForecastDay {

    public ForecastDay(Double maxTemperatureC, Double maxTemperatureF, Double minTemperatureC, Double minTemperatureF, Double avgTemperatureC, Double avgTemperatureF, Double maxWindSpeed, Double avgHumidity, Integer chanceOfRain, WeatherCondition weatherCondition, Double uv) {
        this.maxTemperatureC = maxTemperatureC;
        this.maxTemperatureF = maxTemperatureF;
        this.minTemperatureC = minTemperatureC;
        this.minTemperatureF = minTemperatureF;
        this.avgTemperatureC = avgTemperatureC;
        this.avgTemperatureF = avgTemperatureF;
        this.maxWindSpeed = maxWindSpeed;
        this.avgHumidity = avgHumidity;
        this.chanceOfRain = chanceOfRain;
        this.weatherCondition = weatherCondition;
        this.uv = uv;
    }

    public static ForecastDay fromJsonObject(JSONObject dayObject) throws JSONException {
        Double maxTemperatureC = dayObject.getDouble("maxtemp_c");
        Double maxTemperatureF = dayObject.getDouble("maxtemp_f");
        Double minTemperatureC = dayObject.getDouble("mintemp_c");
        Double minTemperatureF = dayObject.getDouble("mintemp_f");
        Double avgTemperatureC = dayObject.getDouble("avgtemp_c");
        Double avgTemperatureF = dayObject.getDouble("avgtemp_f");
        Double maxWindSpeed = dayObject.getDouble("maxwind_kph");
        Double avgHumidity = dayObject.getDouble("avghumidity");
        Integer chanceOfRain = dayObject.getInt("daily_chance_of_rain");
        JSONObject conditionObject = dayObject.getJSONObject("condition");
        String conditionText = conditionObject.getString("text");
        String icon = conditionObject.getString("icon");
        Integer conditionCode = conditionObject.getInt("code");
        WeatherCondition weatherCondition = new WeatherCondition(conditionText, icon, conditionCode);
        Double uv = dayObject.getDouble("uv");

        return new ForecastDay(maxTemperatureC, maxTemperatureF, minTemperatureC, minTemperatureF,
                avgTemperatureC, avgTemperatureF, maxWindSpeed, avgHumidity, chanceOfRain,
                weatherCondition, uv);
    }

    private final Double maxTemperatureC;
    private final Double maxTemperatureF;
    private final Double minTemperatureC;
    private final Double minTemperatureF;
    private final Double avgTemperatureC;
    private final Double avgTemperatureF;
    private final Double maxWindSpeed;
    private final Double avgHumidity;
    private final Integer chanceOfRain;
    private final WeatherCondition weatherCondition;
    private final Double uv;

    public Double getMaxTemperatureC() {
        return maxTemperatureC;
    }

    public Double getMaxTemperatureF() {
        return maxTemperatureF;
    }

    public Double getMinTemperatureC() {
        return minTemperatureC;
    }

    public Double getMinTemperatureF() {
        return minTemperatureF;
    }

    public Double getAvgTemperatureC() {
        return avgTemperatureC;
    }

    public Double getAvgTemperatureF() {
        return avgTemperatureF;
    }

    public Double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public Double getAvgHumidity() {
        return avgHumidity;
    }

    public Integer getChanceOfRain() {
        return chanceOfRain;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public Double getUv() {
        return uv;
    }
}
