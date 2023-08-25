package com.example.android_menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Sensors extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static SensorManager sensorManager;
    private Sensor sensorSelected;

    private List<Sensor> sensorList = new ArrayList<>();
    private List<String> sensorNameList = new ArrayList<>();

    private Spinner sensorSpinner;
    private Button btnCheck, btnUse;

    private View resultView;
    private TextView result1, result2, result3;

    SensorEventListener sel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            result1.setText("x: " + values[0]);
            result2.setText("y: " + values[1]);
            result3.setText("z: " + values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors);

        btnCheck = (Button) findViewById(R.id.btn_check_sensor);
        resultView = (LinearLayout) findViewById(R.id.sensor_result);
        result1 = (TextView) findViewById(R.id.value1);
        result2 = (TextView) findViewById(R.id.value2);
        result3 = (TextView) findViewById(R.id.value3);
        sensorSpinner = (Spinner) findViewById(R.id.sensors);

        btnCheck.setOnClickListener(this);
        sensorSpinner.setOnItemSelectedListener(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if (sensorList.isEmpty()) {
            Toast.makeText(this, "No sensor found!", Toast.LENGTH_LONG).show();
            btnCheck.setEnabled(false);
            return;
        }

        for (Sensor s : sensorList) {
            sensorNameList.add(s.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sensorNameList);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sensorSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_check_sensor) {
            result1.setText("Name: " + sensorSelected.getName());
            result2.setText("Vendor: " + sensorSelected.getVendor());
            result3.setText("Version: " + sensorSelected.getVersion());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sensorSelected = (Sensor) sensorList.stream().filter(s -> s.getName().equals(sensorNameList.get(i))).findAny().orElse(null);
        if (sensorSelected != null) {
            btnCheck.setEnabled(true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStop() {
        if (sensorList.size() > 0)
            sensorManager.unregisterListener(sel);
        super.onStop();
    }
}
