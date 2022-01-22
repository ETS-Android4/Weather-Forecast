package com.vankhai.weather.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * Field forecastday JSON
 */
public class Forecast {
    private final LocalDateTime date;
    private final ForecastDay forecastDay;
    private final List<ForecastHour> forecastHours;

    public Forecast(LocalDateTime date, ForecastDay forecastDay, List<ForecastHour> forecastHours) {
        this.date = date;
        this.forecastDay = forecastDay;
        this.forecastHours = forecastHours;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ForecastDay getForecastDay() {
        return forecastDay;
    }

    public List<ForecastHour> getHours() {
        return forecastHours;
    }
}
