package com.vankhai.weather.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vankhai.weather.base.WeatherLoadingState;
import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.model.WeatherData;
import com.vankhai.weather.service.base.IRepositoryEventListener;
import com.vankhai.weather.service.base.Repository;
import com.vankhai.weather.service.impl.RepositoryImpl;

import java.util.List;

public class MainViewModel extends ViewModel implements IRepositoryEventListener {
    private final Repository repository = RepositoryImpl.instance;

    MutableLiveData<WeatherLoadingState> loadingState = new MutableLiveData<WeatherLoadingState>(WeatherLoadingState.Loading);

    public void getWeatherData() {
        repository.requestWeatherForecastApi();
    }

    public MutableLiveData<WeatherLoadingState> getLoadingState() {
        return loadingState;
    }

    public void registerRepositoryEventListener() {
        repository.registerIRepositoryEventListener(this);
    }

    public String getLocationName() {
        return repository.getLocationName();
    }

    public WeatherData getWeatherDataInformation() {
        return repository.getWeatherForecast();
    }

    @Override
    public void onNewWeatherForecastReceive() {
        loadingState.postValue(WeatherLoadingState.HaveData);
    }

    @Override
    public void onErrorLoadingWeatherForecast() {
        loadingState.postValue(WeatherLoadingState.Error);
    }

    @Override
    public void onLocationNameChange() {
        //TODO: Handle this later

    }

    @Override
    public void onLocationNameAutoCompleteReceive(List<LocationRecommend> locationNames) {
        /// Do nothing
    }

    @Override
    public void onLocationNameAutoCompleteError() {
        /// Do nothing
    }
}
