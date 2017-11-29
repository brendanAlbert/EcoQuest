package edu.orangecoastcollege.cs273.ecoquest;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaseyTea on 11/20/17.
 */

public class BadgeListAdapter extends ArrayAdapter<Badge>{


    private Context mContext;
    private List<Badge> mBadgeList = new ArrayList<>();
    private int mResourceId;


    /**
     * Creates a new <code> BadgeListAdapter</code> given a mContext, resource id and list of badges
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param badges The list of offerings to display
     */
        public BadgeListAdapter(Context c, int rId, List<Badge> badges)
        {
            super(c, rId, badges);
            mContext = c;
            mResourceId = rId;
            mBadgeList = badges;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent)
        {
            final Badge selectBadge = mBadgeList.get(pos);

            LayoutInflater inflater =
                    (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(mResourceId, null);

            LinearLayout badgeListLinearLayout =
                    (LinearLayout) view.findViewById(R.id.badgesListLinearLayout);

            ImageView badgeListImageView =
                    (ImageView) view.findViewById(R.id.badgeListImageView);

            TextView badgeListNameTextView =
                    (TextView) view.findViewById(R.id.badgeListNameTextView);

            ProgressBar badgeListProgressBar =
                    (ProgressBar) view.findViewById(R.id.badgeListProgressBar);

            badgeListLinearLayout.setTag(selectBadge);
            badgeListNameTextView.setText(selectBadge.getName());

            AssetManager am = mContext.getAssets();
            try{
                InputStream stream = am.open(selectBadge.getImageName());
                Drawable event = Drawable.createFromStream(stream, selectBadge.getName());
                badgeListImageView.setImageDrawable(event);
            }
            catch (IOException ex)
            {
                Log.e("EcoQuest", "Error loading " + selectBadge.getImageName(), ex);
            }

            return view;
        }


}

