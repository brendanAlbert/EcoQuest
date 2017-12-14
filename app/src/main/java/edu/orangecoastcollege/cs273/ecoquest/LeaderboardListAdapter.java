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
import java.util.Random;

/**
 * Created by brendantyleralbert on 11/19/17.
 *
 * LeaderboardListAdapter extends, and therefore "is an" ArrayAdapter of Users.
 *
 * The purpose of this class is to inflate a ListView based on the layout specified, using
 * the data from the List of Users.
 *
 * Member variables include:
 *  - a provided Context
 *  - an int representing the passed in resource
 *  - a List of Users
 *  - a String representing the stat we are tracking ("points","levels","badges")
 *
 * Methods include:
 *  - a parameterized constructor
 *  - getView
 */

public class LeaderboardListAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private int mResource;
    private List<User> mAllUsersList = new ArrayList<>();
    private String mStatToTrack;
    private String[] mListOfTitles= {
        "PooPoo Paladin",
        "Seagull Saver",
        "Bane of Litterbugs",
            "Doctor of Debris",
            "Detritus Destroyer",
            "Cup Collector",
            "Collector of Butts",
            "Trash Blaster",
            "Trash Trooper",
            "Rajah of Recycle",
            "Caliph of Collection",
            "Plastic Potentate",
            "Rex of Rubbish",
            "Regin of Refuse",
            "Sultan of Scrap",
            "Barón de Basura",
            "Warlock of Waste",
            "Waste Warrior"
    };

    /**
     * LeaderboardListAdapter is a parameterized constructor.
     * @param context the provided context
     * @param resource an int representing the layout resource file to inflate
     * @param allUsersList the List of Users
     * @param statToTrack the stat we are tracking based on the button the user tapped. (points,levels,badges)
     */
    LeaderboardListAdapter(@NonNull Context context,
                                  @LayoutRes int resource,
                                  @NonNull List<User> allUsersList,
                                  String statToTrack)
    {
        super(context, resource, allUsersList);
        mContext = context;
        mResource = resource;
        mAllUsersList = allUsersList;
        mStatToTrack = statToTrack;
    }

    /**
     * getView is the "raison d'être" for LeaderboardListAdapter.
     * The layout resource file is inflated using the data from the Users in the List provided.
     * getView is called every time a user scrolls into view in the ListView.
     * The View is returned.
     *
     * An inflater is instantiated, the ListItemView and all other views are wired up,
     * the User is retrieved from the user list based on the position argument,
     * text is set based on the User's data, and an AssetManager object is used
     * to retrieve and set the User's image.
     *
     * We also set a tag using the User object.  This is for when the User taps on a particular User
     * so they can view their profile page.
     *
     * There is an if else block which determines which User stat to display.  This is
     * based on which button the User tapped.
     *
     * @param position is the int representation of the particular User getView is inflating
     * @param convertView unused for our purposes
     * @param parent the parent ViewGroup of the list item, the ListView
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(mResource, null);

        TextView rankTextView = listItemView.findViewById(R.id.rankTextView);
        ImageView userImageView = listItemView.findViewById(R.id.userImageView);
        TextView userNameTextView = listItemView.findViewById(R.id.usernameTextView);
        TextView userTitleTextView = listItemView.findViewById(R.id.userTitleTextView);
        TextView userPointsLevelsBadgesTextView = listItemView.findViewById(R.id.userPointsLevelsBadgesTextView);

        User user = mAllUsersList.get(position);

        // This setText is where the rank of the user is determined and set
        rankTextView.setText(String.valueOf(mAllUsersList.indexOf(user)+1));

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

        // Since the title functionality did not make it into this version,
        // we will simply get a random title String for now for purposes of demonstration.
        //userTitleTextView.setText(user.getTitle());
        Random rand = new Random();
        userTitleTextView.setText(mListOfTitles[rand.nextInt(mListOfTitles.length)]);

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
