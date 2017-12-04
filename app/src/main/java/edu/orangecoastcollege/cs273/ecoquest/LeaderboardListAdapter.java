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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by brendantyleralbert on 11/19/17.
 */

public class LeaderboardListAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private ArrayList<Long> mRankArray;
    private int mResource;
    private List<User> mAllUsersList = new ArrayList<>();
    private String mStatToTrack;

    LeaderboardListAdapter(@NonNull Context context,
                                  @LayoutRes int resource,
                                  @NonNull List<User> allUsersList,
                                  String statToTrack,
                                  ArrayList<Long> rankArray)
    {
        super(context, resource, allUsersList);
        mContext = context;
        mRankArray = rankArray;
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

        // This setText is where the rank of the user is determined and set
        rankTextView.setText(String.valueOf(mRankArray.indexOf(user.getId())+1));


        // Use the AssetManager to retrieve the image
        AssetManager am = mContext.getAssets();

        try {
            InputStream stream = am.open(user.getProfilePictureName());
            Drawable image = Drawable.createFromStream(stream, user.getUserName());
            userImageView.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userNameTextView.setText(user.getUserName());
        LinearLayout mLeaderboardUserLinearLayout = listItemView.findViewById(R.id.leaderboardUserListItemLinearLayout);
        mLeaderboardUserLinearLayout.setTag(user);

        if (mStatToTrack.equals("points"))
            userPointsLevelsBadgesTextView.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(user.getPoints())));
        else if (mStatToTrack.equals("levels"))
            userPointsLevelsBadgesTextView.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(user.getLevel())));
        else if (mStatToTrack.equals("badges"))
            userPointsLevelsBadgesTextView.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(user.getHowManyBadges())));

        //userTitleTextView.setText(user.getTitle());

        return listItemView;
    }

    /*
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
    */
}
