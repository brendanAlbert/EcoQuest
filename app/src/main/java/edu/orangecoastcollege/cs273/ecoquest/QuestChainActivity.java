package edu.orangecoastcollege.cs273.ecoquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * QuestChainActivity does nothing at the moment, but in an upcoming version of ecoQuest
 * there will be QuestChains unique to specific areas.  For instance, Newport Beach or Upper Newport
 * Back Bay might have their own quest chains.
 * There could be beginner level quests that are easy to accomplish.
 * As the user completes quests in the chain they will unlock more quests of increasing
 * difficulty.
 */
public class QuestChainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_chain);
    }
}
