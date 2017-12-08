package edu.orangecoastcollege.cs273.ecoquest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by CaseyTea on 12/7/17.
 *
 * ShakeDetoctor class used SensorEventListener to detect if
 * the device has been shaken.
 *
 */

public class ShakeDetector implements SensorEventListener{

    private static final long ELAPSED_TIME = 1000L;
    // Accelerometer data used float
    private static final float  THRESHOLD = 25;

    private long previousShake;

    private OnShakeListener mListener;

    /**
     * Method to set the listener.
     * @param listener the new listener to be set.
     */
    public ShakeDetector(OnShakeListener listener)
    {
        mListener = listener;
    }

    /**
     * Method to detect changes to the sensor all the time.
     * Checking which sensor type is being used.
     * Checks to see if the conditions have been met inorder to set off
     * the shake event.
     * @param sensorEvent when a sensor even is detected and passes in.
     */
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

    /**
     * Must be implemented because SensorEventListener interface is being used
     * Method currently does nothing.
     *
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Do nothing, not being used
    }

    // Define an interface for others to implement whenever
    // a true shake occurs. Interface = contract (method declaration WITHOUT implementation)
    // Some other class has to implement the method.

    /**
     * True shake occurs.
     */
    public interface OnShakeListener
    {
        void onShake();
    }

}
