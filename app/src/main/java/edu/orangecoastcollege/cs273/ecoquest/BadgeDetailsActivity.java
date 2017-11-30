package edu.orangecoastcollege.cs273.ecoquest;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class BadgeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_details);

        TextView badgeDetailsNameTextView = (TextView) findViewById(R.id.badgeTitleTextView);
        TextView badgeDetailsDescriptionTextView = (TextView) findViewById(R.id.badgeDescriptionTextView);
        ImageView badgeDetailsImageView = (ImageView) findViewById(R.id.badgeImageView);

        Badge selectBadge = getIntent().getExtras().getParcelable("SelectedBadge");

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(selectBadge.getImageName());
            Drawable event = Drawable.createFromStream(stream, selectBadge.getName());
            badgeDetailsImageView.setImageDrawable(event);
        }
        catch (IOException ex)
        {
            Log.e("Gamers Delight", "Error loading " + selectBadge.getImageName(), ex);
        }

        badgeDetailsNameTextView.setText(selectBadge.getName());
        badgeDetailsDescriptionTextView.setText(selectBadge.getDescription());


    }
}
