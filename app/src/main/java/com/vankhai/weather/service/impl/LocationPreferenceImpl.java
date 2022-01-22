package com.vankhai.weather.service.impl;

import android.annotation.SuppressLint;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.vankhai.weather.base.Constants;
import com.vankhai.weather.base.WeatherForecastApplication;
import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.service.base.LocationPreference;

public class LocationPreferenceImpl extends LocationPreference {
    private LocationRecommend currentLocation;
    private FusedLocationProviderClient fusedLocationClient;

    LocationPreferenceImpl() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(WeatherForecastApplication.getContext());
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

    @SuppressLint("MissingPermission")
    @Override
    public void getCurrentLatLngString() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                RepositoryImpl.instance.onGetLocationResultSuccess(location.getLatitude() + "," + location.getLongitude());
            } else RepositoryImpl.instance.onGetLocationResultSuccess(Constants.DEFAULT_LOCATION);
        });

    }


}
