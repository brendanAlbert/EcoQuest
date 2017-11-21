package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardsActivity extends AppCompatActivity {

    private DBHelper db;

    private ArrayList<Long> rankArrayList;

    private List<User> mostPointsUserList = null;
    private List<User> mostLevelsUserList = null;
    private List<User> mostBadgesUserList = null;

    private TextView mostPointsLevelsBadgesTextView;

    private LeaderboardListAdapter mLeaderboardListAdapter;

    private ListView mLeaderboardListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        mostPointsLevelsBadgesTextView = (TextView) findViewById(R.id.pointsLevelsBadgesRankingMessageTextView);
        mostPointsLevelsBadgesTextView.setText("See where all ecoQuest users rank by category");

        mLeaderboardListView = (ListView) findViewById(R.id.leaderboardListView);
    }

    public void displayPointsLeaderboard(View view) {

        if (mostPointsUserList == null)
            mostPointsUserList = orderUsersByPoints();

        mostPointsLevelsBadgesTextView.setText("Users ranked by points earned");

        mLeaderboardListAdapter = new LeaderboardListAdapter(this,
                R.layout.user_leaderboard_list_item,
                mostPointsUserList,
                "points",
                rankArrayList);
        mLeaderboardListView.setAdapter(mLeaderboardListAdapter);
    }

    private List<User> orderUsersByPoints() {
        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importUsersFromCSV("users.csv");
        mostPointsUserList = db.getUserList();
        Collections.sort(mostPointsUserList, new Comparator<User>() {
            @Override
            public int compare(User userA, User userB) {
                return Integer.valueOf(userB.getPoints()).compareTo(userA.getPoints());
            }
        });

        // we want an array or list which stores a user's rank at the index of their id
        // the users rank is stored at the index of their id
        rankArrayList = new ArrayList<>(mostPointsUserList.size()+1);

        for (User user : mostPointsUserList)
            rankArrayList.add(user.getId());

        return mostPointsUserList;
    }

    public void displayLevelsLeaderboard(View view) {
        if (mostLevelsUserList == null)
            mostLevelsUserList = orderUsersByLevel();

        mostPointsLevelsBadgesTextView.setText("Users ranked by level reached");

        mLeaderboardListAdapter = new LeaderboardListAdapter(
                this,
                R.layout.user_leaderboard_list_item,
                mostLevelsUserList,
                "levels",
                rankArrayList);
        mLeaderboardListView.setAdapter(mLeaderboardListAdapter);

    }

    private List<User> orderUsersByLevel() {
        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importUsersFromCSV("users.csv");
        mostLevelsUserList = db.getUserList();
        Collections.sort(mostLevelsUserList, new Comparator<User>() {
            @Override
            public int compare(User userA, User userB) {
                return Integer.valueOf(userB.getLevel()).compareTo(userA.getLevel());
            }
        });

        rankArrayList = new ArrayList<>(mostLevelsUserList.size()+1);

        for (User user : mostLevelsUserList)
            rankArrayList.add(user.getId());

        return mostLevelsUserList;
    }

    public void displayBadgesLeaderboard(View view) {
        if (mostBadgesUserList == null)
            mostBadgesUserList = orderUsersByMostBadges();

        mostPointsLevelsBadgesTextView.setText("Users ranked by badges earned");

        mLeaderboardListAdapter = new LeaderboardListAdapter(
                this,
                R.layout.user_leaderboard_list_item,
                mostBadgesUserList,
                "badges",
                rankArrayList);
        mLeaderboardListView.setAdapter(mLeaderboardListAdapter);
    }

    private List<User> orderUsersByMostBadges() {
        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importUsersFromCSV("users.csv");
        mostBadgesUserList = db.getUserList();
        Collections.sort(mostBadgesUserList, new Comparator<User>() {
            @Override
            public int compare(User userA, User userB) {
                return Integer.valueOf(userB.getHowManyBadges()).compareTo(userA.getHowManyBadges());
            }
        });

        rankArrayList = new ArrayList<>(mostBadgesUserList.size()+1);

        for (User user : mostBadgesUserList)
            rankArrayList.add(user.getId());

        return mostBadgesUserList;
    }
}
