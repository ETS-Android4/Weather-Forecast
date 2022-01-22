package com.vankhai.weather.presentation.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vankhai.weather.adapter.ForecastDayAdapter;
import com.vankhai.weather.base.Constants;
import com.vankhai.weather.base.ImageResource;
import com.vankhai.weather.databinding.BottomSheetTemperatureDetailLayoutBinding;
import com.vankhai.weather.databinding.FragmentDetailWeatherBinding;
import com.vankhai.weather.model.Forecast;
import com.vankhai.weather.model.Weather;
import com.vankhai.weather.model.WeatherData;
import com.vankhai.weather.presentation.viewmodel.MainViewModel;
import com.vankhai.weather.util.Utils;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DetailWeatherFragment extends Fragment {
    public DetailWeatherFragment(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private FragmentDetailWeatherBinding binding;
    private final MainViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailWeatherBinding.inflate(inflater, container, false);

        WeatherData weatherData = viewModel.getWeatherDataInformation();

        Weather currentWeather = weatherData.getCurrentWeather();
        List<Forecast> forecast = weatherData.getForecast();

        binding.mainTemperatureDisplay.locationTv.setText(viewModel.getLocationName());
        binding.mainTemperatureDisplay.dateTv.setText(Utils.getDateFormattedWithoutPrefix(weatherData.getCurrentWeather().getLastUpdate()));

        binding.mainTemperatureDisplay.temperatureTv.setText(Utils.getNiceTemperatureCelsius(currentWeather.getTemperatureCelsius()));
        binding.mainTemperatureDisplay.statusWeatherTv.setText(currentWeather.getWeatherCondition().getText());

        binding.mainTemperatureDisplay.temperatureIndicateImg.setImageResource(Utils.getLargeImageWithCodeAndIsDay(currentWeather.getWeatherCondition().getCode(), currentWeather.getDay()));

        List<Forecast> forecasts = weatherData.getForecast();
        ForecastDayAdapter fDayAdapter = new ForecastDayAdapter(forecasts);
        binding.forecastLv.setAdapter(fDayAdapter);
        binding.forecastLv.setNestedScrollingEnabled(false);
        binding.forecastLv.setOverScrollMode(View.OVER_SCROLL_NEVER);

        binding.detailCurrentWeather.cloudRow.infoTv.setText(Constants.CLOUD + ": " + currentWeather.getCloud() + "%");
        binding.detailCurrentWeather.cloudRow.infoImg.setImageResource(ImageResource.CLOUDY_SMALL);

        binding.detailCurrentWeather.feelLikeRow.infoTv.setText(Constants.FEEL_LIKE + ": " + Utils.getNiceTemperatureCelsius(currentWeather.getFeelLikeC()));
        binding.detailCurrentWeather.feelLikeRow.infoImg.setImageResource(ImageResource.TEMPERATURE_SMALL);

        binding.detailCurrentWeather.humidityRow.infoTv.setText(Constants.HUMIDITY + ": " + currentWeather.getHumidity() + "%");
        binding.detailCurrentWeather.humidityRow.infoImg.setImageResource(ImageResource.HUMIDITY_SMALL);

        binding.detailCurrentWeather.visibilityRow.infoTv.setText(Constants.VISIBILITY + ": " + currentWeather.getVisibilityKm() + "km");
        binding.detailCurrentWeather.visibilityRow.infoImg.setImageResource(ImageResource.VISIBILITY_SMALL);

        binding.detailCurrentWeather.windSpeedRow.infoTv.setText(Constants.WIND_SPEED + ": " + currentWeather.getWindSpeed() + "km");
        binding.detailCurrentWeather.windSpeedRow.infoImg.setImageResource(ImageResource.WIND_SMALL);

        binding.detailCurrentWeather.uvRow.infoTv.setText(Constants.UV + ": " + currentWeather.getUv());
        binding.detailCurrentWeather.uvRow.infoImg.setImageResource(ImageResource.SUNNY_SMALL);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm, dd/MM/yyyy");
        binding.detailCurrentWeather.lastUpdateTv.setText(Constants.LAST_UPDATE + ": " + formatter.format(currentWeather.getLastUpdate()));

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    void showDetailCurrentTemperatureDialog(Weather currentWeather) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());

        BottomSheetTemperatureDetailLayoutBinding bindX = BottomSheetTemperatureDetailLayoutBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(bindX.getRoot());

        bindX.cloudRow.infoTv.setText(Constants.CLOUD + ": " + currentWeather.getCloud() + "%");
        bindX.cloudRow.infoImg.setImageResource(ImageResource.CLOUDY_SMALL);

        bindX.feelLikeRow.infoTv.setText(Constants.FEEL_LIKE + ": " + Utils.getNiceTemperatureCelsius(currentWeather.getFeelLikeC()));
        bindX.feelLikeRow.infoImg.setImageResource(ImageResource.CLOUDY_SMALL);

        bindX.humidityRow.infoTv.setText(Constants.HUMIDITY + ": " + currentWeather.getHumidity() + "%");
        bindX.humidityRow.infoImg.setImageResource(ImageResource.HUMIDITY_SMALL);

        bindX.visibilityRow.infoTv.setText(Constants.VISIBILITY + ": " + currentWeather.getVisibilityKm() + "km");
        bindX.visibilityRow.infoImg.setImageResource(ImageResource.SUNNY_SMALL);

        bindX.windSpeedRow.infoTv.setText(Constants.WIND_SPEED + ": " + currentWeather.getWindSpeed() + "km");
        bindX.windSpeedRow.infoImg.setImageResource(ImageResource.WIND_SMALL);

        bindX.uvRow.infoTv.setText(Constants.UV + ": " + currentWeather.getUv());
        bindX.uvRow.infoImg.setImageResource(ImageResource.SUNNY_SMALL);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm, dd/MM/yyyy");
        bindX.lastUpdateTv.setText(Constants.LAST_UPDATE + ": " + formatter.format(currentWeather.getLastUpdate()));
        bottomSheetDialog.show();
    }
}