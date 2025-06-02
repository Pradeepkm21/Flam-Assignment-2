package com.pradeep.weathertrack.ui.summary;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.pradeep.weathertrack.R;
import com.pradeep.weathertrack.databinding.ActivityWeeklySummaryBinding;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import com.pradeep.weathertrack.ui.details.WeatherDetailsActivity;
import com.pradeep.weathertrack.ui.summary.adapters.WeatherSummaryAdapter;
import com.pradeep.weathertrack.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;

public class WeeklySummaryActivity extends AppCompatActivity implements WeatherSummaryAdapter.OnWeatherClickListener {
    
    private ActivityWeeklySummaryBinding binding;
    private WeeklySummaryViewModel viewModel;
    private WeatherSummaryAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeeklySummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Weekly Summary");
        }
        
        viewModel = new ViewModelProvider(this).get(WeeklySummaryViewModel.class);
        setupRecyclerView();
        setupChart();
        setupObservers();
    }
    
    private void setupRecyclerView() {
        adapter = new WeatherSummaryAdapter(this);
        binding.recyclerViewSummary.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewSummary.setAdapter(adapter);
    }
    
    private void setupChart() {
        binding.temperatureChart.getDescription().setEnabled(false);
        binding.temperatureChart.setTouchEnabled(true);
        binding.temperatureChart.setDragEnabled(true);
        binding.temperatureChart.setScaleEnabled(true);
        binding.temperatureChart.setPinchZoom(true);
        
        XAxis xAxis = binding.temperatureChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
    }
    
    private void setupObservers() {
        viewModel.getWeeklyWeatherData().observe(this, weatherList -> {
            if (weatherList != null && !weatherList.isEmpty()) {
                adapter.updateWeatherList(weatherList);
                updateChart(weatherList);
                binding.tvNoData.setVisibility(android.view.View.GONE);
            } else {
                binding.tvNoData.setVisibility(android.view.View.VISIBLE);
            }
        });
    }
    
    private void updateChart(List<WeatherEntity> weatherList) {
        List<Entry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        for (int i = 0; i < weatherList.size(); i++) {
            WeatherEntity weather = weatherList.get(i);
            entries.add(new Entry(i, (float) weather.getTemperature()));
            labels.add(DateUtils.formatDate(weather.getRecordedAt()));
        }
        
        LineDataSet dataSet = new LineDataSet(entries, "Temperature (°C)");
        dataSet.setColor(getResources().getColor(R.color.primary_color, null));
        dataSet.setCircleColor(getResources().getColor(R.color.accent_color, null));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setValueTextSize(10f);
        
        LineData lineData = new LineData(dataSet);
        binding.temperatureChart.setData(lineData);
        
        XAxis xAxis = binding.temperatureChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setLabelCount(Math.min(labels.size(), 7));
        
        binding.temperatureChart.invalidate();
    }
    
    @Override
    public void onWeatherClick(WeatherEntity weather) {
        Intent intent = new Intent(this, WeatherDetailsActivity.class);
        intent.putExtra("weather_id", weather.getId());
        startActivity(intent);
    }
}