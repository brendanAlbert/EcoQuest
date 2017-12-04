package edu.orangecoastcollege.cs273.ecoquest;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class QuestDetailsActivity extends AppCompatActivity {


    private ImageView mQuestImageView;
    private TextView mQuestNameTextView;
    private TextView mQuestDescriptionTextView;
    private ProgressBar mProgressBar;
    private TextView mQuestProgressTextView;
    private ImageView mQuestStatusImageView;

    private Quest mQuest;

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
        Log.i("deets", mQuest.toString());
        Log.i("deets", String.valueOf(R.id.questNameTextView));

        mQuestNameTextView.setText(mQuest.getName());


        mQuestDescriptionTextView.setText(mQuest.getDescription());
        mProgressBar.setMax(mQuest.getMaxProgress());
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
