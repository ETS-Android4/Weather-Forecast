package com.vankhai.weather.service.base;

import com.vankhai.weather.model.LocationRecommend;

public abstract class LocationPreference {
    public abstract String getLocationName();
    public abstract String getCountryName();
    public abstract void updateLocation(LocationRecommend locationRecommend);
}
