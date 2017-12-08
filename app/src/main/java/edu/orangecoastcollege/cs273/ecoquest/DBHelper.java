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
 *
 * The DBHelper class extends, and therefore "is an", SQLiteOpenHelper.
 *
 * This veritable monolith of a Model class contains all the member variables and methods responsible
 * for persisting and retrieving data in and from the SQLite database.
 *
 * The tables in the ecoQuest database are:
 *  - badges (Brendan & Casey)
 *  - users (Brendan & Casey)
 *  - quests (Brendan & Casey)
 *  - titles (Casey)
 *  - Locations (Casey)
 *
 *  Each table has many private static final variables corresponding to the fields of that table.
 *
 *  The methods in DBHelper include:
 *   - constructor
 *   - onCreate  (must be implemented when extending SQLiteOpenHelper)
 *   - onUpgrade (must be implemented when extending SQLiteOpenHelper)
 *   - addBadge, getBadge, getAllBadges
 *   - addQuest, getQuest, getAllQuests
 *   - getTitle
 *   - addUser, getUserList
 *   - addLocation, getAllQuestLocations
 *   - importUsersFromCSV, importQuestsFromCSV, importBadgesFromCSV, and importLocationsFromCSV
 *   - helper methods, convertStringToList, convertListToString
 *
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
    private static final String BADGES_FIELD_CURRENT_PROGRESS = "current_progress";
    private static final String BADGES_FIELD_MAX_PROGRESS = "max_progress";

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
    private static final String QUEST_FIELD_QUEST_TYPES = "quest_types";

    // Define the fields (column names) for the Titles table
    private static final String TITLE_TABLE = "titles";
    private static final String TITLE_KEY_FIELD_ID = "_id";
    private static final String TITLE_FIELD_NAME = "name";

    // Define the fields (column names) for the Location table
    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE CAFFEINE LOCATIONS TABLE
    private static final String LOCATIONS_TABLE = "Locations";
    private static final String LOCATIONS_KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_ZIP_CODE = "zip_code";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_LONGITUDE = "longitude";


    /**
     * DBHelper
     * @param context
     */
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
                + BADGES_FIELD_IMAGE_NAME + " TEXT, "
                + BADGES_FIELD_CURRENT_PROGRESS + " INTEGER, "
                + BADGES_FIELD_MAX_PROGRESS + " INTEGER " +  ")";
        database.execSQL(createQuery);

        // Creating User Data Table
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
                + QUEST_FIELD_IMAGE_NAME + " TEXT,"
                + QUEST_FIELD_CURRENT_PROGRESS + " INTEGER, "
                + QUEST_FIELD_MAX_PROGRESS + " INTEGER, "
                + QUEST_FIELD_QUEST_TYPES + " TEXT " + ")";
        database.execSQL(createQuery);

       /* // Creating Title Data Table
        createQuery = "CREATE TABLE "+ TITLE_TABLE + "("
                + TITLE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + TITLE_FIELD_NAME + "TEXT" + ")"; */


       // Creating Locations Data Table
        createQuery = "CREATE TABLE " + LOCATIONS_TABLE + "("
                + LOCATIONS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_ADDRESS + " TEXT, "
                + FIELD_CITY + " TEXT,"
                + FIELD_STATE + " TEXT,"
                + FIELD_ZIP_CODE + " TEXT,"
                + FIELD_PHONE + " TEXT,"
                + FIELD_LATITUDE + " REAL,"
                + FIELD_LONGITUDE + " REAL"
                + ")";
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
        values.put(BADGES_FIELD_CURRENT_PROGRESS, badge.getProgress());
        values.put(BADGES_FIELD_MAX_PROGRESS, badge.getMaxProgress());

        db.insert(BADGES_TABLE, null, values);

        db.close();
    }

    public Badge getBadge(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                BADGES_TABLE,
                new String[]{BADGES_KEY_FIELD_ID,
                        BADGES_FIELD_NAME,
                        BADGES_FIELD_DESCRIPTION,
                        BADGES_FIELD_IMAGE_NAME,
                        BADGES_FIELD_CURRENT_PROGRESS,
                        BADGES_FIELD_MAX_PROGRESS},
                BADGES_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Badge badge = new Badge(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5));

        cursor.close();
        db.close();
        return badge;
    }

    public List<Badge> getAllBadges()
    {
        List<Badge> badgeList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                BADGES_TABLE,
                new String[]{BADGES_KEY_FIELD_ID,
                        BADGES_FIELD_NAME,
                        BADGES_FIELD_DESCRIPTION,
                        BADGES_FIELD_IMAGE_NAME,
                        BADGES_FIELD_CURRENT_PROGRESS,
                        BADGES_FIELD_MAX_PROGRESS},
                null,
                null,
                null, null, null, null);

        // Collect each row in the table
        if (cursor.moveToFirst())
        {
            do {
                Badge badge = new Badge(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5));

                badgeList.add(badge);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return badgeList;
    }

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
        values.put(QUEST_FIELD_MAX_PROGRESS, quest.getMaxProgress());
        values.put(QUEST_FIELD_QUEST_TYPES, convertListToString(quest.getQuestTypes()));

        Long id = db.insert(QUEST_TABLE, null, values);

        quest.setId(id);

        db.close();
    }



    public Quest getQuest(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                QUEST_TABLE,
                new String[]{QUEST_KEY_FIELD_ID,
                        QUEST_FIELD_NAME,
                        QUEST_FIELD_DESCRIPTION,
                        QUEST_FIELD_IMAGE_NAME,
                        QUEST_FIELD_CURRENT_PROGRESS,
                        QUEST_FIELD_MAX_PROGRESS,
                        QUEST_FIELD_QUEST_TYPES},
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
                cursor.getInt(5),
                convertStringToList(cursor.getString(6)));

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
                new String[]{QUEST_KEY_FIELD_ID,
                        QUEST_FIELD_NAME,
                        QUEST_FIELD_DESCRIPTION,
                        QUEST_FIELD_IMAGE_NAME,
                        QUEST_FIELD_CURRENT_PROGRESS,
                        QUEST_FIELD_MAX_PROGRESS,
                        QUEST_FIELD_QUEST_TYPES},
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
                        cursor.getInt(5),
                        convertStringToList(cursor.getString(6)));
                questList.add(quest);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return questList;
    }


    /* END OF QUEST CODE */


    /* START TITLE CODE */

    public Title getTitle(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TITLE_TABLE,
            new String[]{TITLE_KEY_FIELD_ID, TITLE_FIELD_NAME},
            QUEST_KEY_FIELD_ID + "=?",
             new String[]{String.valueOf(id)},
             null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Title title = new Title(
                cursor.getLong(0),
                cursor.getString(1));

        cursor.close();
        db.close();
        return title;
    }


    /* END OF TITLE CODE */


    /* START OF USER-RELATED METHODS */

    /**
     * (Brendan)
     * addUser is called many times from the importUsersFromCSV method.
     * Each row of the csv file means a new User to add to the database.
     *
     * A writable database reference is gotten,
     * a ContentValues object is instantiated,
     * all of the attributes of the User argument are put into the ContentValues object,
     * the values object is then inserted into the database and an id is returned and stored.
     * This id is then set on the User before the database connection is closed.
     * @param user
     */
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

    /**
     * (Brendan)
     * getUserList creates a readable reference to the SQLite database.
     * A Cursor object is created using a query to retrieve data from all of the fields of
     * the User table.
     *
     * A List of Users is populated using a while loop to loop through
     * all of the Users obtained from the Cursor.
     *
     * Once all the Users have been found and added to the List, the cursor and database
     * are closed.
     *
     * The List of Users is returned.
     * @return
     */
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

    /*  START OF LOCATION METHODS  */

    public void addLocation(QuestLocations questLocations) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, questLocations.getName());
        values.put(FIELD_ADDRESS, questLocations.getAddress());
        values.put(FIELD_CITY, questLocations.getCity());
        values.put(FIELD_STATE, questLocations.getState());
        values.put(FIELD_ZIP_CODE, questLocations.getZipCode());
        values.put(FIELD_LATITUDE, questLocations.getLatitude());
        values.put(FIELD_LONGITUDE, questLocations.getLongitude());

        long id = db.insert(LOCATIONS_TABLE, null, values);
        questLocations.setId(id);
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<QuestLocations> getAllQuestLocations() {
        ArrayList<QuestLocations> locationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOCATIONS_TABLE,
                new String[]{LOCATIONS_KEY_FIELD_ID, FIELD_NAME, FIELD_ADDRESS, FIELD_CITY, FIELD_STATE, FIELD_ZIP_CODE, FIELD_LATITUDE, FIELD_LONGITUDE},
                null,
                null,
                null, null, null, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                QuestLocations questLocations =
                        new QuestLocations(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getDouble(6),
                                cursor.getDouble(7));
                locationsList.add(questLocations);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return locationsList;
    }
    /*  END OF LOCATION METHODS  */


    /* START OF IMPORTING FROM CSV METHODS */

    /**
     * (Brendan)
     * The importUsersFromCSV method is used to read the "users.csv" comma-separated value file
     * in the assets directory. The database is populated with new Users using the data in each
     * row of the users.csv file.
     *
     * In the future this method will likely be deprecated in favor of a JSON implementation.
     * @param csvFileName
     * @return
     */
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

    /**
     * (Brendan)
     * The importQuestsFromCSV method is used to read the "quests.csv" comma-separated value
     * file in the assets directory.  The database is populated by creating a new Quest
     * using the data in each row of the file.
     *
     * The convertStringToList helper method allows us to store data in the csv as space separated
     * values and turns that into a List of Integers.  This was helpful for converting the
     * QuestTypes.  In the future this will probably be converted to a JSON file format reader,
     * to prevent awkward conversions between the datafile and how it is stored in the SQLite db.
     * @param csvFileName
     * @return
     */
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
                    int maxProgress     = Integer.parseInt(fields[3].trim());
                    String questTypes   = fields[4].trim();
                    Quest quest = new Quest(questName, questDesc, questIcon, maxProgress, convertStringToList(questTypes));
                    addQuest(quest);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private List<Integer> convertStringToList(String csvString)
    {
        List<Integer> questTypesList = new ArrayList<>();

        //csvString = "0 4 5"
        String[] values = csvString.trim().split(" ");
        //values = ["0"|"4"|"5"]

        for (String value : values)
            questTypesList.add(Integer.parseInt(value));

        return questTypesList;
    }

    private String convertListToString(List<Integer> integerList)
    {
        String questTypesString = "";

        for (Integer i : integerList)
            questTypesString += " " + String.valueOf(i);

        return questTypesString;
    }


    // Import Badges from csv file
    boolean importBadgesFromCSV(String csvFileName)
    {
        AssetManager assetManager = mContext.getAssets();
        InputStream inputStream = null;
        try{
            inputStream = assetManager.open(csvFileName);
        } catch (IOException e){
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while((line = bufferedReader.readLine()) != null)
            {
                String[] fields = line.split(",");
                if (fields.length != 4 )
                {
                    Log.d("ecoQuest", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                }
                else
                {
                    String badgeName = fields[0].trim();
                    String badgeDesc = fields[1].trim();
                    String badgeIcon = fields[2].trim();
                    int maxProgress  = Integer.parseInt(fields[3].trim());

                    Badge badge = new Badge(badgeName, badgeDesc, badgeIcon, maxProgress);
                    addBadge(badge);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    // Import Locations from csv file

    public boolean importLocationsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 8) {
                    Log.d("Quest Locations", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                long id = Long.parseLong(fields[0].trim());
                String name = fields[1].trim();
                String address = fields[2].trim();
                String city = fields[3].trim();
                String state = fields[4].trim();
                String zipCode = fields[5].trim();
                double latitude = Double.parseDouble(fields[6].trim());
                double longitude = Double.parseDouble(fields[7].trim());
                addLocation(new QuestLocations(id, name, address, city, state, zipCode, latitude, longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /* END OF IMPORTING FROM CSV METHODS */
}



