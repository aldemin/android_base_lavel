package com.alexanr.demin.test_app.feature;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherInfoFragment extends Fragment {

    private TextView city;
    private TextView temperatureValue;
    private TextView pressure;
    private TextView pressureValue;
    private TextView humidity;
    private TextView humidityValue;
    private LinearLayout pressureLayout;
    private LinearLayout humidityLayout;

    protected static final String CITY_KEY = "city";
    protected static final String TEMP_KEY = "city";
    protected static final String PRESSURE_KEY = "pressure";
    protected static final String HUMIDITY_KEY = "humidity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_info_fragment, container, false);
        initViews(view);
        initParams(getArguments());
        return view;
    }

    private void initViews(View view) {
        this.city = view.findViewById(R.id.text_city);
        this.temperatureValue = view.findViewById(R.id.text_temperature_value);
        this.pressure = view.findViewById(R.id.text_pressure);
        this.pressureValue = view.findViewById(R.id.text_pressure_value);
        this.humidity = view.findViewById(R.id.text_humidity);
        this.humidityValue = view.findViewById(R.id.text_humidity_value);
        this.pressureLayout = view.findViewById(R.id.pressure_layout);
        this.humidityLayout = view.findViewById(R.id.humidity_layout);
    }

    private void initParams(Bundle bundle) {
        city.setText(bundle.getString(ChooseCityFragment.CITY_KEY));
        temperatureValue.setText(bundle.getString(ChooseCityFragment.TEMP_KEY));

        if (bundle.getBoolean(ChooseCityFragment.CHECK_HUMIDITY_KEY)) {
            this.humidityLayout.setVisibility(View.VISIBLE);
            this.humidityValue.setText(bundle.getString(ChooseCityFragment.HUMIDITY_KEY));
        } else {
            this.humidityLayout.setVisibility(View.GONE);
        }

        if (bundle.getBoolean(ChooseCityFragment.CHECK_PRESSURE_KEY)) {
            this.pressureLayout.setVisibility(View.VISIBLE);
            this.pressureValue.setText(bundle.getString(ChooseCityFragment.PRESSURE_KEY));
        } else {
            this.pressureLayout.setVisibility(View.GONE);
        }
    }
}
