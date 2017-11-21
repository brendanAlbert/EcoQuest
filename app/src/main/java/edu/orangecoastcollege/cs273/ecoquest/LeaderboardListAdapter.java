package edu.orangecoastcollege.cs273.ecoquest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by brendantyleralbert on 11/19/17.
 */

public class LeaderboardListAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private int mRank;
    private int mResource;
    private List<User> mAllUsersList = new ArrayList<>();
    private String mStatToTrack;

    public LeaderboardListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> allUsersList, String statToTrack) {
        super(context, resource, allUsersList);
        mContext = context;
        mRank = 1;
        mResource = resource;
        mAllUsersList = allUsersList;
        mStatToTrack = statToTrack;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(mResource, null);

        TextView rankTextView = listItemView.findViewById(R.id.rankTextView);
        ImageView userImageView = listItemView.findViewById(R.id.userImageView);
        TextView userNameTextView = listItemView.findViewById(R.id.usernameTextView);
        //TextView userTitleTextView = listItemView.findViewById(R.id.userTitleTextView);
        TextView userPointsLevelsBadgesTextView = listItemView.findViewById(R.id.userPointsLevelsBadgesTextView);

        User user = mAllUsersList.get(position);

        Log.i("adapter: rank", String.valueOf(mRank));

        rankTextView.setText(String.valueOf(mRank++));
        userImageView.setImageURI(getUriFromResource(mContext,R.drawable.frog));
        userNameTextView.setText(user.getUserName());

        if (mStatToTrack.equals("points"))
            userPointsLevelsBadgesTextView.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(user.getPoints())));
        else if (mStatToTrack.equals("levels"))
            userPointsLevelsBadgesTextView.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(user.getLevel())));
        else if (mStatToTrack.equals("badges"))
            userPointsLevelsBadgesTextView.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(user.getHowManyBadges())));

        //userTitleTextView.setText(user.getTitle());

        return listItemView;
    }

    private static Uri getUriFromResource(Context context, int resId)
    {
        Resources res = context.getResources();
        // Build a String in the form:
        // android.resource://edu.orangecoastcollege.cs273.ecoquest/drawable/none
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(resId) + "/"
                + res.getResourceTypeName(resId) + "/"
                + res.getResourceEntryName(resId);

        // Parse the String in order to construct a URI
        return Uri.parse(uri);
    }
}
