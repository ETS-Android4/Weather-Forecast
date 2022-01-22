package com.vankhai.weather.service.impl;

import com.vankhai.weather.base.Constants;
import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.service.base.LocationPreference;

public class LocationPreferenceImpl extends LocationPreference {
    private LocationRecommend currentLocation;

    LocationPreferenceImpl() {
    }

    @Override
    public String getLocationName() {
        if(currentLocation == null) {
            return Constants.DEFAULT_LOCATION;
        } else {
            return currentLocation.getName();
        }
    }

    @Override
    public String getCountryName() {
        if(currentLocation == null) {
            return Constants.DEFAULT_COUNTRY;
        } else {
            return currentLocation.getCountry();
        }
    }

    @Override
    public void updateLocation(LocationRecommend locationRecommend) {
        currentLocation = locationRecommend;
    }
}
