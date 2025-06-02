package com.pradeep.weathertrack.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.pradeep.weathertrack.data.database.WeatherDao;
import com.pradeep.weathertrack.data.database.WeatherDatabase;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import com.pradeep.weathertrack.data.network.WeatherApiService;
import com.pradeep.weathertrack.data.network.models.WeatherResponse;
import com.pradeep.weathertrack.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherRepository {
    private WeatherDao weatherDao;
    private WeatherApiService apiService;
    private ExecutorService executor;
    private Context context;
    
    public WeatherRepository(Context context) {
        this.context = context;
        WeatherDatabase database = WeatherDatabase.getDatabase(context);
        weatherDao = database.weatherDao();
        executor = Executors.newFixedThreadPool(2);
        
        // Initialize Retrofit - using mock data for demo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mockweather.com/") // Mock URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        apiService = retrofit.create(WeatherApiService.class);
    }
    
    // Fetch weather from API and save to database
    public void fetchAndSaveWeather(String cityName, WeatherFetchCallback callback) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            callback.onError("No internet connection available");
            return;
        }
        
        // For demo purposes, generate mock weather data
        // In real app, you would call: apiService.getCurrentWeather(cityName)
        generateMockWeatherData(cityName, callback);
    }
    
    private void generateMockWeatherData(String cityName, WeatherFetchCallback callback) {
        executor.execute(() -> {
            try {
                // Simulate network delay
                Thread.sleep(1000);
                
                // Generate realistic mock data
                Random random = new Random();
                double temperature = 15 + (random.nextDouble() * 25); // 15-40°C
                int humidity = 30 + random.nextInt(50); // 30-80%
                String[] conditions = {"Sunny", "Cloudy", "Rainy", "Partly Cloudy", "Overcast"};
                String condition = conditions[random.nextInt(conditions.length)];
                double feelsLike = temperature + (random.nextDouble() * 6 - 3); // ±3°C
                int pressure = 1000 + random.nextInt(50); // 1000-1050 hPa
                String description = generateWeatherDescription(condition);
                
                WeatherEntity weatherEntity = new WeatherEntity(
                    Math.round(temperature * 10.0) / 10.0, // Round to 1 decimal
                    humidity,
                    condition,
                    System.currentTimeMillis(),
                    cityName,
                    Math.round(feelsLike * 10.0) / 10.0,
                    pressure,
                    description
                );
                
                weatherDao.insertWeatherRecord(weatherEntity);
                callback.onSuccess();
                
            } catch (Exception e) {
                callback.onError("Failed to fetch weather data: " + e.getMessage());
            }
        });
    }
    
    private String generateWeatherDescription(String condition) {
        switch (condition) {
            case "Sunny": return "Clear skies with bright sunshine";
            case "Cloudy": return "Overcast with thick cloud cover";
            case "Rainy": return "Light to moderate rainfall expected";
            case "Partly Cloudy": return "Mix of sun and clouds";
            case "Overcast": return "Heavy cloud cover, no sun visible";
            default: return "Current weather conditions";
        }
    }
    
    // Database operations
    public LiveData<List<WeatherEntity>> getAllWeatherRecords() {
        return weatherDao.getAllWeatherRecords();
    }
    
    public LiveData<List<WeatherEntity>> getWeatherRecordsFromDate(long startTime) {
        return weatherDao.getWeatherRecordsFromDate(startTime);
    }
    
    public LiveData<WeatherEntity> getWeatherRecordById(int id) {
        return weatherDao.getWeatherRecordById(id);
    }
    
    public LiveData<WeatherEntity> getLatestWeatherRecord() {
        return weatherDao.getLatestWeatherRecord();
    }
    
    public void cleanupOldRecords() {
        executor.execute(() -> {
            long cutoffTime = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000); // 30 days ago
            weatherDao.deleteOldRecords(cutoffTime);
        });
    }
    
    public interface WeatherFetchCallback {
        void onSuccess();
        void onError(String error);
    }
}