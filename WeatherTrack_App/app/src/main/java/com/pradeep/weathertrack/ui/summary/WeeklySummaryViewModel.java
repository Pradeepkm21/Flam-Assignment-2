package com.pradeep.weathertrack.ui.summary;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import com.pradeep.weathertrack.data.repository.WeatherRepository;
import com.pradeep.weathertrack.utils.DateUtils;
import java.util.List;

public class WeeklySummaryViewModel extends AndroidViewModel {
    
    private WeatherRepository repository;
    private LiveData<List<WeatherEntity>> weeklyWeatherData;
    
    public WeeklySummaryViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
        
        // Get weather data from last 7 days
        long weekAgo = DateUtils.getDaysAgo(7);
        weeklyWeatherData = repository.getWeatherRecordsFromDate(weekAgo);
    }
    
    public LiveData<List<WeatherEntity>> getWeeklyWeatherData() {
        return weeklyWeatherData;
    }
}