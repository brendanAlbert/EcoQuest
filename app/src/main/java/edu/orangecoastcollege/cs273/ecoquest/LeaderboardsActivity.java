package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LeaderboardsActivity is the Activity / Controller that handles interactivity
 * on the Leaderboards page.  When the User taps on one of the buttons to see where
 * Users are ranked for certain stats, (levels, points or badges), that is handled here.
 *
 * In future versions of the app there will most likely be additional buttons to track other
 * stats, such as, who has collected the most cigarette butts or recycled the most bottles.
 *
 * The member variables include:
 *  - a DBHelper instance,
 *  - ArrayList of Users, which will be sorted by that User's rank for a particular stat
 *  - a TextView and ListView
 *  - a LeaderboardListAdapter
 *
 * The methods include:
 *   - displayPointsLeaderboard, displayLevelsLeaderboard, displayBadgesLeaderboard, and private
 *     helper methods for each to populate the userList with the Users in the proper order
 *     for that stat.
 *   - visitUserProfile is called when the User taps on a particular User in the ListView,
 *      they are taken via Intent to the profile page which is then populated with that
 *      User's stats.
 */
public class LeaderboardsActivity extends AppCompatActivity {

    private List<User> userList;
    private TextView mostPointsLevelsBadgesTextView;
    private ListView mLeaderboardListView;
    private LeaderboardListAdapter mLeaderboardListAdapter;

    /**
     * onCreate is called when the User first arrives at LeaderboardsActivity.
     * The userList is populated from users persisted using an SQLite database.
     * The Users were initially added from a csv file.
     * The content view is set, the TextView is wired up and initial descriptive text is set.
     * The ListView is wired up but won't be populated until the User taps one of the buttons.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        deleteDatabase(DBHelper.DATABASE_NAME);
        DBHelper db = new DBHelper(this);
        db.importUsersFromCSV("users.csv");
        userList = db.getUserList();

        mostPointsLevelsBadgesTextView = (TextView) findViewById(R.id.pointsLevelsBadgesRankingMessageTextView);
        mostPointsLevelsBadgesTextView.setText(R.string.ranks_users_by_category);

        mLeaderboardListView = (ListView) findViewById(R.id.leaderboardListView);
    }

    /**
     * displayPointsLeaderboard() is called when the User taps the points button.
     * Users in userList are ordered based on most points thanks to the
     * call to orderUsersByPoints().
     * The TextView is set accordingly, and the adapter is passed
     * 'this' context, the user_leaderboard_list_item layout xml file,
     * the newly ordered userList, and a String (points),
     * so the adapter knows which stat to display.
     * @param view
     */
    public void displayPointsLeaderboard(View view) {
        orderUsersByPoints();
        mostPointsLevelsBadgesTextView.setText(R.string.rank_by_points);
        mLeaderboardListAdapter = new LeaderboardListAdapter(this,
                R.layout.user_leaderboard_list_item,
                userList,
                "points");
        mLeaderboardListView.setAdapter(mLeaderboardListAdapter);
    }

    private void orderUsersByPoints() {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User userA, User userB) {
                return Integer.valueOf(userB.getPoints()).compareTo(userA.getPoints());
            }
        });
    }

    /**
     * displayLevelsLeaderboard is called when the User taps the level button.
     * Users in userList are ordered based on highest level thanks to the
     * call to orderUsersByLevel().
     * The TextView is set accordingly, and the adapter is passed
     * 'this' context, the user_leaderboard_list_item layout xml file,
     * the newly ordered userList, and a String (levels),
     * so the adapter knows which stat to display.
     * @param view
     */
    public void displayLevelsLeaderboard(View view) {
        orderUsersByLevel();
        mostPointsLevelsBadgesTextView.setText(R.string.users_ranked_by_level);
        mLeaderboardListAdapter = new LeaderboardListAdapter(
                this,
                R.layout.user_leaderboard_list_item,
                userList,
                "levels");
        mLeaderboardListView.setAdapter(mLeaderboardListAdapter);
    }

    private void orderUsersByLevel() {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User userA, User userB) {
                return Integer.valueOf(userB.getLevel()).compareTo(userA.getLevel());
            }
        });
    }

    /**
     * displayBadgesLeaderboard is called when the User taps the badges button.
     * Users in userList are ordered based on most badges thanks to the
     * call to orderUsersByMostBadges().
     * The TextView is set accordingly, and the adapter is passed
     * 'this' context, the user_leaderboard_list_item layout xml file,
     * the newly ordered userList, and a String (badges),
     * so the adapter knows which stat to display.
     * @param view
     */
    public void displayBadgesLeaderboard(View view) {
        orderUsersByMostBadges();
        mostPointsLevelsBadgesTextView.setText(R.string.users_ranked_by_badges_earned);
        mLeaderboardListAdapter = new LeaderboardListAdapter(
                this,
                R.layout.user_leaderboard_list_item,
                userList,
                "badges");
        mLeaderboardListView.setAdapter(mLeaderboardListAdapter);
    }

    private void orderUsersByMostBadges() {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User userA, User userB) {
                return Integer.valueOf(userB.getHowManyBadges()).compareTo(userA.getHowManyBadges());
            }
        });
    }

    /**
     * visitUserProfile is called when the User taps on a particular User in the ListView.
     * An Intent is created using the User stored as a Tag in the selected layout.
     * The Intent is told to go to UserProfileActivity.
     * The User is put as an extra in the Intent, because we made User parcelable,
     * and startActivity is passed the Intent.
     * @param view
     */
    public void visitUserProfile(View view)
    {
        if ( view instanceof LinearLayout )
        {
            LinearLayout selectedLayout = (LinearLayout) view;
            User user = (User) selectedLayout.getTag();
            Intent userIntent = new Intent(this, UserProfileActivity.class);
            userIntent.putExtra("user", user);
            startActivity(userIntent);
        }
    }
}
