package edu.orangecoastcollege.cs273.ecoquest;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static edu.orangecoastcollege.cs273.ecoquest.R.id.profilePictureImageView;

public class UserProfileActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Badge> badgesList;
    private BadgeListAdapter badgeListAdapter;
    private ListView badgeListView;

    // Constants for permissions:
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED;
    public static final int DENIED = PackageManager.PERMISSION_DENIED;

    private ImageView mProfilePicture;
    private Uri imageUri;

    private User mUser;
    private ImageView mUserImageView;
    private TextView mUsernameTextView;
    private TextView mUserBadgesEarnedTextView;
    private TextView mUserPointsEarnedTextView;

    // Sensors:
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private ShakeDetector mShakeDetector;

    private ImageView mCaptainImage;
    private Animation captainAnimation;

    /**
     * onCreate() populates the views for the UserProfile.
     * Populates badges from data base to put into a list to display in the views.
     * Displays points and badges the user has in textview.
     * @param savedInstanceState contains the state of the app since it was list minimized.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importBadgesFromCSV("badges.csv");

        mProfilePicture = (ImageView) findViewById(profilePictureImageView);
        //mProfilePicture.setImageURI(getUriFromResource(this, R.mipmap.ic_launcher_round));

        badgesList = db.getAllBadges();
        badgeListAdapter = new BadgeListAdapter(this, R.layout.badge_list_item, badgesList);
        badgeListView = (ListView) findViewById(R.id.badgesListView);
        badgeListView.setAdapter(badgeListAdapter);

        try {
            mUser = getIntent().getExtras().getParcelable("user");
        } catch (NullPointerException e) {
            db.importUsersFromCSV("users.csv");
            List<User> userList = db.getUserList();
            mUser = userList.get(0);
        }


        mUserImageView = (ImageView) findViewById(R.id.profilePictureImageView);
        mUsernameTextView = (TextView) findViewById(R.id.profileUsernameTextView);
        mUsernameTextView.setText(mUser.getUserName() + "\nLevel: " + String.valueOf(mUser.getLevel()));
        mUserBadgesEarnedTextView = (TextView) findViewById(R.id.badgesEarnedTextView);
        mUserPointsEarnedTextView = (TextView) findViewById(R.id.pointsEarnedTextView);
        mUserBadgesEarnedTextView.setText(getString(R.string.user_badges_earned_text_view, String.valueOf(mUser.getHowManyBadges()), "20"));
        mUserPointsEarnedTextView.setText(getString(R.string.user_points_earned_text_view, String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(mUser.getPoints()))));
        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(mUser.getProfilePictureName());
            Drawable image = Drawable.createFromStream(stream, mUser.getUserName());
            mUserImageView.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sensor Shake:
        // TASK 3: REGISTER THE SENSOR MANAGER AND SETUP THE SHAKE DETECTION
        mCaptainImage = (ImageView) findViewById(R.id.captainImageView);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * onShake method override used shake detector class to have
             * animation activated when "shake" has been performed.
             */
            @Override
            public void onShake() {
                Log.i("onShake called", "OnShake was called");
                captainAnimation = AnimationUtils.loadAnimation(UserProfileActivity.this, R.anim.captain_pop_up);
                mCaptainImage.startAnimation(captainAnimation);

            }
        });

    }

    /* Shake methods */

    /**
     * onResume method called after onStart; when the user first starts the app, or
     * it can be called after the user returns from a different app or activity.
     *
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * onStop method called when the activity is finished or destroyed by the system.
     */
    @Override
    protected void onStop()
    {
        super.onStop();
        mSensorManager.unregisterListener(mShakeDetector, accelerometer);
    }

    /* Shake methods End */

    /**
     * Method that loads intent when badges are clicked in the list view.
     * Populates the details page from the selected object.
     * @param view the view that is called this method.
     */
    public void viewBadgeDetails(View view)
    {
        if (view instanceof LinearLayout)
        {
            LinearLayout selectedLayout = (LinearLayout) view;
            Badge selectedBadge = (Badge) selectedLayout.getTag();
            Log.i("EcoQuest", selectedBadge.toString());
            Intent detailsIntent = new Intent (this, BadgeDetailsActivity.class);

            detailsIntent.putExtra("SelectedBadge", selectedBadge);
            startActivity(detailsIntent);



        }
    }

    /**
     * Called from onCreate to set the ImageView.
     * Allowing the user to set the profile picture on the account.
     * @param context
     * @param resId
     * @return
     */
    public static Uri getUriFromResource(Context context, int resId)
    {
        Resources res = context.getResources();
        // Build a String in the form:
        // edu.orangecoastcollege.cs273.ecoquest/drawable/none
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(resId) + "/"
                + res.getResourcePackageName(resId) + "/"
                + res.getResourcePackageName(resId);

        return Uri.parse(uri);

    }

    /**
     * This method checks if the user had allowed access to camera, read storage,
     * and write storage permissions.
     * If permission is allowed, gallery intent is loaded from onActivityResult method.
     * If there is not permission the user will be prompted again to allow permission.
     * @param view The view that is called this method.
     */

    public void selectProfilePicture(View view)
    {
        List<String> permissionList = new ArrayList<>();

        // Check permissions:
        int hasCameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        if (hasCameraPermission == DENIED)
            permissionList.add(android.Manifest.permission.CAMERA);

        int hasReadStoragePermissions = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasReadStoragePermissions == DENIED)
            permissionList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);

        int hasWriteStoragePermissions = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermissions == DENIED)
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // Some permissions have not been granted
        if (permissionList.size() > 0)
        {
            // Convert permissionList into array
            String[] permissionsArray = new String[permissionList.size()];
            permissionList.toArray(permissionsArray);

            // Ask user for permission:
            ActivityCompat.requestPermissions(this, permissionsArray, 1969);
        }

        // Verify all permissions needed are granted to access image gallery:
        if (hasCameraPermission == GRANTED && hasReadStoragePermissions == GRANTED && hasWriteStoragePermissions == GRANTED)
        {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 1);

        }



    }

    /**
     * This method is called once permission had been granted to load
     * gallery intent allowing the user to select an image.
     *
     * @param requestCode
     * @param resultCode
     * @param data The intent the data is loaded to
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            // data is the data from GalleryIntent (the uri of some image)
            imageUri = data.getData();
            mProfilePicture.setImageURI(imageUri);
        }

    }




}
