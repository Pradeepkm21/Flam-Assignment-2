package com.pradeep.weathertrack.workers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.pradeep.weathertrack.data.repository.WeatherRepository;
import com.pradeep.weathertrack.utils.Constants;

public class WeatherSyncWorker extends Worker {
    
    public WeatherSyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    
    @NonNull
    @Override
    public Result doWork() {
        try {
            WeatherRepository repository = new WeatherRepository(getApplicationContext());
            
            // Fetch weather for default city
            final boolean[] success = {false};
            final String[] error = {null};
            
            repository.fetchAndSaveWeather(Constants.DEFAULT_CITY, new WeatherRepository.WeatherFetchCallback() {
                @Override
                public void onSuccess() {
                    success[0] = true;
                }
                
                @Override
                public void onError(String errorMessage) {
                    error[0] = errorMessage;
                }
            });
            
            // Wait for completion (simplified for demo)
            Thread.sleep(3000);
            
            if (success[0]) {
                // Also cleanup old records
                repository.cleanupOldRecords();
                return Result.success();
            } else {
                return Result.retry();
            }
            
        } catch (Exception e) {
            return Result.failure();
        }
    }
}