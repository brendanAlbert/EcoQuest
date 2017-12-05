package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class UserProfileActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Badge> badgesList;
    private BadgeListAdapter badgeListAdapter;
    private ListView badgeListView;

    private User mUser;
    private ImageView mUserImageView;
    private TextView mUsernameTextView;
    private TextView mUserBadgesEarnedTextView;
    private TextView mUserPointsEarnedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importBadgesFromCSV("badges.csv");

        badgesList = db.getAllBadges();
        badgeListAdapter = new BadgeListAdapter(this, R.layout.badge_list_item, badgesList);
        badgeListView = (ListView) findViewById(R.id.badgesListView);
        badgeListView.setAdapter(badgeListAdapter);

        try {
            mUser = getIntent().getExtras().getParcelable("user");
        } catch (NullPointerException e) {
            db.importUsersFromCSV("users.csv");
            List<User> userList = db.getUserList();
            mUser = userList.get(0);
        }


        mUserImageView = (ImageView) findViewById(R.id.profilePictureImageView);
        mUsernameTextView = (TextView) findViewById(R.id.profileUsernameTextView);
        mUsernameTextView.setText(mUser.getUserName() + "\nLevel: " + String.valueOf(mUser.getLevel()));
        mUserBadgesEarnedTextView = (TextView) findViewById(R.id.badgesEarnedTextView);
        mUserPointsEarnedTextView = (TextView) findViewById(R.id.pointsEarnedTextView);
        mUserBadgesEarnedTextView.setText(String.valueOf(mUser.getHowManyBadges()) + " / 20 Badges");
        mUserPointsEarnedTextView.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(mUser.getPoints())) + " Points");
        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(mUser.getProfilePictureName());
            Drawable image = Drawable.createFromStream(stream, mUser.getUserName());
            mUserImageView.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
