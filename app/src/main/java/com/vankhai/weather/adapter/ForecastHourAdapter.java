package com.vankhai.weather.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vankhai.weather.databinding.ForecastHourTileBinding;
import com.vankhai.weather.model.ForecastDay;
import com.vankhai.weather.model.ForecastHour;
import com.vankhai.weather.util.Utils;

import java.util.List;

public class ForecastHourAdapter extends RecyclerView.Adapter<ForecastHourViewHolder> {
    final List<ForecastHour> forecastHours;

    public ForecastHourAdapter(List<ForecastHour> forecastHours) {
        this.forecastHours = forecastHours;
    }

    @NonNull
    @Override
    public ForecastHourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final ForecastHourTileBinding binding = ForecastHourTileBinding.inflate(layoutInflater, parent, false);
        return new ForecastHourViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ForecastHourViewHolder holder, int position) {
        ForecastHour currentForecast = forecastHours.get(position);
        holder.binding.temperatureTv.setText(Utils.getNiceTemperatureCelsius(currentForecast.getTemperatureCelsius()));
        holder.binding.timeTv.setText(Utils.getHourIndicateForecast(currentForecast.getTime()));

        holder.binding.weatherIndicateImg.setImageResource(Utils.getSmallImageWithCodeAndIsDay(currentForecast.getWeatherCondition().getCode(), currentForecast.getDay()));
    }

    @Override
    public int getItemCount() {
        return forecastHours.size();
    }
}

class ForecastHourViewHolder extends RecyclerView.ViewHolder {

    public ForecastHourViewHolder(@NonNull ForecastHourTileBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    final ForecastHourTileBinding binding;
}