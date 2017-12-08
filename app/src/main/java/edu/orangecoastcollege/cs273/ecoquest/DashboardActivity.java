package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * DashboardActivity is like the landing page / controller of ecoQuest.
 * This central hub is where Users will branch off to various Activities based on the button they tap.
 */
public class DashboardActivity extends AppCompatActivity {

    Button questsButton;

    /**
     * onCreate sets the content view and wires up the various View widgets.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        questsButton = (Button) findViewById(R.id.questsButton);
    }

    /**
     * The questsPage method is called when the user taps VIEW QUESTS.
     * An Intent is created which directs the user to QuestsActivity.
     * @param view
     */
    public void questsPage(View view) {
        Intent intent = new Intent(this, QuestsActivity.class);
        startActivity(intent);
    }

    /**
     * The completeAQuest method is called when the user taps TURN IN QUEST.
     * An Intent is created which directs the user to TurnInQuestActivity.
     * @param view
     */
    public void completeAQuest(View view) {
        Intent intent = new Intent(this, TurnInQuestActivity.class);
        startActivity(intent);
    }

    /**
     * The viewLeaderboards method is called when the user taps the BOARDS button.
     * An Intent directs the user to LeaderboardsActivity.
     * @param view
     */
    public void viewLeaderboards(View view) {
        Intent intent = new Intent(this, LeaderboardsActivity.class);
        startActivity(intent);
    }

    /**
     * The viewProfilePage method is called when the user taps the PROFILE button.
     * An Intent directs the user to UserProfileActivity.
     * @param view
     */
    public void viewProfilePage(View view)
    {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    /**
     * The viewBadgeList method is called when the user taps the BADGES button.
     * An Intent directs the user to BadgesListActivity.
     * @param view
     */
    public void viewBadgeList(View view)
    {
        Intent intent = new Intent(this, BadgesListActivity.class);
        startActivity(intent);
    }

    /**
     * The viewMapActivity method is called when the user taps the MAP button.
     * An Intent directs the user to MapActivity.
     * @param view
     */
    public void viewMapActivity(View view)
    {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

}
