package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button questsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        questsButton = (Button) findViewById(R.id.questsButton);
    }

    public void questsPage(View view) {
        Intent intent = new Intent(this, QuestsActivity.class);
        startActivity(intent);
    }

    public void viewLeaderboards(View view) {
        Intent intent = new Intent(this, LeaderboardsActivity.class);
        startActivity(intent);
    }

    public void viewProfilePage(View view)
    {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

}
