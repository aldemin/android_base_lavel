package com.alexanr.demin.test_app.feature;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView city;
        private TextView temperature;
        private TextView pressure;
        private TextView humidity;
        private TextView date;

        private LinearLayout pressureLayout;
        private LinearLayout humidityLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.city = itemView.findViewById(R.id.item_city);
            this.temperature = itemView.findViewById(R.id.item_temperature);
            this.pressure = itemView.findViewById(R.id.item_pressure);
            this.humidity = itemView.findViewById(R.id.item_humidity);
            this.date = itemView.findViewById(R.id.item_date);
            this.pressureLayout = itemView.findViewById(R.id.item_layout_pressure);
            this.humidityLayout = itemView.findViewById(R.id.item_layout_humidity);
        }

        public void bind(WeatherItem item) {
            this.city.setText(item.getCity());
            this.temperature.setText(item.getTemperature());
            this.date.setText(item.getDate());
            if (item.getHumidity() != null) {
                this.humidityLayout.setVisibility(View.VISIBLE);
                this.humidity.setText(item.getHumidity());
            } else {
                this.humidityLayout.setVisibility(View.GONE);
            }
            if (item.getPressure() != null) {
                this.pressureLayout.setVisibility(View.VISIBLE);
                this.pressure.setText(item.getPressure());
            } else {
                this.pressureLayout.setVisibility(View.GONE);
            }
        }
    }

    private List<WeatherItem> weatherList = new ArrayList<>();

    public void setWeatherList(List<WeatherItem> list) {
        this.weatherList.addAll(list);
        notifyDataSetChanged();
    }

    public void setWeatherItem(WeatherItem item) {
        this.weatherList.add(item);
        notifyDataSetChanged();
    }

    public void cleanWeatherItems() {
        this.weatherList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_history_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(weatherList.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
