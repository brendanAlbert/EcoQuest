package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class LeaderboardsActivity extends AppCompatActivity {

    private DBHelper db;

    private List<User> allUsersList;
    private List<User> mostPointsUserList = null;
    private List<User> mostLevelsUserList;
    private List<User> mostBadgesUserList;

    private TextView mostPointsLevelsBadgesTextView;

    private LeaderboardListAdapter mLeaderboardListAdapter;

    private ListView mLeaderboardListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        mLeaderboardListView = (ListView) findViewById(R.id.leaderboardListView);

    }

    public void displayPointsLeaderboard(View view) {

        if (mostPointsUserList == null)
        {
            deleteDatabase(DBHelper.DATABASE_NAME);
            db = new DBHelper(this);
            db.importUsersFromCSV("users.csv");
            mostPointsUserList = db.getMostPointsUserList();

            for (User u : mostPointsUserList)
                Log.i("User: ", u.toString());

            Log.i("userList.size() = ", String.valueOf(mostPointsUserList.size()));
        }

        Log.i("LeaderboardsActivity", "user tapped points button");
        mLeaderboardListAdapter = new LeaderboardListAdapter(this, R.layout.user_leaderboard_list_item, mostPointsUserList);
        mLeaderboardListView.setAdapter(mLeaderboardListAdapter);
    }

    public void displayLevelsLeaderboard() {

    }

    public void displayBadgesLeaderboard() {

    }
}
