package edu.orangecoastcollege.cs273.ecoquest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by CaseyTea on 12/7/17.
 */

public class ShakeDetector implements SensorEventListener{

    private static final long ELAPSED_TIME = 1000L;
    // Accelerometer data used float
    private static final float  THRESHOLD = 25;

    private long previousShake;

    private OnShakeListener mListener;

    public ShakeDetector(OnShakeListener listener)
    {
        mListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // Ignore all other events, except ACCELEROMETERS
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            // Neutralize the effect of gravity (subtract it out from each value)
            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            float netForce = (float) Math.sqrt(Math.pow(gForceX, 2) + Math.pow(gForceY, 2) + Math.pow(gForceZ, 2));
            if (netForce >= THRESHOLD)
            {
                // Get current time
                long currentTime = System.currentTimeMillis();
                if (currentTime > previousShake + ELAPSED_TIME)
                {
                    // Reset the previous shake to the current time
                    previousShake = currentTime;

                    // Register a shake event (it qualifies)
                    mListener.onShake();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Do nothing, not being used
    }

    // Define an interface for others to implement whenever
    // a true shake occurs. Interface = contract (method declaration WITHOUT implementation)
    // Some other class has to implement the method.

    public interface OnShakeListener
    {
        void onShake();
    }

}
