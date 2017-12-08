package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * DashboardActivity is like the landing page / controller of ecoQuest.
 * This central hub is where Users will branch off to various Activities based on the button they tap.
 *
 * A custom shrinkGrowAnimation is started whenever a user presses a button.
 *
 * There are Button and an Animation member variables.
 *
 * The Button are wired up in onCreate.
 */
public class DashboardActivity extends AppCompatActivity {

    private Animation shrinkGrowAnimation;

    private Button mapButton;
    private Button profileButton;
    private Button badgesButton;
    private Button boardsButton;
    private Button questsButton;
    private Button turnInQuestButton;

    /**
     * onCreate sets the content view and wires up the various View widgets.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mapButton =         (Button) findViewById(R.id.mapButton);
        profileButton =     (Button) findViewById(R.id.profileButton);
        badgesButton =      (Button) findViewById(R.id.badgesButton);
        boardsButton =      (Button) findViewById(R.id.boardsButton);
        questsButton =      (Button) findViewById(R.id.questsButton);
        turnInQuestButton = (Button) findViewById(R.id.turnInQuestButton);
    }

    /**
     * The questsPage method is called when the user taps VIEW QUESTS.
     * The custom shrinkGrow animation is started.
     * An Intent is created which directs the user to QuestsActivity.
     * @param view
     */
    public void questsPage(View view) {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        questsButton.startAnimation(shrinkGrowAnimation);
        Intent intent = new Intent(this, QuestsActivity.class);
        startActivity(intent);
    }

    /**
     * The completeAQuest method is called when the user taps TURN IN QUEST.
     * The custom shrinkGrow animation is started.
     * An Intent is created which directs the user to TurnInQuestActivity.
     * @param view
     */
    public void completeAQuest(View view) {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        turnInQuestButton.startAnimation(shrinkGrowAnimation);
        Intent intent = new Intent(this, TurnInQuestActivity.class);
        startActivity(intent);
    }

    /**
     * The viewLeaderboards method is called when the user taps the BOARDS button.
     * The custom shrinkGrow animation is started.
     * An Intent directs the user to LeaderboardsActivity.
     * @param view
     */
    public void viewLeaderboards(View view) {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        boardsButton.startAnimation(shrinkGrowAnimation);
        Intent intent = new Intent(this, LeaderboardsActivity.class);
        startActivity(intent);
    }

    /**
     * The viewProfilePage method is called when the user taps the PROFILE button.
     * The custom shrinkGrow animation is started.
     * An Intent directs the user to UserProfileActivity.
     * @param view
     */
    public void viewProfilePage(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        profileButton.startAnimation(shrinkGrowAnimation);
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    /**
     * The viewBadgeList method is called when the user taps the BADGES button.
     * The custom shrinkGrow animation is started.
     * An Intent directs the user to BadgesListActivity.
     * @param view
     */
    public void viewBadgeList(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        badgesButton.startAnimation(shrinkGrowAnimation);
        Intent intent = new Intent(this, BadgesListActivity.class);
        startActivity(intent);
    }

    /**
     * The viewMapActivity method is called when the user taps the MAP button.
     * The custom shrinkGrow animation is started.
     * An Intent directs the user to MapActivity.
     * @param view
     */
    public void viewMapActivity(View view)
    {
        shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
        mapButton.startAnimation(shrinkGrowAnimation);
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

}
