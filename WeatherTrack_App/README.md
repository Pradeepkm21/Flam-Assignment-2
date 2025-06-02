# 🌦️ WeatherTrack

**WeatherTrack** is a simple Android application for users to track daily weather statistics in their city. It fetches weather data from a mock API, stores the information locally, and presents a visual summary of temperature trends over the past week. The app uses a Room database for local storage and WorkManager for periodic background sync.

---

## 📱 Features

- **Fetch Weather Data**:  
  Retrieves temperature, humidity, and weather conditions from a mock API and stores it locally with a timestamp.

- **Automatic Background Sync**:  
  Automatically fetches and stores updated weather data every 6 hours using WorkManager.

- **Manual Refresh**:  
  Allows users to manually refresh current weather data.

- **Weekly Summary**:  
  Displays temperature trends for the past 7 days in a graph or list format. Users can click on a day to view detailed weather information.

- **User-Friendly Error Handling**:  
  Displays clear error messages in cases of no internet connection, API failures, or database errors.

---

## 🛠️ Tech Stack

- **Language**: Java  
- **Architecture**: MVVM (Model-View-ViewModel)  
- **Libraries/Components**:
  - Retrofit (for networking)
  - Room (for local database)
  - WorkManager (for background tasks)
  - LiveData & ViewModel (for reactive UI)
  - RecyclerView (for lists)
  - Mock API (using static JSON)

---

## 📂 Project Structure

WeatherTrack/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/yourname/weathertrack/
│   │   │   │   ├── data/
│   │   │   │   │   ├── database/
│   │   │   │   │   │   ├── WeatherDatabase.java
│   │   │   │   │   │   ├── WeatherDao.java
│   │   │   │   │   │   └── entities/
│   │   │   │   │   │       └── WeatherEntity.java
│   │   │   │   │   ├── network/
│   │   │   │   │   │   ├── WeatherApiService.java
│   │   │   │   │   │   └── models/
│   │   │   │   │   │       └── WeatherResponse.java
│   │   │   │   │   └── repository/
│   │   │   │   │       └── WeatherRepository.java
│   │   │   │   ├── ui/
│   │   │   │   │   ├── main/
│   │   │   │   │   │   ├── MainActivity.java
│   │   │   │   │   │   └── MainViewModel.java
│   │   │   │   │   ├── summary/
│   │   │   │   │   │   ├── WeeklySummaryActivity.java
│   │   │   │   │   │   ├── WeeklySummaryViewModel.java
│   │   │   │   │   │   └── adapters/
│   │   │   │   │   │       └── WeatherSummaryAdapter.java
│   │   │   │   │   └── details/
│   │   │   │   │       ├── WeatherDetailsActivity.java
│   │   │   │   │       └── WeatherDetailsViewModel.java
│   │   │   │   ├── utils/
│   │   │   │   │   ├── NetworkUtils.java
│   │   │   │   │   ├── DateUtils.java
│   │   │   │   │   └── Constants.java
│   │   │   │   ├── workers/
│   │   │   │   │   └── WeatherSyncWorker.java
│   │   │   │   └── WeatherTrackApplication.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   └── drawable/
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   └── build.gradle
├── gradle/
├── README.md
└── LICENSE




---

## ⚙️ Setup & Run Instructions

1. **Install Android Studio**  
   Download from: [Android Studio Official Site](https://developer.android.com/studio)

2. **Create a New Project**
   - Select **Empty Activity**
   - Set package name as `com.yourname.weathertrack`
   - Choose Java as the language

3. **Copy the Folder Structure**  
   Follow the structure provided above for placing Java classes, resources, and layouts.

4. **Configure Dependencies**
   - Add Room, Retrofit, WorkManager, and other required libraries in `build.gradle`:
     ```gradle
     implementation "androidx.room:room-runtime:2.6.1"
     implementation "androidx.work:work-runtime:2.9.0"
     implementation "com.squareup.retrofit2:retrofit:2.9.0"
     implementation "androidx.lifecycle:lifecycle-livedata:2.7.0"
     implementation "androidx.lifecycle:lifecycle-viewmodel:2.7.0"
     ```

5. **Build & Run the Project**
   - Connect an Android device or start an emulator.
   - Run the app from Android Studio.

---

## 📌 Notes

- Currently uses a **mock API** — replace with a real weather API for production.
- All components are modular and cleanly separated following MVVM best practices.
- Suitable for academic projects and beginner Android developers learning modern app architecture.

---

## 📜 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

## 🙌 Acknowledgements

- Android Developer Documentation
- Android Room, WorkManager, and Retrofit official guides

