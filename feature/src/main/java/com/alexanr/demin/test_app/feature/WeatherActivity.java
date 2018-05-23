package com.alexanr.demin.test_app.feature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    private TextView city;
    private TextView temperatureValue;
    private TextView temperature;
    private TextView pressure;
    private TextView pressureValue;
    private TextView humidity;
    private TextView humidityValue;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getIntent().getExtras();

        city = findViewById(R.id.text_city);
        temperature = findViewById(R.id.text_temperature);
        temperatureValue = findViewById(R.id.text_temperature_value);
        pressure = findViewById(R.id.text_pressure);
        pressureValue = findViewById(R.id.text_pressure_value);
        humidity = findViewById(R.id.text_humidity);
        humidityValue = findViewById(R.id.text_humidity_value);

        city.setText(bundle.getString(MainActivity.CITY_KEY));
        temperatureValue.setText("78");

        if (bundle.getBoolean(MainActivity.CHECK_HUMIDITY_KEY)) {
            this.humidity.setVisibility(View.VISIBLE);
            this.humidity.setText(R.string.humidity);
            this.humidityValue.setVisibility(View.VISIBLE);
            this.humidityValue.setText("22");
        } else {
            this.humidity.setVisibility(View.GONE);
            this.humidityValue.setVisibility(View.GONE);
        }

        if (bundle.getBoolean(MainActivity.CHECK_PRESSURE_KEY)) {
            this.pressure.setVisibility(View.VISIBLE);
            this.pressure.setText(R.string.pressure);
            this.pressureValue.setVisibility(View.VISIBLE);
            this.pressureValue.setText("31");
        } else {
            this.pressure.setVisibility(View.GONE);
            this.pressureValue.setVisibility(View.GONE);
        }
    }
}
