package com.pradeep.weathertrack.data.network.models;

public class WeatherResponse {
    private String location;
    private Current current;
    
    public static class Current {
        private double temperature;
        private int humidity;
        private String condition;
        private double feelslike;
        private int pressure;
        private String weather_desc;
        
        // Getters
        public double getTemperature() { return temperature; }
        public int getHumidity() { return humidity; }
        public String getCondition() { return condition; }
        public double getFeelslike() { return feelslike; }
        public int getPressure() { return pressure; }
        public String getWeather_desc() { return weather_desc; }
    }
    
    // Getters
    public String getLocation() { return location; }
    public Current getCurrent() { return current; }
}