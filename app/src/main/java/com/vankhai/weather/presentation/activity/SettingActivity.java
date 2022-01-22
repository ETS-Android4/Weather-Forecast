package com.vankhai.weather.presentation.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.vankhai.weather.R;
import com.vankhai.weather.adapter.RecommendArrayAdapter;
import com.vankhai.weather.base.Constants;
import com.vankhai.weather.base.WeatherForecastApplication;
import com.vankhai.weather.databinding.ActivitySettingBinding;
import com.vankhai.weather.model.LocationRecommend;
import com.vankhai.weather.presentation.viewmodel.SettingViewModel;

import java.util.List;

import timber.log.Timber;

public class SettingActivity extends AppCompatActivity {

    public static Intent intent(Context context) {
        return new Intent(context, SettingActivity.class);
    }

    private ActivitySettingBinding binding;
    private SettingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        viewModel.registerRepositoryEventListener();

        binding.leftArrowImgBtn.setOnClickListener(view -> finish());
        binding.getLocationByLatLongBtn.setOnClickListener(view -> onGetLocationByLatLongBtnClick());
        binding.editLocationInputBtn.setOnClickListener(view -> onEditLocationInputBtnClick());
        binding.cancelInputLocationBtn.setOnClickListener(view -> onCancelEditLocationInputBtnClick());

//        binding.celsiusRadioRow.titleTv.setText(R.string.celsius);
//        binding.fahrenheitRadioRow.titleTv.setText(R.string.fahrenheit);
//
//        binding.celsiusRadioRow.radioBtn.setOnClickListener(view -> onCelsiusRadioBtnClick());
//        binding.fahrenheitRadioRow.radioBtn.setOnClickListener(view -> onFahrenheitRadioBtnClick());

        /// Observe location name change
        final Observer<String> locationNameObserver = new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(String name) {
                if (View.VISIBLE == binding.locationValueTv.getVisibility()) {
                    binding.locationValueTv.setText(name);
                }
            }
        };
        viewModel.getLocationName().observe(this, locationNameObserver);

        /// Observe edit location by input state
        final Observer<Boolean> editLocationByInputObserver = new Observer<Boolean>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(Boolean isEdit) {
                if (isEdit) {
                    binding.locationValueTv.setVisibility(View.GONE);
                    binding.countryValueTv.setVisibility(View.GONE);
                    binding.getLocationByLatLongBtn.setVisibility(View.GONE);
                    binding.editLocationInputBtn.setVisibility(View.GONE);
                    binding.locationTitleTv.setVisibility(View.GONE);
                    binding.countryTitleTv.setVisibility(View.GONE);

                    binding.cancelInputLocationBtn.setVisibility(View.VISIBLE);
                    AutoCompleteTextView acTv = binding.inputLocationAutoCompleteTv;
                    acTv.setVisibility(View.VISIBLE);
                    acTv.setText(Constants.EMPTY);
                    acTv.setThreshold(1);
                    acTv.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence pattern, int i, int i1, int i2) {

                            if (pattern.length() >= acTv.getThreshold()) {
                                viewModel.onTypeQueryLocation(pattern.toString());
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                    acTv.setOnItemClickListener((adapterView, view, position, id) -> {
                        LocationRecommend locationSelected = (LocationRecommend) acTv.getAdapter().getItem(position);
                        onRecommendItemClick(locationSelected);
                    });
                } else {
                    binding.inputLocationAutoCompleteTv.setVisibility(View.GONE);
                    binding.cancelInputLocationBtn.setVisibility(View.GONE);

                    binding.editLocationInputBtn.setVisibility(View.VISIBLE);
                    binding.getLocationByLatLongBtn.setVisibility(View.VISIBLE);
                    binding.locationValueTv.setVisibility(View.VISIBLE);
                    binding.locationValueTv.setText(viewModel.getCurrentLocation());
                    binding.countryValueTv.setVisibility(View.VISIBLE);
                    binding.countryValueTv.setText(viewModel.getCurrentCountryName());
                    binding.locationTitleTv.setVisibility(View.VISIBLE);
                    binding.countryTitleTv.setVisibility(View.VISIBLE);
                }
            }
        };
        viewModel.getIsEditLocationByInput().observe(this, editLocationByInputObserver);

        /// Observe recommend location from API
        final Observer<List<LocationRecommend>> recommendLocationObserver = new Observer<List<LocationRecommend>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(List<LocationRecommend> recommends) {
                if (View.VISIBLE == binding.inputLocationAutoCompleteTv.getVisibility()) {
                    Timber.i("Recommend count: " + recommends.size() + "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
                    RecommendArrayAdapter locationRecommendArrayAdapter = new RecommendArrayAdapter(SettingActivity.this, recommends);
                    binding.inputLocationAutoCompleteTv.setAdapter(locationRecommendArrayAdapter);
                    binding.inputLocationAutoCompleteTv.showDropDown();
                }
            }
        };
        viewModel.getLocationRecommends().observe(this, recommendLocationObserver);
    }

    private final int LOCATION_PERMISSION_REQUEST_CODE = 111;

    private void onGetLocationByLatLongBtnClick() {
        if (ContextCompat.checkSelfPermission(
                WeatherForecastApplication.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SettingActivity.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            viewModel.triggerChooseLocationByCurrentLatLng();
        }
    }

    private void onEditLocationInputBtnClick() {
        viewModel.triggerEditLocationByInput();
    }

    private void onCancelEditLocationInputBtnClick() {
        viewModel.triggerCancelEditLocationByInput();
    }

    private void onCelsiusRadioBtnClick() {
        ///TODO: Handle this later
    }

    private void onFahrenheitRadioBtnClick() {
        ///TODO: Handle this later
    }

    private void onRecommendItemClick(LocationRecommend selected) {
        ///TODO: Handle popup maybe
        viewModel.triggerConfirmNewLocationName(selected);
    }
}