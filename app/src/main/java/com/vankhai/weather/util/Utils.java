package com.vankhai.weather.util;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.vankhai.weather.base.Constants;
import com.vankhai.weather.base.ImageResource;
import com.vankhai.weather.base.WeatherApiCodeToIcon;

import java.time.LocalDateTime;

public class Utils {
    public static String getNiceTemperatureCelsius(Double temperature) {
        int t = temperature.intValue();
        return t + "°C";
    }

    public static String getNiceTemperatureFah(Double temperature) {
        int t = temperature.intValue();
        return t + "°F";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateFormatted(LocalDateTime date) {
        int dayOfMonth = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        String prefix = "";

        LocalDateTime today = LocalDateTime.now();
        if (today.getDayOfMonth() == dayOfMonth) {
            prefix = Constants.TODAY + ", ";
        } else if (today.getDayOfMonth() == dayOfMonth - 1) {
            prefix = Constants.TOMORROW + ", ";
        }

        String dayOfWeekText = getTextFromDayOfWeek(date.getDayOfWeek().getValue());

        return prefix + dayOfWeekText + ", " + Constants.DAY + " " + dayOfMonth + " " + Constants.MONTH + " " + month;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateFormattedWithoutPrefix(LocalDateTime date) {
        int dayOfMonth = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        String dayOfWeekText = getTextFromDayOfWeek(date.getDayOfWeek().getValue());

        return dayOfWeekText + ", " + Constants.DAY + " " + dayOfMonth + " " + Constants.MONTH + " " + month;
    }

    public static String getTextFromDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return Constants.MONDAY;
            case 2:
                return Constants.TUESDAY;
            case 3:
                return Constants.WEDNESDAY;
            case 4:
                return Constants.THURSDAY;
            case 5:
                return Constants.FRIDAY;
            case 6:
                return Constants.SATURDAY;

            default:
                return Constants.SUNDAY;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getHourIndicateForecast(LocalDateTime time) {
        Integer hour = time.getHour();
        @SuppressLint("DefaultLocale") String paddedHour = String.format("%02d", hour);
        return paddedHour + ":00";
    }

    public static int getSmallImageWithCodeAndIsDay(int code, boolean isDay) {
        WeatherApiCodeToIcon weatherApiCodeToIcon = getWeatherApiCodeToIcon(code);
        if (isDay) {
            return weatherApiCodeToIcon.getSmallIconDay();
        } else {
            return weatherApiCodeToIcon.getSmallIconNight();
        }
    }

    public static int getLargeImageWithCodeAndIsDay(int code, boolean isDay) {
        WeatherApiCodeToIcon weatherApiCodeToIcon = getWeatherApiCodeToIcon(code);
        if (isDay) {
            return weatherApiCodeToIcon.getLargeIconDay();
        } else {
            return weatherApiCodeToIcon.getLargeIconNight();
        }
    }

    public static WeatherApiCodeToIcon getWeatherApiCodeToIcon(int code) {
        switch (code) {
            case 1000:
                return new WeatherApiCodeToIcon(1000, "Sunny", "Clear", ImageResource.SUNNY_SMALL, ImageResource.SUNNY_LARGE, ImageResource.CLEAR_NIGHT_SMALL, ImageResource.CLEAR_NIGHT_LARGE);
            case 1003:
                return new WeatherApiCodeToIcon(1003, "Partly cloudy", "Partly cloudy", ImageResource.PARTLY_CLOUDY_DAY_SMALL, ImageResource.PARTLY_CLOUDY_DAY_LARGE, ImageResource.PARTLY_CLOUDY_NIGHT_SMALL, ImageResource.PARTLY_CLOUDY_NIGHT_LARGE);
            case 1006:
                return new WeatherApiCodeToIcon(1006, "Cloudy", "Cloudy", ImageResource.CLOUDY_SMALL, ImageResource.CLOUDY_LARGE, ImageResource.PARTLY_CLOUDY_NIGHT_SMALL, ImageResource.PARTLY_CLOUDY_NIGHT_LARGE);
            case 1009:
                return new WeatherApiCodeToIcon(1009, "Overcast", "Overcast", ImageResource.CLOUDY_SMALL, ImageResource.CLOUDY_LARGE, ImageResource.PARTLY_CLOUDY_NIGHT_SMALL, ImageResource.PARTLY_CLOUDY_NIGHT_LARGE);
            case 1030:
                return new WeatherApiCodeToIcon(1030, "Mist", "Mist", ImageResource.FOG_SMALL, ImageResource.FOG_LARGE, ImageResource.FOG_SMALL, ImageResource.FOG_LARGE);
            case 1063:
                return new WeatherApiCodeToIcon(1063, "Patchy rain possible", "Patchy rain possible", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1066:
                return new WeatherApiCodeToIcon(1066, "Patchy snow possible", "Patchy snow possible", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1069:
                return new WeatherApiCodeToIcon(1069, "Patchy sleet possible", "Patchy sleet possible", ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE, ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE);
            case 1072:
                return new WeatherApiCodeToIcon(1072, "Patchy freezing drizzle possible", "Patchy freezing drizzle possible", ImageResource.DRIZZLE_AND_SUN_SMALL, ImageResource.DRIZZLE_AND_SUN_LARGE, ImageResource.DRIZZLE_AND_MOON_SMALL, ImageResource.DRIZZLE_AND_MOON_LARGE);
            case 1087:
                return new WeatherApiCodeToIcon(1087, "Thundery outbreaks possible", "Thundery outbreaks possible", ImageResource.SERVER_THUNDERSTORM_SMALL, ImageResource.SERVER_THUNDERSTORM_LARGE, ImageResource.SERVER_THUNDERSTORM_SMALL, ImageResource.SERVER_THUNDERSTORM_LARGE);
            case 1114:
                return new WeatherApiCodeToIcon(1114, "Blowing snow", "Blowing snow", ImageResource.BLOWING_SNOW_SMALL, ImageResource.BLOWING_SNOW_LARGE, ImageResource.BLOWING_SNOW_SMALL, ImageResource.BLOWING_SNOW_LARGE);
            case 1117:
                return new WeatherApiCodeToIcon(1117, "Blizzard", "Blizzard", ImageResource.BLIZZARD_SMALL, ImageResource.BLIZZARD_LARGE, ImageResource.BLIZZARD_SMALL, ImageResource.BLIZZARD_LARGE);
            case 1135:
                return new WeatherApiCodeToIcon(1135, "Fog", "Fog", ImageResource.FOG_SMALL, ImageResource.FOG_LARGE, ImageResource.FOG_SMALL, ImageResource.FOG_LARGE);
            case 1147:
                return new WeatherApiCodeToIcon(1147, "Freezing fog", "Freezing fog", ImageResource.FOG_SMALL, ImageResource.FOG_LARGE, ImageResource.FOG_SMALL, ImageResource.FOG_LARGE);
            case 1150:
                return new WeatherApiCodeToIcon(1150, "Patchy light drizzle", "Patchy light drizzle", ImageResource.DRIZZLE_AND_SUN_SMALL, ImageResource.DRIZZLE_AND_SUN_LARGE, ImageResource.DRIZZLE_AND_MOON_SMALL, ImageResource.DRIZZLE_AND_MOON_LARGE);
            case 1153:
                return new WeatherApiCodeToIcon(1153, "Light drizzle", "Light drizzle", ImageResource.DRIZZLE_AND_SUN_SMALL, ImageResource.DRIZZLE_AND_SUN_LARGE, ImageResource.DRIZZLE_AND_MOON_SMALL, ImageResource.DRIZZLE_AND_MOON_LARGE);
            case 1168:
                return new WeatherApiCodeToIcon(1168, "Freezing drizzle", "Freezing drizzle", ImageResource.DRIZZLE_AND_SUN_SMALL, ImageResource.DRIZZLE_AND_SUN_LARGE, ImageResource.DRIZZLE_AND_MOON_SMALL, ImageResource.DRIZZLE_AND_MOON_LARGE);
            case 1171:
                return new WeatherApiCodeToIcon(1171, "Heavy freezing drizzle", "Heavy freezing drizzle", ImageResource.DRIZZLE_AND_SUN_SMALL, ImageResource.DRIZZLE_AND_SUN_LARGE, ImageResource.DRIZZLE_AND_MOON_SMALL, ImageResource.DRIZZLE_AND_MOON_LARGE);
            case 1180:
                return new WeatherApiCodeToIcon(1180, "Patchy light rain", "Patchy light rain", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1183:
                return new WeatherApiCodeToIcon(1183, "Light rain", "Light rain", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1186:
                return new WeatherApiCodeToIcon(1186, "Moderate rain at times", "Moderate rain at times", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1189:
                return new WeatherApiCodeToIcon(1189, "Moderate rain", "Moderate rain", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1192:
                return new WeatherApiCodeToIcon(1192, "Heavy rain at times", "Heavy rain at times", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1195:
                return new WeatherApiCodeToIcon(1195, "Heavy rain", "Heavy rain", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1198:
                return new WeatherApiCodeToIcon(1198, "Light freezing rain", "Light freezing rain", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1201:
                return new WeatherApiCodeToIcon(1201, "Moderate or heavy freezing rain", "Moderate or heavy freezing rain", ImageResource.RAIN_AND_SUN_SMALL, ImageResource.RAIN_AND_SUN_LARGE, ImageResource.RAIN_AND_MOON_SMALL, ImageResource.RAIN_AND_MOON_LARGE);
            case 1204:
                return new WeatherApiCodeToIcon(1204, "Light sleet", "Light sleet", ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE, ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE);
            case 1207:
                return new WeatherApiCodeToIcon(1207, "Moderate or heavy sleet", "Moderate or heavy sleet", ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE, ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE);
            case 1210:
                return new WeatherApiCodeToIcon(1210, "Patchy light snow", "Patchy light snow", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1213:
                return new WeatherApiCodeToIcon(1213, "Light snow", "Light snow", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1216:
                return new WeatherApiCodeToIcon(1216, "Patchy moderate snow", "Patchy moderate snow", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1219:
                return new WeatherApiCodeToIcon(1219, "Moderate snow", "Moderate snow", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1222:
                return new WeatherApiCodeToIcon(1222, "Patchy heavy snow", "Patchy heavy snow", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1225:
                return new WeatherApiCodeToIcon(1225, "Heavy snow", "Heavy snow", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1237:
                return new WeatherApiCodeToIcon(1237, "Ice pellets", "Ice pellets", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1240:
                return new WeatherApiCodeToIcon(1240, "Light rain shower", "Light rain shower", ImageResource.SCATTERED_SHOWERS_DAY_SMALL, ImageResource.SCATTERED_SHOWERS_DAY_LARGE, ImageResource.SCATTERED_SHOWERS_NIGHT_SMALL, ImageResource.SCATTERED_SHOWERS_NIGHT_LARGE);
            case 1243:
                return new WeatherApiCodeToIcon(1243, "Moderate or heavy rain shower", "Moderate or heavy rain shower", ImageResource.SCATTERED_SHOWERS_DAY_SMALL, ImageResource.SCATTERED_SHOWERS_DAY_LARGE, ImageResource.SCATTERED_SHOWERS_NIGHT_SMALL, ImageResource.SCATTERED_SHOWERS_NIGHT_LARGE);
            case 1246:
                return new WeatherApiCodeToIcon(1246, "Torrential rain shower", "Torrential rain shower", ImageResource.SCATTERED_SHOWERS_DAY_SMALL, ImageResource.SCATTERED_SHOWERS_DAY_LARGE, ImageResource.SCATTERED_SHOWERS_NIGHT_SMALL, ImageResource.SCATTERED_SHOWERS_NIGHT_LARGE);
            case 1249:
                return new WeatherApiCodeToIcon(1249, "Light sleet showers", "Light sleet showers", ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE, ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE);
            case 1252:
                return new WeatherApiCodeToIcon(1252, "Moderate or heavy sleet showers", "Moderate or heavy sleet showers", ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE, ImageResource.SLEET_SMALL, ImageResource.SLEET_LARGE);
            case 1255:
                return new WeatherApiCodeToIcon(1255, "Light snow showers", "Light snow showers", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1258:
                return new WeatherApiCodeToIcon(1258, "Moderate or heavy snow showers", "Moderate or heavy snow showers", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1261:
                return new WeatherApiCodeToIcon(1261, "Light showers of ice pellets", "Light showers of ice pellets", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1264:
                return new WeatherApiCodeToIcon(1264, "Moderate or heavy showers of ice pellets", "Moderate or heavy showers of ice pellets", ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE, ImageResource.SNOW_SMALL, ImageResource.SNOW_LARGE);
            case 1273:
                return new WeatherApiCodeToIcon(1273, "Patchy light rain with thunder", "Patchy light rain with thunder", ImageResource.SCATTERED_THUNDERSTORM_SMALL, ImageResource.SCATTERED_THUNDERSTORM_LARGE, ImageResource.RAIN_AND_THUNDERSTORM_SMALL, ImageResource.RAIN_AND_THUNDERSTORM_LARGE);
            case 1276:
                return new WeatherApiCodeToIcon(1276, "Moderate or heavy rain with thunder", "Moderate or heavy rain with thunder", ImageResource.SCATTERED_THUNDERSTORM_SMALL, ImageResource.SCATTERED_THUNDERSTORM_LARGE, ImageResource.RAIN_AND_THUNDERSTORM_SMALL, ImageResource.RAIN_AND_THUNDERSTORM_LARGE);
            case 1279:
                return new WeatherApiCodeToIcon(1279, "Patchy light snow with thunder", "Patchy light snow with thunder", ImageResource.SCATTERED_THUNDERSTORM_SMALL, ImageResource.SCATTERED_THUNDERSTORM_LARGE, ImageResource.RAIN_AND_THUNDERSTORM_SMALL, ImageResource.RAIN_AND_THUNDERSTORM_LARGE);
            case 1282:
                return new WeatherApiCodeToIcon(1282, "Moderate or heavy snow with thunder", "Moderate or heavy snow with thunder", ImageResource.SCATTERED_THUNDERSTORM_SMALL, ImageResource.SCATTERED_THUNDERSTORM_LARGE, ImageResource.RAIN_AND_THUNDERSTORM_SMALL, ImageResource.RAIN_AND_THUNDERSTORM_LARGE);

            default:
                return new WeatherApiCodeToIcon(0, "Unknown", "Unknown", ImageResource.SUNNY_SMALL, ImageResource.SUNNY_LARGE, ImageResource.CLEAR_NIGHT_SMALL, ImageResource.CLEAR_NIGHT_LARGE);
        }
    }
}
