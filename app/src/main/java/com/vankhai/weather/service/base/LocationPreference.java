package com.vankhai.weather.service.base;

import com.vankhai.weather.model.LocationRecommend;

import java.util.Optional;

public abstract class LocationPreference {
    public abstract String getLocationName();
    public abstract String getCountryName();
    public abstract void updateLocation(LocationRecommend locationRecommend);
    public abstract String getCurrentLatLngString();
}
