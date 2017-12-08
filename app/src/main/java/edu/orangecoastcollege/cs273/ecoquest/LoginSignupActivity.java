package edu.orangecoastcollege.cs273.ecoquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * LoginSignupActivity will be implemented eventually but was taken out of the app's usual flow
 * for now for purposes of demonstrating the app.  Eventually, when the app is properly configured
 * to register Users, using Google's database service Firebase, this Activity will be fleshed out.
 */
public class LoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
    }
}
