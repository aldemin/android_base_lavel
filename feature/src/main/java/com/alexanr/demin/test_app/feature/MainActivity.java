package com.alexanr.demin.test_app.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
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
        this.editCity.setText(getResources().getString(R.string.city));
/*        checkBoxPressure = findViewById(R.id.check_box_pressure);
        checkBoxPressure.setChecked(false);
        checkBoxHumidity = findViewById(R.id.check_box_humidity);
        checkBoxHumidity.setChecked(true);*/
        this.okButton = findViewById(R.id.button_ok);
        this.okButton.setOnClickListener(new View.OnClickListener() {
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
        //increment = saveInstanceState.getInt(COUNTER_KEY);
        //textView.setText("Inc " + increment);
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        //saveInstanceState.putInt(COUNTER_KEY, increment);
    }
}
