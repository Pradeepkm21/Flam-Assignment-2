package com.pradeep.weathertrack.data.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_records")
public class WeatherEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private double temperature;
    private int humidity;
    private String weatherCondition;
    private long recordedAt;
    private String cityName;
    private double feelsLike;
    private int pressure;
    private String description;

    // Constructor
    public WeatherEntity(double temperature, int humidity, String weatherCondition, 
                        long recordedAt, String cityName, double feelsLike, 
                        int pressure, String description) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.weatherCondition = weatherCondition;
        this.recordedAt = recordedAt;
        this.cityName = cityName;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.description = description;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public String getWeatherCondition() { return weatherCondition; }
    public void setWeatherCondition(String weatherCondition) { this.weatherCondition = weatherCondition; }

    public long getRecordedAt() { return recordedAt; }
    public void setRecordedAt(long recordedAt) { this.recordedAt = recordedAt; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

    public int getPressure() { return pressure; }
    public void setPressure(int pressure) { this.pressure = pressure; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}