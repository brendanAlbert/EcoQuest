package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class QuestsActivity extends AppCompatActivity {

    private DBHelper db;

    private List<Quest> mAllQuestsList     = null;
    private List<Quest> mBeachQuestsList   = null;
    private List<Quest> mParkQuestsList    = null;
    private List<Quest> mCityQuestsList    = null;
    private List<Quest> mSchoolQuestsList  = null;
    private List<Quest> mTrashQuestsList   = null;
    private List<Quest> mRecycleQuestsList = null;

    // All the TextViews and ImageViews and ListView widgets
    private TextView mListedQuestsTextView;
    private ListView mQuestsPageListView;

    private QuestsListAdapter mQuestsListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        mListedQuestsTextView = (TextView) findViewById(R.id.listedQuestsTextView);
        mQuestsPageListView = (ListView) findViewById(R.id.questsPageListView);

        db = new DBHelper(this);
        db.importQuestsFromCSV("quests.csv");
        mAllQuestsList = db.getAllQuests();
    }

    public void parkQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that take place in parks.");
        if (mParkQuestsList == null)
            mParkQuestsList = filterParkQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mParkQuestsList);
    }

    private List<Quest> filterParkQuests()
    {
        return null;
    }

    public void beachQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that take place at the beach.");
    }
    public void cityQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that take place in cities.");
    }
    public void schoolQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that take place on school campuses.");
    }
    public void trashQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that involve tossing trash.");
    }
    public void recycleQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that involve recycling.");
    }
}
