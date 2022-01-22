package com.vankhai.weather.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

import com.vankhai.weather.BuildConfig;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class WeatherForecastApplication extends Application {

    private static WeatherForecastApplication instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new NotLoggingTree());
        }
    }
}

class NotLoggingTree extends Timber.Tree {
    @Override
    protected void log(int priority, @Nullable String tag,
                       @NotNull String message, @Nullable Throwable t) {
        // Do nothing here
    }
}
