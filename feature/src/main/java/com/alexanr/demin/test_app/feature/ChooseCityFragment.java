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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.Date;

public class ChooseCityFragment extends Fragment {

    private EditText editCity;
    private Button okButton;
    private CheckBox checkBoxPressure;
    private CheckBox checkBoxHumidity;
    private ToggleButton paramButton;
    private Button historyButton;

    private LinearLayout paramLayout;
    private WeatherItemAdapter adapter;

    protected static final String CITY_KEY = "city";
    protected static final String TEMP_KEY = "temperature";
    protected static final String CHECK_PRESSURE_KEY = "check pressure";
    protected static final String PRESSURE_KEY = "pressure";
    protected static final String CHECK_HUMIDITY_KEY = "check humidity";
    protected static final String HUMIDITY_KEY = "humidity";
    protected static final String DATE_KEY = "date";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_city_fragment, container, false);
        this.initViews(view);
        this.setTextWatcher();
        this.okButtonListener();
        this.paramButtonListener();
        this.historyButtonListener();
        return view;
    }

    private void initViews(View view) {
        this.editCity = view.findViewById(R.id.edit_city);
        this.okButton = view.findViewById(R.id.button_ok);
        this.historyButton = view.findViewById(R.id.button_history);
        this.paramButton = view.findViewById(R.id.toggle_other_param);
        this.paramLayout = view.findViewById(R.id.param_layout);
        this.checkBoxPressure = view.findViewById(R.id.check_box_pressure);
        this.checkBoxHumidity = view.findViewById(R.id.check_box_humidity);
        this.checkBoxPressure.setChecked(false);
        this.checkBoxHumidity.setChecked(false);
        this.okButton.setEnabled(false);
        this.adapter = new WeatherItemAdapter();
    }

    private void showInfo() {
        Intent intent = this.generateIntentForWeatherInfo();
        if (this.isAdditionalFragmentExist()) {
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

    private void showHistory() {
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        if (this.isAdditionalFragmentExist()) {
            if (getFragmentManager().findFragmentById(R.id.weather_fragment) == null) {
                Fragment fragment = new WeatherHistoryFragment();
                getFragmentManager().beginTransaction().add(R.id.weather_fragment, fragment).commit();
            } else {
                Fragment fragment = new WeatherHistoryFragment();
                getFragmentManager().beginTransaction().replace(R.id.weather_fragment, fragment).commit();
            }
        } else {
            startActivity(intent);
        }
    }

    private Intent generateIntentForWeatherInfo() {
        Intent intent = new Intent(getActivity(), WeatherActivity.class);
        intent.putExtra(CITY_KEY, editCity.getText().toString());
        intent.putExtra(TEMP_KEY, "20");
        intent.putExtra(DATE_KEY, new Date().toString());
        if (checkBoxPressure.isChecked()) {
            intent.putExtra(CHECK_PRESSURE_KEY, true);
            intent.putExtra(PRESSURE_KEY, "30");
        }
        if (checkBoxHumidity.isChecked()) {
            intent.putExtra(CHECK_HUMIDITY_KEY, true);
            intent.putExtra(HUMIDITY_KEY, "34");
        }
        this.setNewItem(intent);
        return intent;
    }

    private boolean isAdditionalFragmentExist() {
        View view = getActivity().findViewById(R.id.weather_fragment);
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    private void setTextWatcher() {
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
    }

    private void okButtonListener() {
        this.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo();
            }
        });
    }

    private void historyButtonListener() {
        this.historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistory();
            }
        });
    }

    private void paramButtonListener() {
        this.paramButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    paramLayout.setVisibility(View.VISIBLE);
                } else {
                    paramLayout.setVisibility(View.GONE);
                    checkBoxHumidity.setChecked(false);
                    checkBoxPressure.setChecked(false);
                }
            }
        });
    }

    private void setNewItem(Intent intent) {
        Bundle bundle = intent.getExtras();
        WeatherItem item = new WeatherItem(bundle.getString(CITY_KEY),
                bundle.getString(TEMP_KEY),
                bundle.getString(DATE_KEY));
        if (bundle.getBoolean(CHECK_PRESSURE_KEY)) {
            item.setPressure(bundle.getString(PRESSURE_KEY));
        }
        if (bundle.getBoolean(CHECK_HUMIDITY_KEY)) {
            item.setHumidity(bundle.getString(HUMIDITY_KEY));
        }
        WeatherItems.getInstance().addNewItem(item);
    }

}
