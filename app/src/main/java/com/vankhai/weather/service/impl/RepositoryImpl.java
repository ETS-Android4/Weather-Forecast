package com.vankhai.weather.service.impl;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.model.WeatherData;
import com.vankhai.weather.service.base.ApiService;
import com.vankhai.weather.service.base.IRepositoryEventListener;
import com.vankhai.weather.service.base.LocationPreference;
import com.vankhai.weather.service.base.Repository;

import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

public class RepositoryImpl extends Repository {

    /// Implement Singleton pattern to have only one Repository for all.
    private static final Repository _instance = new RepositoryImpl();

    public static Repository instance = _instance;

    private WeatherData currentWeather;

    private final ApiService apiService = new ApiServiceImpl();
    private final LocationPreference locationPreference = new LocationPreferenceImpl();

    private final LinkedList<IRepositoryEventListener> listeners = new LinkedList<>();

    @Override
    public void requestWeatherForecastApi() {
        String paramRequest = locationPreference.getLocationName();
        try {
            apiService.getForecastRequest(paramRequest);
        } catch (Exception e) {
            Timber.e("Error occurred: %s", e.toString());
        }
    }

    @Override
    public void onWeatherForecastComplete(WeatherData weatherData) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            listeners.forEach(IRepositoryEventListener::onNewWeatherForecastReceive);
        }
        currentWeather = weatherData;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onWeatherForecastError(String messageError) {
        listeners.forEach(IRepositoryEventListener::onErrorLoadingWeatherForecast);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLocationRecommendComplete(List<LocationRecommend> locationNames) {
        listeners.forEach(iRepositoryEventListener -> iRepositoryEventListener.onLocationNameAutoCompleteReceive(locationNames));
        Timber.i("Call this hong??????????????????????????????????????? RepoImpl");
    }

    @Override
    public WeatherData getWeatherForecast() {
        return currentWeather;
    }

    @Override
    public void registerIRepositoryEventListener(IRepositoryEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public String getLocationName() {
        return locationPreference.getLocationName();
    }

    @Override
    public void onTypeQueryLocation(String pattern) {
        apiService.requestRecommendLocationNameWithPattern(pattern);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLocationRecommendRequestError() {
        listeners.forEach(IRepositoryEventListener::onLocationNameAutoCompleteError);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onUserChangeLocationName(LocationRecommend location) {
        locationPreference.updateLocation(location);
        listeners.forEach(IRepositoryEventListener::onLocationNameChange);
    }
}
