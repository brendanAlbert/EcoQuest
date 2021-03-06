package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

/**
 * BadgeListActivity populates a list of badges to display in a list view.
 *
 * Currently this page is only able to populate a list and access the badge details page.
 * In the future this page might have other functions once Titles and other mechanics of the game
 * have been implemented.
 *
 * (Casey)
 */
public class BadgesListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Badge> badgesList;
    private BadgeListAdapter badgeListAdapter;
    private ListView badgeListView;

    /**
     * onCreate populates the badgeListView to dispay all the the possible badges a user can earn
     * and the progress needed in order to complete each.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges_list);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importBadgesFromCSV("badges.csv");

        badgesList = db.getAllBadges();
        badgeListAdapter = new BadgeListAdapter(this, R.layout.badge_list_item, badgesList);
        badgeListView = (ListView) findViewById(R.id.fullBadgeListView);
        badgeListView.setAdapter(badgeListAdapter);
    }

    /**
     * viewBadgeDetails method fires intent to view the details of each badge once clicked
     * from the list view.
     *
     * @param view
     */
    public void viewBadgeDetails(View view)
    {
        if (view instanceof LinearLayout)
        {
            LinearLayout selectedLayout = (LinearLayout) view;
            Badge selectedBadge = (Badge) selectedLayout.getTag();
            Log.i("EcoQuest", selectedBadge.toString());
            Intent detailsIntent = new Intent (this, BadgeDetailsActivity.class);

            detailsIntent.putExtra("SelectedBadge", selectedBadge);
            startActivity(detailsIntent);



        }
    }
}
