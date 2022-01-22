package com.vankhai.weather.service.base;

import com.vankhai.weather.model.LocationRecommend;

import java.util.List;

public interface IRepositoryEventListener {
    void onNewWeatherForecastReceive();
    void onErrorLoadingWeatherForecast();
    void onLocationNameChange();
    void onLocationNameAutoCompleteReceive(List<LocationRecommend> locationNames);
    void onLocationNameAutoCompleteError();
}
