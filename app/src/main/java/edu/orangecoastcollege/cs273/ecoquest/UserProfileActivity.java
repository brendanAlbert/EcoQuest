package edu.orangecoastcollege.cs273.ecoquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Badge> badgesList;
    private BadgeListAdapter badgeListAdapter;
    private ListView badgeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        db = new DBHelper(this);




    }
}
