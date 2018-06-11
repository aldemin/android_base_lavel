package com.alexanr.demin.test_app.feature;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherInfoFragment extends Fragment {

    private TextView city;
    private TextView temperatureValue;
    private TextView pressure;
    private TextView pressureValue;
    private TextView humidity;
    private TextView humidityValue;

    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_info_fragment, container, false);

        this.bundle = getArguments();

        city = view.findViewById(R.id.text_city);
        temperatureValue = view.findViewById(R.id.text_temperature_value);
        pressure = view.findViewById(R.id.text_pressure);
        pressureValue = view.findViewById(R.id.text_pressure_value);
        humidity = view.findViewById(R.id.text_humidity);
        humidityValue = view.findViewById(R.id.text_humidity_value);

        city.setText(bundle.getString(ChooseCityFragment.CITY_KEY));
        temperatureValue.setText("78");

        if (bundle.getBoolean(ChooseCityFragment.CHECK_HUMIDITY_KEY)) {
            this.humidity.setVisibility(View.VISIBLE);
            this.humidity.setText(R.string.humidity);
            this.humidityValue.setVisibility(View.VISIBLE);
            this.humidityValue.setText("22");
        } else {
            this.humidity.setVisibility(View.GONE);
            this.humidityValue.setVisibility(View.GONE);
        }

        if (bundle.getBoolean(ChooseCityFragment.CHECK_PRESSURE_KEY)) {
            this.pressure.setVisibility(View.VISIBLE);
            this.pressure.setText(R.string.pressure);
            this.pressureValue.setVisibility(View.VISIBLE);
            this.pressureValue.setText("31");
        } else {
            this.pressure.setVisibility(View.GONE);
            this.pressureValue.setVisibility(View.GONE);
        }
        return view;
    }
}