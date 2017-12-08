package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * SplashActivity is the first Controller and essentially the "main" entry point to ecoQuest.
 * In the AndroidManifest.xml file we specify this as the app's entry point.
 *
 * The only functionality of this class is to present the activity_splash.xml layout file.
 * That layout file is simply a RelativeLayout whose background is set to the isometric_splash.png
 * included in the drawable directory.
 *
 * A TimerTask is used to set the length of time that the splash screen will be visible.
 * Currently we are not using this pause time for anything more than a delay.  But in the
 * next versions of ecoQuest, I can see this pause being used to make calls to
 * Firebase or some other task.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * onCreate simply creates a TimerTask object which is passed
     * to a Timer object and scheduled for 2000 milliseconds.
     * After the time elapses, we fire the Intent to take us to DashboardActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }
}
