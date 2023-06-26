package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class SensorActivity extends AppCompatActivity {
    private TextView proximitySensorTextView, data;
    private LottieAnimationView sensorAnimation;
    private SensorManager sensorManager;
    private Sensor proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        proximitySensorTextView = findViewById(R.id.proximitySensor);
        data = findViewById(R.id.data);
        sensorAnimation = findViewById(R.id.sensorAnimation);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (proximitySensor == null) {
            proximitySensorTextView.setText("Sensor Proximity Tidak Terdeteksi!");
        } else {
            sensorManager.registerListener(proximitySensorEventListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        startAnimation();
    }
    private void startAnimation() {
        sensorAnimation.setAnimation(R.raw.sensor_animation);
        sensorAnimation.setRepeatCount(LottieDrawable.INFINITE);
        sensorAnimation.playAnimation();
    }


    private SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0) {
                    data.setText("Dekat");
                } else {
                    data.setText("Jauh");
                }
            }
        }
    };
}
