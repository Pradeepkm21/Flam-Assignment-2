package com.pradeep.weathertrack.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.pradeep.weathertrack.R;
import com.pradeep.weathertrack.databinding.ActivityMainBinding;
import com.pradeep.weathertrack.ui.summary.WeeklySummaryActivity;
import com.pradeep.weathertrack.utils.DateUtils;
import com.pradeep.weathertrack.workers.WeatherSyncWorker;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        
        setupUI();
        setupObservers();
        setupBackgroundSync();
        
        // Load initial data
        viewModel.refreshWeatherData();
    }
    
    private void setupUI() {
        binding.btnRefresh.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            viewModel.refreshWeatherData();
        });
        
        binding.btnViewSummary.setOnClickListener(v -> {
            Intent intent = new Intent(this, WeeklySummaryActivity.class);
            startActivity(intent);
        });
    }
    
    private void setupObservers() {
        viewModel.getLatestWeather().observe(this, weather -> {
            binding.progressBar.setVisibility(View.GONE);
            if (weather != null) {
                updateWeatherDisplay(weather);
            }
        });
        
        viewModel.getErrorMessage().observe(this, error -> {
            binding.progressBar.setVisibility(View.GONE);
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });
        
        viewModel.getIsLoading().observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.btnRefresh.setEnabled(!isLoading);
        });
    }
    
    private void updateWeatherDisplay(com.pradeep.weathertrack.data.database.entities.WeatherEntity weather) {
        binding.tvTemperature.setText(String.format("%.1f°C", weather.getTemperature()));
        binding.tvCondition.setText(weather.getWeatherCondition());
        binding.tvHumidity.setText(String.format("Humidity: %d%%", weather.getHumidity()));
        binding.tvFeelsLike.setText(String.format("Feels like: %.1f°C", weather.getFeelsLike()));
        binding.tvPressure.setText(String.format("Pressure: %d hPa", weather.getPressure()));
        binding.tvCity.setText(weather.getCityName());
        binding.tvLastUpdated.setText("Last updated: " + DateUtils.formatDateTime(weather.getRecordedAt()));
        binding.tvDescription.setText(weather.getDescription());
        
        // Show the weather card
        binding.cardWeather.setVisibility(View.VISIBLE);
    }
    
    private void setupBackgroundSync() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        
        PeriodicWorkRequest syncRequest = new PeriodicWorkRequest.Builder(
                WeatherSyncWorker.class, 6, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        
        WorkManager.getInstance(this).enqueue(syncRequest);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}