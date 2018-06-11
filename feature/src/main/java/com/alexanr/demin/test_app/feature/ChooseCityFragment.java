package com.alexanr.demin.test_app.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ChooseCityFragment extends Fragment {

    private EditText editCity;
    private Button okButton;
    private CheckBox checkBoxPressure;
    private CheckBox checkBoxHumidity;

    protected static final String CITY_KEY = "city";
    protected static final String CHECK_PRESSURE_KEY = "pressure";
    protected static final String CHECK_HUMIDITY_KEY = "humidity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_city_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.editCity = getActivity().findViewById(R.id.edit_city);
        this.checkBoxPressure = getActivity().findViewById(R.id.check_box_pressure);
        this.checkBoxHumidity = getActivity().findViewById(R.id.check_box_humidity);
        this.okButton = getActivity().findViewById(R.id.button_ok);
        this.okButton.setEnabled(false);

        this.editCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    okButton.setEnabled(true);
                } else {
                    okButton.setEnabled(false);
                }
            }
        });

        this.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeatherInfo();
            }
        });
    }

    private void showWeatherInfo() {
        Intent intent = this.generateIntentForWeatherInfo();
        if (this.isWeatherInfoExist()) {
            if (getFragmentManager().findFragmentById(R.id.weather_fragment) == null) {
                Fragment fragment = new WeatherInfoFragment();
                fragment.setArguments(intent.getExtras());
                getFragmentManager().beginTransaction().add(R.id.weather_fragment, fragment).commit();
            } else {
                Fragment fragment = new WeatherInfoFragment();
                fragment.setArguments(intent.getExtras());
                getFragmentManager().beginTransaction().replace(R.id.weather_fragment, fragment).commit();
            }
        } else {
            startActivity(intent);
        }
    }

    private Intent generateIntentForWeatherInfo() {
        Intent intent = new Intent(getActivity(), WeatherActivity.class);
        intent.putExtra(CITY_KEY, editCity.getText().toString());
        if (checkBoxPressure.isChecked()) {
            intent.putExtra(CHECK_PRESSURE_KEY, true);
        }
        if (checkBoxHumidity.isChecked()) {
            intent.putExtra(CHECK_HUMIDITY_KEY, true);
        }
        return intent;
    }

    private boolean isWeatherInfoExist() {
        View view = getActivity().findViewById(R.id.weather_fragment);
        return view != null && view.getVisibility() == View.VISIBLE;
    }
}
