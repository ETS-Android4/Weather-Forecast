package com.vankhai.weather.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.service.base.IRepositoryEventListener;
import com.vankhai.weather.service.base.Repository;
import com.vankhai.weather.service.impl.RepositoryImpl;

import java.util.List;

import timber.log.Timber;

public class SettingViewModel extends ViewModel implements IRepositoryEventListener {
    private final Repository repository = RepositoryImpl.instance;

    // Current location name
    private final MutableLiveData<String> locationName = new MutableLiveData<>(repository.getLocationName());

    // Indicate if user is editing location for weather forecast, by hit edit icon and type in EditText.
    private final MutableLiveData<Boolean> isEditLocationByInput = new MutableLiveData<>(false);

    private final MutableLiveData<List<LocationRecommend>> locationRecommends = new MutableLiveData<>();

    public MutableLiveData<String> getLocationName() {
        return locationName;
    }

    public MutableLiveData<Boolean> getIsEditLocationByInput() {
        return isEditLocationByInput;
    }

    public MutableLiveData<List<LocationRecommend>> getLocationRecommends() {
        return locationRecommends;
    }

    public String getCurrentLocation() {
        return repository.getLocationName();
    }

    public  String getCurrentCountryName() {
        return repository.getCountryName();
    }

    public void onTypeQueryLocation(String pattern) {
        repository.onTypeQueryLocation(pattern);
    }

    public void triggerChooseLocationByCurrentLatLng() {
        isEditLocationByInput.setValue(true);
        repository.getLatLngStringDelegate();
    }

    public void triggerEditLocationByInput() {
        isEditLocationByInput.setValue(true);
    }

    public void triggerCancelEditLocationByInput() {
        isEditLocationByInput.setValue(false);
    }

    public void triggerConfirmNewLocationName(LocationRecommend locationRecommend) {
        repository.onUserChangeLocationName(locationRecommend);
        locationName.postValue(locationRecommend.getName());
        repository.requestWeatherForecastApi();
        isEditLocationByInput.postValue(false);
    }

    @Override
    public void onNewWeatherForecastReceive() {
        /// Do nothing
    }

    @Override
    public void onErrorLoadingWeatherForecast() {
        /// Do nothing
    }

    @Override
    public void onLocationNameChange() {
        locationName.postValue(repository.getLocationName());
    }

    @Override
    public void onLocationNameAutoCompleteReceive(List<LocationRecommend> locationNames) {
        locationRecommends.postValue(locationNames);
    }

    @Override
    public void onLocationNameAutoCompleteError() {
        /// Fuck
    }

    public void registerRepositoryEventListener() {
        repository.registerIRepositoryEventListener(this);
    }

    @Override
    public void onLocationStringResultReady(String result) {
        repository.onTypeQueryLocation(result);
    }
}
