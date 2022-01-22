package com.vankhai.weather.service.base;

import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.model.WeatherData;

import java.util.List;

public interface IApiListener {
    void onWeatherForecastComplete(WeatherData weatherData);
    void onWeatherForecastError(String errorMessage);
    void onLocationRecommendComplete(List<LocationRecommend> locationNames);
    void onLocationRecommendRequestError();
}
