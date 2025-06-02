package com.pradeep.weathertrack.ui.summary.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pradeep.weathertrack.databinding.ItemWeatherSummaryBinding;
import com.pradeep.weathertrack.data.database.entities.WeatherEntity;
import com.pradeep.weathertrack.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;

public class WeatherSummaryAdapter extends RecyclerView.Adapter<WeatherSummaryAdapter.WeatherViewHolder> {
    
    private List<WeatherEntity> weatherList;
    private OnWeatherClickListener listener;
    
    public interface OnWeatherClickListener {
        void onWeatherClick(WeatherEntity weather);
    }
    
    public WeatherSummaryAdapter(OnWeatherClickListener listener) {
        this.weatherList = new ArrayList<>();
        this.listener = listener;
    }
    
    public void updateWeatherList(List<WeatherEntity> newWeatherList) {
        this.weatherList = newWeatherList;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWeatherSummaryBinding binding = ItemWeatherSummaryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new WeatherViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherEntity weather = weatherList.get(position);
        holder.bind(weather);
    }
    
    @Override
    public int getItemCount() {
        return weatherList.size();
    }
    
    class WeatherViewHolder extends RecyclerView.ViewHolder {
        private ItemWeatherSummaryBinding binding;
        
        public WeatherViewHolder(ItemWeatherSummaryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        
        public void bind(WeatherEntity weather) {
            binding.tvDate.setText(DateUtils.formatDate(weather.getRecordedAt()));
            binding.tvTime.setText(DateUtils.formatTime(weather.getRecordedAt()));
            binding.tvTemperature.setText(String.format("%.1f°C", weather.getTemperature()));
            binding.tvCondition.setText(weather.getWeatherCondition());
            binding.tvHumidity.setText(String.format("%d%%", weather.getHumidity()));
            
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onWeatherClick(weather);
                }
            });
        }
    }
}