package edu.orangecoastcollege.cs273.ecoquest;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * QuestDetailsActivity is the Activity/Controller that handles when a user tapped
 * on a Quest in a ListView and they are brought to this Activity.
 *
 * There are member variables for the ImageViews, TextViews, ProgressBar, and the Quest.
 *
 * The only method is onCreate.
 * onCreate sets the content view,
 * wires up all the Views,
 * instantiates the Quest by getting a parceled Quest sent via an Intent.
 */
public class QuestDetailsActivity extends AppCompatActivity {


    private ImageView mQuestImageView;
    private TextView mQuestNameTextView;
    private TextView mQuestDescriptionTextView;
    private ProgressBar mProgressBar;
    private TextView mQuestProgressTextView;
    private ImageView mQuestStatusImageView;

    private Quest mQuest;

    /**
     * onCreate wires up all the Views and Progress Bar, and
     * gets the Quest sent via an Intent.  The Intent has a parceled Quest.
     *
     * All of the Views are populated with the data provided by the parceled Quest.
     *
     * The Progress Bar and quest completion status are hardcoded at the moment
     * for demonstration purposes.  But when the app is made available to users,
     * these values would reflect the accomplishments of the User.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_details);

        mQuestImageView = (ImageView) findViewById(R.id.detailsQuestImageView);
        mQuestNameTextView = (TextView) findViewById(R.id.detailsQuestNameTextView);
        mQuestDescriptionTextView = (TextView) findViewById(R.id.detailsQuestDescriptionTextView);
        mProgressBar = (ProgressBar) findViewById(R.id.detailsQuestProgressBar);
        mQuestProgressTextView = (TextView) findViewById(R.id.detailsQuestProgressTextView);
        mQuestStatusImageView = (ImageView) findViewById(R.id.detailsQuestStatusImageView);


        mQuest = getIntent().getExtras().getParcelable("quest");

        mQuestNameTextView.setText(mQuest.getName());


        mQuestDescriptionTextView.setText(mQuest.getDescription());
        mProgressBar.setMax(mQuest.getMaxProgress());
        // for demonstration purposes we have just hard coded a progress of 1
        //mProgressBar.setProgress(mQuest.getCurrentProgress());
        mProgressBar.setProgress(1);

        String questStatusString = String.valueOf(mQuest.getCurrentProgress()) + " / " + String.valueOf(mQuest.getMaxProgress());
        mQuestProgressTextView.setText(questStatusString);

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(mQuest.getImageName());
            Drawable image = Drawable.createFromStream(stream, mQuest.getName());
            mQuestImageView.setImageDrawable(image);

            if ( mQuest.getCurrentProgress() == mQuest.getMaxProgress() )
            {
                InputStream stream1 = am.open("quest_complete.png");
                image = Drawable.createFromStream(stream1, "quest_complete.png");
                mQuestStatusImageView.setImageDrawable(image);
            }
            else
            {
                InputStream stream2 = am.open("quest_incomplete.png");
                image = Drawable.createFromStream(stream2, "quest_incomplete.png");
                mQuestStatusImageView.setImageDrawable(image);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
