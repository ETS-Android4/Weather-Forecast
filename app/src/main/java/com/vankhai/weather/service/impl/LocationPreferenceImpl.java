package com.vankhai.weather.service.impl;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.vankhai.weather.base.Constants;
import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.service.base.LocationPreference;

public class LocationPreferenceImpl extends LocationPreference {
    private LocationRecommend currentLocation;
    private FusedLocationProviderClient fusedLocationClient;

    LocationPreferenceImpl() {
    }

    @Override
    public String getLocationName() {
        if (currentLocation == null) {
            return Constants.DEFAULT_LOCATION_RECOMMEND.getName();
        } else {
            return currentLocation.getName();
        }
    }

    @Override
    public String getCountryName() {
        if (currentLocation == null) {
            return Constants.DEFAULT_LOCATION_RECOMMEND.getCountry();
        } else {
            if (currentLocation.getCountry().equals("Vietnam"))
                return Constants.DEFAULT_COUNTRY;
            else
                return currentLocation.getCountry();
        }
    }

    @Override
    public void updateLocation(LocationRecommend locationRecommend) {
        currentLocation = locationRecommend;
    }

    @Override
    public String getCurrentLatLngString() {
        return "12.495070079883831,109.1325853714918";
    }
}
