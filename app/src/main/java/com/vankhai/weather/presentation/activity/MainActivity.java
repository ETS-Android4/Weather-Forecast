package com.vankhai.weather.presentation.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.vankhai.weather.base.WeatherLoadingState;
import com.vankhai.weather.databinding.ActivityMainBinding;
import com.vankhai.weather.presentation.fragments.DetailWeatherFragment;
import com.vankhai.weather.presentation.fragments.ErrorLoadingFragment;
import com.vankhai.weather.presentation.fragments.LoadingShimmerFragment;
import com.vankhai.weather.presentation.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.topSideSearchBox.settingsBtn.setOnClickListener(view -> onSettingButtonClick());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        final Observer<WeatherLoadingState> weatherLoadingStateObserver = new Observer<WeatherLoadingState>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(WeatherLoadingState state) {
                if (WeatherLoadingState.HaveData == state)
                    updateDetailWeatherUI();
                else if (WeatherLoadingState.Error == state)
                    updateErrorOccurredUI();
                else
                    updateLoadingWeatherUI();
            }
        };

        updateLoadingWeatherUI();

        viewModel.getLoadingState().observe(this, weatherLoadingStateObserver);
        /// Register MainViewModel listen to event occur in Repo to Update UI.
        viewModel.registerRepositoryEventListener();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /// Start loading Weather Forecast after start application.
                viewModel.getWeatherData();
            }
        }, 1000);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private void updateDetailWeatherUI() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.bodyPlaceholder.getId(), new DetailWeatherFragment(viewModel));
        fragmentTransaction.commit();
    }

    private void updateLoadingWeatherUI() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.bodyPlaceholder.getId(), new LoadingShimmerFragment());
        fragmentTransaction.commit();
    }

    private void updateErrorOccurredUI() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.bodyPlaceholder.getId(), new ErrorLoadingFragment());
        fragmentTransaction.commit();
    }

    void onSettingButtonClick() {
        startActivity(SettingActivity.intent(this));
    }
}