package com.pradeep.weathertrack.data.network;

import com.pradeep.weathertrack.data.network.models.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    
    // Mock API endpoint - replace with your actual API
    @GET("current")
    Call<WeatherResponse> getCurrentWeather(@Query("city") String city);
    
    // For demonstration, we'll create a mock response
    // In real implementation, you would use actual weather API like OpenWeatherMap
}
