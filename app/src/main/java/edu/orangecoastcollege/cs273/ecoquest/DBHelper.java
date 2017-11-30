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
    private static final String USERS_FIELD_HOW_MANY_BADGES = "how_many_badges";
    private static final String USERS_FIELD_PROFILE_PICTURE_NAME = "profile_pic_name";


    // Define the fields (column names) for the quest table
    private static final String QUEST_TABLE = "quests";
    private static final String QUEST_KEY_FIELD_ID = "_id";
    private static final String QUEST_FIELD_NAME = "name";
    private static final String QUEST_FIELD_DESCRIPTION = "description";
    private static final String QUEST_FIELD_IMAGE_NAME = "image_name";
    private static final String QUEST_FIELD_CURRENT_PROGRESS = "current_progress";
    private static final String QUEST_FIELD_MAX_PROGRESS = "max_progress";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        // Creating Badge Data Table
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
                + USERS_FIELD_HOW_MANY_BADGES + " INTEGER, "
                + USERS_FIELD_PROFILE_PICTURE_NAME + " TEXT " + ")";
        database.execSQL(createQuery);

        // Creating Quest Data Table
        createQuery = "CREATE TABLE " + QUEST_TABLE + "("
                + QUEST_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + QUEST_FIELD_NAME + " TEXT, "
                + QUEST_FIELD_DESCRIPTION + " TEXT, "
                + QUEST_FIELD_IMAGE_NAME + " TEXT" + ")";
        database.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + BADGES_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + QUEST_TABLE);
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

    // TODO: Create get all badges method

    /* END OF BADGE RELATED CODE*/


    /* START OF QUEST CODE */

    /**
     * This method add a new quest into the SQLite database
     * @param quest Quest is an object we are adding into the database
     */
    public void addQuest(Quest quest)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(QUEST_FIELD_NAME, quest.getName());
        values.put(QUEST_FIELD_DESCRIPTION, quest.getDescription());
        values.put(QUEST_FIELD_IMAGE_NAME, quest.getImageName());
        values.put(QUEST_FIELD_CURRENT_PROGRESS, quest.getCurrentProgress());
        values.put(QUEST_FIELD_MAX_PROGRESS, quest.getMaxProgress());

        db.insert(QUEST_TABLE, null, values);

        db.close();
    }

    public Quest getQuest(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                QUEST_TABLE,
                new String[]{QUEST_KEY_FIELD_ID, QUEST_FIELD_NAME, QUEST_FIELD_DESCRIPTION, QUEST_FIELD_IMAGE_NAME},
                QUEST_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Quest quest = new Quest(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5));

        cursor.close();
        db.close();
        return quest;
    }


    public List<Quest> getAllQuests()
    {
        List<Quest> questList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                QUEST_TABLE,
                new String[]{QUEST_KEY_FIELD_ID, QUEST_FIELD_NAME, QUEST_FIELD_DESCRIPTION, QUEST_FIELD_IMAGE_NAME, QUEST_FIELD_CURRENT_PROGRESS, QUEST_FIELD_MAX_PROGRESS},
                null,
                null,
                null, null, null, null);

        // collect each row in the table
        if (cursor.moveToFirst()) {
            do {
                Quest quest = new Quest(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5));
                questList.add(quest);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return questList;
    }


    /* END OF QUEST CODE */



    /* START OF USER-RELATED METHODS */

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USERS_FIELD_USERNAME, user.getUserName());
        values.put(USERS_FIELD_POINTS, user.getPoints());
        values.put(USERS_FIELD_LEVEL, user.getLevel());
        values.put(USERS_FIELD_HOW_MANY_BADGES, user.getHowManyBadges());
        values.put(USERS_FIELD_PROFILE_PICTURE_NAME, user.getProfilePictureName());

        long id = db.insert(USERS_TABLE, null, values);

        user.setId(id);

        db.close();
    }

    public List<User> getUserList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID, USERS_FIELD_USERNAME, USERS_FIELD_POINTS, USERS_FIELD_LEVEL, USERS_FIELD_HOW_MANY_BADGES, USERS_FIELD_PROFILE_PICTURE_NAME},
                null, null,
                null, null, null, null);

        List<User> mUserList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getLong(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
                mUserList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return mUserList;
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
                if (fields.length != 5) {
                    Log.d("ecoQuest", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                }
                else {
                    String userName = fields[0].trim();
                    int points = Integer.parseInt(fields[1].trim());
                    int level = Integer.parseInt(fields[2].trim());
                    int badges = Integer.parseInt(fields[3].trim());
                    String profilePictureName = fields[4].trim();
                    addUser( new User(userName, points, level, badges, profilePictureName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    boolean importQuestsFromCSV(String csvFileName) {
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
                if (fields.length != 5) {
                    Log.d("ecoQuest", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                }
                else {
                    String questName    = fields[0].trim();
                    String questDesc    = fields[1].trim();
                    String questIcon    = fields[2].trim();
                    int currentProgress = Integer.parseInt(fields[3].trim());
                    int maxProgress     = Integer.parseInt(fields[4].trim());
                    addQuest(new Quest(questName, questDesc, questIcon, currentProgress, maxProgress));
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



