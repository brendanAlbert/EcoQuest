package edu.orangecoastcollege.cs273.ecoquest;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

    private Animation shrinkGrowAnimation;
    private Animation plusOneAnimation;

    private ImageView mQuestImageView;
    private TextView mQuestNameTextView;
    private TextView mQuestDescriptionTextView;
    //private ProgressBar mProgressBar;
    private ImageView mProgressBar;
    private TextView mQuestProgressTextView;
    private ImageView mQuestStatusImageView;

    private ImageView mTurnInItemButton;
    private ImageView mTurnInItemPlus1Icon;

    private int mProgress = 0; // This is only for use during the demonstration
    private String questStatusString; // This is only for use during the demonstration

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
        //mProgressBar = (ProgressBar) findViewById(R.id.detailsQuestProgressBar);
        mProgressBar = (ImageView) findViewById(R.id.detailsQuestProgressBar);
        mQuestProgressTextView = (TextView) findViewById(R.id.detailsQuestProgressTextView);
        mQuestStatusImageView = (ImageView) findViewById(R.id.detailsQuestStatusImageView);

        mTurnInItemButton = (ImageView) findViewById(R.id.turnInItemButton);
        mTurnInItemPlus1Icon = (ImageView) findViewById(R.id.turnInItemPlus1Icon);


        mQuest = getIntent().getExtras().getParcelable("quest");

        mQuestNameTextView.setText(mQuest.getName());


        mQuestDescriptionTextView.setText(mQuest.getDescription());
        //mProgressBar.setMax(mQuest.getMaxProgress());


        // for demonstration purposes the progress only affects the View, but the next version will
        // appropriately change the User's stat and update the Model/Database.
        //mProgressBar.setProgress(mQuest.getCurrentProgress());
        //mProgressBar.setProgress(mProgress);

        questStatusString = String.valueOf(mQuest.getCurrentProgress()) + " / " + String.valueOf(mQuest.getMaxProgress());
        mQuestProgressTextView.setText(questStatusString);

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(mQuest.getImageName());
            Drawable image = Drawable.createFromStream(stream, mQuest.getName());
            mQuestImageView.setImageDrawable(image);

            /*
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
            */
            InputStream stream1 = am.open("progress_bar_none.png");
            image = Drawable.createFromStream(stream1, "progress_bar_none.png");
            mProgressBar.setImageDrawable(image);

            stream1 = am.open("quest_incomplete.png");
            image = Drawable.createFromStream(stream1, "quest_incomplete.png");
            mQuestStatusImageView.setImageDrawable(image);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnInTapped(View view) {

        if (mProgress < mQuest.getMaxProgress()) {

            mProgress++;

            //mProgressBar.setProgress(++mProgress);

            String progressFileFullName = "";

            try
            {
                if (mProgress == mQuest.getMaxProgress())
                    progressFileFullName = "progress_bar_full.png";
                else if(mProgress == ( mQuest.getMaxProgress() / 2) )
                    progressFileFullName = "progress_bar_half.png";
                else
                {
                    switch(mQuest.getMaxProgress())
                    {
                        case 5:
                            progressFileFullName = "progress_bar_" + String.valueOf(mProgress) + "_5th.png";
                            break;
                        case 10:
                            progressFileFullName = "progress_bar_" + String.valueOf(mProgress) + "_10th.png";
                            break;
                        case 25:
                            progressFileFullName = "progress_bar_" + String.valueOf(mProgress) + "_25th.png";
                            break;
                    }
                }

                AssetManager am = this.getAssets();
                InputStream stream1 = am.open(progressFileFullName);
                Drawable image = Drawable.createFromStream(stream1, progressFileFullName);
                mProgressBar.setImageDrawable(image);

            } catch (IOException e)
            {
                e.printStackTrace();
            }



            questStatusString = String.valueOf( mProgress + " / " + String.valueOf(mQuest.getMaxProgress()));
            mQuestProgressTextView.setText(questStatusString);
            shrinkGrowAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_grow_anim);
            plusOneAnimation = AnimationUtils.loadAnimation(this, R.anim.plus_one_anim);
            mTurnInItemButton.startAnimation(shrinkGrowAnimation);
            mTurnInItemPlus1Icon.startAnimation(plusOneAnimation);
        }

        // if quest completed, change icon from incomplete -> complete, and hide the "+" button
        if(mProgress == mQuest.getMaxProgress()) {
            mTurnInItemButton.setVisibility(View.GONE);
            AssetManager am = QuestDetailsActivity.this.getAssets();
            try {
                InputStream stream = am.open("quest_complete.png");
                Drawable image = Drawable.createFromStream(stream, "quest_complete.png");
                mQuestStatusImageView.setImageDrawable(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
