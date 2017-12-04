package edu.orangecoastcollege.cs273.ecoquest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestsActivity extends AppCompatActivity {

    private DBHelper db;

    private List<Quest> mAllQuestsList;
    private List<Quest> mFilteredQuestsList;

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
    }

    public void parkQuests(View v)
    {
        mListedQuestsTextView.setText("All park quests.");
        mFilteredQuestsList = filterParkQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mFilteredQuestsList);
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
            }
        }
        return parkQuestList;
    }

    public void beachQuests(View v)
    {
        mListedQuestsTextView.setText("All beach-related quests.");
        mFilteredQuestsList = filterBeachQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mFilteredQuestsList);
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
        mFilteredQuestsList = filterCityQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mFilteredQuestsList);
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
        mFilteredQuestsList = filterSchoolQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mFilteredQuestsList);
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
        mFilteredQuestsList = filterTrashQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mFilteredQuestsList);
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
        mFilteredQuestsList = filterRecycleQuests();
        mQuestsListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mFilteredQuestsList);
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

    public void viewQuestDetails(View view)
    {
        if (view instanceof RelativeLayout)
        {
            RelativeLayout selectedLayout = (RelativeLayout) view;
            Quest quest = (Quest) selectedLayout.getTag();
            Intent questIntent = new Intent(this, QuestDetailsActivity.class);
            questIntent.putExtra("quest", quest);
            Log.i("viewQuestDetails", quest.toString());
            startActivity(questIntent);
        }
    }
}
