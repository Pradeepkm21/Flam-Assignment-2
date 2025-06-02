package com.pradeep.weathertrack.ui.details;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import com.pradeep.weathertrack.data.repository.WeatherRepository;

public class WeatherDetailsViewModel extends AndroidViewModel {
    
    private WeatherRepository repository;
    private MutableLiveData<Integer> weatherId;
    private LiveData<WeatherEntity> weatherDetails;
    
    public WeatherDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
        weatherId = new MutableLiveData<>();
        
        weatherDetails = Transformations.switchMap(weatherId, id -> 
                repository.getWeatherRecordById(id));
    }
    
    public void loadWeatherDetails(int id) {
        weatherId.setValue(id);
    }
    
    public LiveData<WeatherEntity> getWeatherDetails() {
        return weatherDetails;
    }
}