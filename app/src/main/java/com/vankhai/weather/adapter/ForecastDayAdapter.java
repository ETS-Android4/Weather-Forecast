package com.vankhai.weather.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vankhai.weather.databinding.ForecastDayTileBinding;
import com.vankhai.weather.model.Forecast;
import com.vankhai.weather.model.ForecastDay;
import com.vankhai.weather.model.ForecastHour;
import com.vankhai.weather.util.Utils;

import java.util.List;

import timber.log.Timber;

public class ForecastDayAdapter extends RecyclerView.Adapter<ForecastDayViewHolder> {
    final List<Forecast> forecasts;

    public ForecastDayAdapter(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @NonNull
    @Override
    public ForecastDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final ForecastDayTileBinding binding = ForecastDayTileBinding.inflate(layoutInflater, parent, false);
        return new ForecastDayViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ForecastDayViewHolder holder, int position) {
        Forecast currentForecast = forecasts.get(position);
        ForecastDay currentForecastDay = currentForecast.getForecastDay();

        holder.binding.temperatureTv.setText(Utils.getNiceTemperatureCelsius(currentForecastDay.getAvgTemperatureC()));
        holder.binding.statusIndicateTv.setText(currentForecastDay.getWeatherCondition().getText());
        holder.binding.timeTv.setText(Utils.getDateFormatted(currentForecast.getDate()));

        holder.binding.weatherIndicateImg.setImageResource(Utils.getSmallImageWithCodeAndIsDay(currentForecastDay.getWeatherCondition().getCode(), true));

        List<ForecastHour> forecastHours = currentForecast.getHours();
        ForecastHourAdapter forecastHourAdapter = new ForecastHourAdapter(forecastHours);

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.binding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false);

        holder.binding.forecastHourlyLv.setLayoutManager(layoutManager);
        holder.binding.forecastHourlyLv.setAdapter(forecastHourAdapter);
        holder.binding.forecastHourlyLv.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }
}

class ForecastDayViewHolder extends RecyclerView.ViewHolder {

    public ForecastDayViewHolder(@NonNull ForecastDayTileBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    final ForecastDayTileBinding binding;
}