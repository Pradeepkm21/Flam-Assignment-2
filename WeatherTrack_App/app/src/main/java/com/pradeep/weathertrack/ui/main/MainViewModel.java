package com.pradeep.weathertrack.ui.main;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import com.pradeep.weathertrack.data.repository.WeatherRepository;
import com.pradeep.weathertrack.utils.Constants;

public class MainViewModel extends AndroidViewModel {
    
    private WeatherRepository repository;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<Boolean> isLoading;
    
    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }
    
    public LiveData<WeatherEntity> getLatestWeather() {
        return repository.getLatestWeatherRecord();
    }
    
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
    
    public void refreshWeatherData() {
        isLoading.setValue(true);
        errorMessage.setValue(""); // Clear previous errors
        
        repository.fetchAndSaveWeather(Constants.DEFAULT_CITY, new WeatherRepository.WeatherFetchCallback() {
            @Override
            public void onSuccess() {
                isLoading.postValue(false);
            }
            
            @Override
            public void onError(String error) {
                isLoading.postValue(false);
                errorMessage.postValue(error);
            }
        });
    }
}