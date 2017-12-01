package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importQuestsFromCSV("quests.csv");
        mAllQuestsList = db.getAllQuests();
        for (Quest q : mAllQuestsList)
            Log.i("onCreate", q.toString());
    }

    public void parkQuests(View v)
    {
        mListedQuestsTextView.setText("All park quests.");
        if (mParkQuestsList == null)
            mParkQuestsList = filterParkQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mParkQuestsList);
        mQuestsPageListView.setAdapter(mQuestsListAdapter);
    }

    private List<Quest> filterParkQuests()
    {
        List<Quest> parkQuestList = new ArrayList<>();
        List<Integer> tempIntList;
        for (Quest quest : mAllQuestsList) {
            tempIntList = quest.getQuestTypes();
            if (tempIntList.contains(QuestType.PARK)) {
                parkQuestList.add(quest);
                Log.i("filterParkQuests", quest.toString());
            }
        }
        return parkQuestList;
    }

    public void beachQuests(View v)
    {
        mListedQuestsTextView.setText("All beach-related quests.");
        if (mBeachQuestsList == null)
            mBeachQuestsList = filterBeachQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mBeachQuestsList);
        mQuestsPageListView.setAdapter(mQuestsListAdapter);

    }

    private List<Quest> filterBeachQuests()
    {
        List<Quest> beachQuestList = new ArrayList<>();
        List<Integer> tempIntList;
        for (Quest quest: mAllQuestsList) {
            tempIntList = quest.getQuestTypes();
            if (tempIntList.contains(QuestType.BEACH)) {
                beachQuestList.add(quest);
            }
        }
        return beachQuestList;
    }

    public void cityQuests(View v)
    {
        mListedQuestsTextView.setText("All city-related quests.");
        if (mCityQuestsList == null)
            mCityQuestsList = filterCityQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mCityQuestsList);
        mQuestsPageListView.setAdapter(mQuestsListAdapter);
    }

    private List<Quest> filterCityQuests()
    {
        List<Quest> cityQuestsList = new ArrayList<>();
        List<Integer> tempIntList;
        for (Quest quest: mAllQuestsList) {
            tempIntList = quest.getQuestTypes();
            if (tempIntList.contains(QuestType.CITY)) {
                cityQuestsList.add(quest);
            }
        }
        return cityQuestsList;
    }

    public void schoolQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that take place on school campuses.");
        if (mSchoolQuestsList == null)
            mSchoolQuestsList = filterSchoolQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mSchoolQuestsList);
        mQuestsPageListView.setAdapter(mQuestsListAdapter);
    }

    private List<Quest> filterSchoolQuests()
    {
        List<Quest> schoolQuestsList = new ArrayList<>();
        List<Integer> tempIntList;
        for (Quest quest: mAllQuestsList) {
            tempIntList = quest.getQuestTypes();
            if (tempIntList.contains(QuestType.SCHOOL)) {
                schoolQuestsList.add(quest);
            }
        }
        return schoolQuestsList;
    }

    public void trashQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that involve tossing trash.");
        if (mTrashQuestsList == null)
            mTrashQuestsList = filterTrashQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mTrashQuestsList);
        mQuestsPageListView.setAdapter(mQuestsListAdapter);
    }

    private List<Quest> filterTrashQuests()
    {
        List<Quest> trashQuestsList = new ArrayList<>();
        List<Integer> tempIntList;
        for (Quest quest: mAllQuestsList) {
            tempIntList = quest.getQuestTypes();
            if (tempIntList.contains(QuestType.TRASH)) {
                trashQuestsList.add(quest);
            }
        }
        return trashQuestsList;
    }

    public void recycleQuests(View v)
    {
        mListedQuestsTextView.setText("All quests that involve recycling.");
        if (mRecycleQuestsList == null)
            mRecycleQuestsList = filterRecycleQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mRecycleQuestsList);
        mQuestsPageListView.setAdapter(mQuestsListAdapter);
    }

    private List<Quest> filterRecycleQuests()
    {
        List<Quest> recycleQuestsList = new ArrayList<>();
        List<Integer> tempIntList;
        for (Quest quest: mAllQuestsList) {
            tempIntList = quest.getQuestTypes();
            if (tempIntList.contains(QuestType.RECYCLE)) {
                recycleQuestsList.add(quest);
            }
        }
        return recycleQuestsList;
    }
}
