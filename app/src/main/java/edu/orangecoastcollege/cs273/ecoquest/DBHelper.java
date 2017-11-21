package edu.orangecoastcollege.cs273.ecoquest;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Brendan and Casey
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    // Database version and name
    public static final String DATABASE_NAME = "ecoQuest";
    private static final int DATABASE_VERSION = 1;

    // Define the fields (column names) for the badges table
    private static final String BADGES_TABLE = "badges";
    private static final String BADGES_KEY_FIELD_ID = "_id";
    private static final String BADGES_FIELD_NAME = "name";
    private static final String BADGES_FIELD_DESCRIPTION = "description";
    private static final String BADGES_FIELD_IMAGE_NAME = "image_name";

    // Define the fields for the users table
    private static final String USERS_TABLE = "users";
    private static final String USERS_KEY_FIELD_ID = "_id";
    private static final String USERS_FIELD_USERNAME = "username";
    private static final String USERS_FIELD_POINTS = "points";
    private static final String USERS_FIELD_LEVEL = "level";
    private static final String USERS_FIELD_PROFILE_PICTURE_NAME = "profile_pic_name";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createQuery = "CREATE TABLE " + BADGES_TABLE + "("
                + BADGES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + BADGES_FIELD_NAME + " TEXT, "
                + BADGES_FIELD_DESCRIPTION + " TEXT, "
                + BADGES_FIELD_IMAGE_NAME + " TEXT" + ")";
        database.execSQL(createQuery);

        createQuery = "CREATE TABLE " + USERS_TABLE + "("
                + USERS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + USERS_FIELD_USERNAME + " TEXT, "
                + USERS_FIELD_POINTS + " INTEGER, "
                + USERS_FIELD_LEVEL + " INTEGER, "
                + USERS_FIELD_PROFILE_PICTURE_NAME + " TEXT " + ")";
        database.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + BADGES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        onCreate(database);
    }

    /* START OF BADGE RELATED CODE */

    public void addBadge(Badge badge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BADGES_FIELD_NAME, badge.getName());
        values.put(BADGES_FIELD_DESCRIPTION, badge.getDescription());
        values.put(BADGES_FIELD_IMAGE_NAME, badge.getImageName());

        db.insert(BADGES_TABLE, null, values);

        db.close();
    }

    public List<Badge> getAllBadges() {
        List<Badge> badgesList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                BADGES_TABLE,
                new String[]{BADGES_KEY_FIELD_ID, BADGES_FIELD_NAME, BADGES_FIELD_DESCRIPTION, BADGES_FIELD_IMAGE_NAME},
                null,
                null,
                null, null, null, null);

        // collect each row in the table
        if (cursor.moveToFirst()) {
            do {
                Badge badge = new Badge(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                badgesList.add(badge);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return badgesList;
    }

    public Badge getBadge(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                BADGES_TABLE,
                new String[]{BADGES_KEY_FIELD_ID, BADGES_FIELD_NAME, BADGES_FIELD_DESCRIPTION, BADGES_FIELD_IMAGE_NAME},
                BADGES_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Badge badge = new Badge(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        cursor.close();
        db.close();
        return badge;
    }

    /* END OF BADGE RELATED CODE*/



    /* START OF USER-RELATED METHODS */

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USERS_FIELD_USERNAME, user.getUserName());
        values.put(USERS_FIELD_POINTS, user.getPoints());
        values.put(USERS_FIELD_LEVEL, user.getLevel());
        values.put(USERS_FIELD_PROFILE_PICTURE_NAME, user.getProfilePictureName());

        long id = db.insert(USERS_TABLE, null, values);

        user.setId(id);

        db.close();
    }

    public List<User> getMostPointsUserList() {
        // create a list of users ordered from most points to least points
        /*
            SELECT _id, points FROM users ORDER BY points ASC;
         */
        SQLiteDatabase db = this.getReadableDatabase();
        /*String queryString =
                "SELECT " + USERS_KEY_FIELD_ID + ", " + USERS_FIELD_POINTS
                + " FROM " + USERS_TABLE + " ORDER BY " + USERS_FIELD_POINTS;*/
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID, USERS_FIELD_USERNAME, USERS_FIELD_POINTS, USERS_FIELD_LEVEL, USERS_FIELD_PROFILE_PICTURE_NAME},
                null, null,
                null, null, null, null);


        List<User> mMostPointsUserList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getLong(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
                mMostPointsUserList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return mMostPointsUserList;
    }

    /* END OF USER-RELATED METHODS */

    /* START OF IMPORTING FROM CSV METHODS */

    boolean importUsersFromCSV(String csvFileName) {
        AssetManager assetManager = mContext.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    Log.d("ecoQuest", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                }
                else {
                    String userName = fields[0].trim();
                    int points = Integer.parseInt(fields[1].trim());
                    int level = Integer.parseInt(fields[2].trim());
                    String profilePictureName = fields[3].trim();
                    addUser( new User(userName, points, level, profilePictureName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    /* END OF IMPORTING FROM CSV METHODS */
}
