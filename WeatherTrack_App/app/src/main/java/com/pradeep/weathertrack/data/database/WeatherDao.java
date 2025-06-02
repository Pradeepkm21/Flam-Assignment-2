package com.pradeep.weathertrack.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import java.util.List;

@Dao
public interface WeatherDao {
    
    @Insert
    void insertWeatherRecord(WeatherEntity weatherEntity);
    
    @Query("SELECT * FROM weather_records ORDER BY recordedAt DESC")
    LiveData<List<WeatherEntity>> getAllWeatherRecords();
    
    @Query("SELECT * FROM weather_records WHERE recordedAt >= :startTime ORDER BY recordedAt DESC")
    LiveData<List<WeatherEntity>> getWeatherRecordsFromDate(long startTime);
    
    @Query("SELECT * FROM weather_records WHERE id = :id")
    LiveData<WeatherEntity> getWeatherRecordById(int id);
    
    @Query("SELECT * FROM weather_records ORDER BY recordedAt DESC LIMIT 1")
    LiveData<WeatherEntity> getLatestWeatherRecord();
    
    @Query("DELETE FROM weather_records WHERE recordedAt < :cutoffTime")
    void deleteOldRecords(long cutoffTime);
    
    @Query("SELECT COUNT(*) FROM weather_records")
    int getRecordCount();
}