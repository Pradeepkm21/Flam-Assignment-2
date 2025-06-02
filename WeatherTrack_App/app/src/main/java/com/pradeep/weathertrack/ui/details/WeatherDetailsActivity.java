package com.pradeep.weathertrack.ui.details;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.pradeep.weathertrack.databinding.ActivityWeatherDetailsBinding;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import com.pradeep.weathertrack.utils.DateUtils;

public class WeatherDetailsActivity extends AppCompatActivity {
    
    private ActivityWeatherDetailsBinding binding;
    private WeatherDetailsViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Weather Details");
        }
        
        viewModel = new ViewModelProvider(this).get(WeatherDetailsViewModel.class);
        
        int weatherId = getIntent().getIntExtra("weather_id", -1);
        if (weatherId != -1) {
            setupObservers();
            viewModel.loadWeatherDetails(weatherId);
        }
    }
    
    private void setupObservers() {
        viewModel.getWeatherDetails().observe(this, weather -> {
            if (weather != null) {
                displayWeatherDetails(weather);
            }
        });
    }
    
    private void displayWeatherDetails(WeatherEntity weather) {
        binding.tvTemperature.setText(String.format("%.1f°C", weather.getTemperature()));
        binding.tvCondition.setText(weather.getWeatherCondition());
        binding.tvDescription.setText(weather.getDescription());
        binding.tvHumidity.setText(String.format("Humidity: %d%%", weather.getHumidity()));
        binding.tvFeelsLike.setText(String.format("Feels like: %.1f°C", weather.getFeelsLike()));
        binding.tvPressure.setText(String.format("Pressure: %d hPa", weather.getPressure()));
        binding.tvCity.setText(weather.getCityName());
        binding.tvDateTime.setText(DateUtils.formatDateTime(weather.getRecordedAt()));
    }
}