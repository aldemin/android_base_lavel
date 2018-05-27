package com.alexanr.demin.test_app.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editCity;
    private Button okButton;
    private CheckBox checkBoxPressure;
    private CheckBox checkBoxHumidity;
    private Intent intent;

    protected static final String CITY_KEY = "city";
    protected static final String CHECK_PRESSURE_KEY = "pressure";
    protected static final String CHECK_HUMIDITY_KEY = "humidity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editCity = findViewById(R.id.edit_city);
        checkBoxPressure = findViewById(R.id.check_box_pressure);
        checkBoxHumidity = findViewById(R.id.check_box_humidity);
        this.okButton = findViewById(R.id.button_ok);
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

        this.onClick(okButton);

    }

    private void onClick(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra(CITY_KEY, editCity.getText().toString());
                if (checkBoxPressure.isChecked()) {
                    intent.putExtra(CHECK_PRESSURE_KEY, true);
                }
                if (checkBoxHumidity.isChecked()) {
                    intent.putExtra(CHECK_HUMIDITY_KEY, true);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
    }
}
