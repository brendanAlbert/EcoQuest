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

/**
 * QuestsActivity is the controller for the Activity/Page where Users can view all the available
 * quests.  There are six ImageViews being used as buttons.  We don't even wire them up here,
 * we simply use different methods for those Views to call when they are tapped on.
 *
 * The member variables include:
 *   - a DBHelper
 *   - two Lists of Quests, a List of all Quests, and a List that is filtered based
 *      on the button the User tapped.
 *   - TextView and ListView, these are updated accordingly based on the button
 *      the User tapped.
 *   - a QuestsListAdapter to help populate the ListView with quests.
 *
 * The methods include:
 * 6 methods for each quest area type button and a private helper method for each,
 * this makes 12 methods, plus onCreate makes 13, and one last method
 * viewQuestDetails which is used to fire an Intent when the User taps on a Quest
 * in the ListView, for 14 methods.
 */
public class QuestsActivity extends AppCompatActivity {

    private DBHelper db;

    private List<Quest> mAllQuestsList;
    private List<Quest> mFilteredQuestsList;

    // All the TextViews and ImageViews and ListView widgets
    private TextView mListedQuestsTextView;
    private ListView mQuestsPageListView;

    private QuestsListAdapter mQuestsListAdapter;


    /**
     * onCreate is called when the user arrives at QuestsActivity.
     *
     * This method sets the content view and wires up the ListView and TextView.
     *
     * Currently, every time the User visits QuestsActivity, we delete the ecoQuest
     * database, instantiate a new DBHelper, and import all the quests from the csv into
     * the db object and finally we populate the allQuestsList with all the imported quests.
     *
     * @param savedInstanceState
     */
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

    /**
     * parkQuests is called when the user taps the top left park button.
     * The TextView is updated to reflect this action.
     * The filtered quests list is populated with only park quests.
     * The QuestsListAdapter is instantiated using 'this' context, the quest_list_item layout,
     * and the filtered quests list is passed in.
     * Finally we set the adapter on the ListView using the QuestsListAdapter.
     * @param v
     */
    public void parkQuests(View v)
    {
        mListedQuestsTextView.setText(R.string.all_park_quests);
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

    /**
     * beachQuests is called when the user taps the top middle beach button.
     * The TextView is updated to reflect this action.
     * The filtered quests list is populated with only beach quests.
     * The QuestsListAdapter is instantiated using 'this' context, the quest_list_item layout,
     * and the filtered quests list is passed in.
     * Finally we set the adapter on the ListView using the QuestsListAdapter.
     * @param v
     */
    public void beachQuests(View v)
    {
        mListedQuestsTextView.setText(R.string.beach_quests);
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

    /**
     * cityQuests is called when the user taps the top right city button.
     * The TextView is updated to reflect this action.
     * The filtered quests list is populated with only city quests.
     * The QuestsListAdapter is instantiated using 'this' context, the quest_list_item layout,
     * and the filtered quests list is passed in.
     * Finally we set the adapter on the ListView using the QuestsListAdapter.
     * @param v
     */
    public void cityQuests(View v)
    {
        mListedQuestsTextView.setText(R.string.all_city_quests);
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

    /**
     * schoolQuests is called when the user taps the bottom left school button.
     * The TextView is updated to reflect this action.
     * The filtered quests list is populated with only school quests.
     * The QuestsListAdapter is instantiated using 'this' context, the quest_list_item layout,
     * and the filtered quests list is passed in.
     * Finally we set the adapter on the ListView using the QuestsListAdapter.
     * @param v
     */
    public void schoolQuests(View v)
    {
        mListedQuestsTextView.setText(R.string.all_school_quests);
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

    /**
     * trashQuests is called when the user taps the bottom middle trash button.
     * The TextView is updated to reflect this action.
     * The filtered quests list is populated with only trash quests.
     * The QuestsListAdapter is instantiated using 'this' context, the quest_list_item layout,
     * and the filtered quests list is passed in.
     * Finally we set the adapter on the ListView using the QuestsListAdapter.
     * @param v
     */
    public void trashQuests(View v)
    {
        mListedQuestsTextView.setText(R.string.all_trash_quests);
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

    /**
     * recycleQuests is called when the user taps the bottom right recycle button.
     * The TextView is updated to reflect this action.
     * The filtered quests list is populated with only recycle quests.
     * The QuestsListAdapter is instantiated using 'this' context, the quest_list_item layout,
     * and the filtered quests list is passed in.
     * Finally we set the adapter on the ListView using the QuestsListAdapter.
     * @param v
     */
    public void recycleQuests(View v)
    {
        mListedQuestsTextView.setText(R.string.all_recycling_quests);
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

    /**
     * viewQuestDetails is called when the User taps on a Quest in the ListView.
     *
     * We call getTag to instantiate a Quest object that was stored in the RelativeLayout
     * in the ListView.
     * This Quest is put into the questIntent.
     * We startActivity using the questIntent as a parameter.
     * This takes the User to the QuestDetailsActivity.
     * @param view
     */
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
