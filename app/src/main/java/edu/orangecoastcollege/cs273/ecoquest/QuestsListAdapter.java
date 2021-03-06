package edu.orangecoastcollege.cs273.ecoquest;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

/**
 * Created by brendantyleralbert on 11/29/17.
 *
 * QuestsListAdapter "is an" ArrayAdapter of Quests.
 *
 * This class is used to inflate views in a ListView via the getView method.
 *
 * Views are wired up, the respective Quest is gotten from the List of Quests passed into
 * the constructor, the various TextViews and ImageViews are set using the data stored in
 * the Quest object.
 *
 * In the massive try catch block where we load the image asset, we are randomly assigning
 * a quest complete or incomplete image.  This is simply to make demonstrating the app
 * more interesting.  This would behave as expected as if a User was using the app.
 * It would use the appropriate image for their quest completion status.
 */
public class QuestsListAdapter extends ArrayAdapter<Quest> {
    private Context mContext;
    private int mResource;
    private List<Quest> mQuestsList;

    /**
     * QuestsListAdapter is a parameterized constructor that accepts 3 arguments
     * @param context the context where this adapter is being used.
     * @param resource the id of the resource to populate
     * @param questsList the List of Quests to populate the ListView with
     */
    public QuestsListAdapter(@NonNull Context context,
                             @LayoutRes int resource,
                             @NonNull List<Quest> questsList) {
        super(context, resource, questsList);
        mContext = context;
        mResource = resource;
        mQuestsList = questsList;
    }

    /**
     * In getView, Views are wired up, the respective Quest is gotten from the List of Quests passed into
     * the constructor, the various TextViews and ImageViews are set using the data stored in
     * the Quest object.
     *
     * In the massive try catch block where we load the image asset, we are randomly assigning
     * a quest complete or incomplete image.  This is simply to make demonstrating the app
     * more interesting.  This would behave as expected as if a User was using the app.
     * It would use the appropriate image for their quest completion status.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View questListItemView = inflater.inflate(mResource, null);

        RelativeLayout mQuestRelativeLayout = questListItemView.findViewById(R.id.questRelativeLayout);
        TextView questNameTextView = questListItemView.findViewById(R.id.questNameTextView);
        TextView questDescriptionTextView = questListItemView.findViewById(R.id.questDescriptionTextView);
        ImageView questIconImageView = questListItemView.findViewById(R.id.questIconImageView);
        ImageView questCompleteStatusImageView = questListItemView.findViewById(R.id.questCompleteStatusImageView);

        Quest quest = mQuestsList.get(position);

        questNameTextView.setText(quest.getName());
        questDescriptionTextView.setText(quest.getDescription());
        mQuestRelativeLayout.setTag(quest);

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(quest.getImageName());
            Drawable image = Drawable.createFromStream(stream, quest.getName());
            questIconImageView.setImageDrawable(image);
            // if quest complete setImageDrawable to quest_complete.png

            Random rand = new Random(); // this is just for purposes of making the test more interesting to look at while debugging
            int numb = rand.nextInt(10);

            if (numb % 2 == 0) {
                InputStream stream1 = am.open("quest_complete.png");
                image = Drawable.createFromStream(stream1, "quest_complete.png");
                questCompleteStatusImageView.setImageDrawable(image);
            }
            else {
                // else set to quest_incomplete.png
                InputStream stream2 = am.open("quest_incomplete.png");
                image = Drawable.createFromStream(stream2, "quest_incomplete.png");
                questCompleteStatusImageView.setImageDrawable(image);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return questListItemView;
    }
}
