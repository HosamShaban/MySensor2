package com.example.mysensor2;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This comment looks like javadoc but it at an invalid location.
 * Therefore, the text will not get into TestClass.html and the check will produce a violation.
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {

  Sensor sensor;
  SensorManager sensorManager;
  TextView textView;
  HorizontalScrollView scrollView;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = findViewById(R.id.infoXYZ);
    scrollView = findViewById(R.id.hView);

    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
      sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    } else {
      Toast.makeText(this, "is not found", Toast.LENGTH_SHORT).show();
    }

  }

  @SuppressLint("SetTextI18n")
  @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
    if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
      int x = (int) sensorEvent.values[0];
      int y = (int) sensorEvent.values[1];
      int z = (int) sensorEvent.values[2];

      scrollView.smoothScrollTo(scrollView.getScrollX() + (y * 130), 0);

      textView.setText("X: " + x + "\nY: " + y + "\nZ: " + z);

    }

  }

  @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

  }

  @Override
    protected void onResume() {
    super.onResume();
    sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

  }

  @Override
    protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);
  }
}