package com.vankhai.weather.service.impl;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.model.WeatherData;
import com.vankhai.weather.service.RequestUrl;
import com.vankhai.weather.service.base.ApiService;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;

public class ApiServiceImpl extends ApiService {

    OkHttpClient client = new OkHttpClient();

    @Override
    public void getForecastRequest(String paramRequest) throws IOException {
        String url = RequestUrl.forecastApi(paramRequest);
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    try {
                        assert responseBody != null;
                        WeatherData weatherData = WeatherData.fromJson(responseBody.string());
                        Timber.i("Complete parse Weather Forecast");
                        RepositoryImpl.instance.onWeatherForecastComplete(weatherData);
                    } catch (JSONException e) {
                        Timber.i("Some error occurred: %s", e.toString());
                    }
                }
            }
        });
    }

    @Override
    public void requestRecommendLocationNameWithPattern(String pattern) {
        String url = RequestUrl.autocompleteApi(pattern);
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                RepositoryImpl.instance.onWeatherForecastError("Có lỗi xảy ra");
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    assert responseBody != null;
                    List<LocationRecommend> recommendLocationNames = null;
                    try {
                        recommendLocationNames = LocationRecommend.listFromJson(responseBody.string());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RepositoryImpl.instance.onLocationRecommendComplete(recommendLocationNames);
                    }
                }

        });
    }
}
