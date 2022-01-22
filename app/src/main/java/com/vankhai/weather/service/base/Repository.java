package com.vankhai.weather.service.base;

import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.model.Weather;
import com.vankhai.weather.model.WeatherData;

abstract public class Repository implements IApiListener {
    public abstract void requestWeatherForecastApi();
    public abstract WeatherData getWeatherForecast();
    public abstract void registerIRepositoryEventListener(IRepositoryEventListener listener);
    public abstract String getLocationName();
    public abstract void onTypeQueryLocation(String pattern);
    public abstract void onUserChangeLocationName(LocationRecommend location);
}
