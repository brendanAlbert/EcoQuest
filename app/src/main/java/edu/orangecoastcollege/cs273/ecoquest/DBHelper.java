package edu.orangecoastcollege.cs273.ecoquest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Brendan and Casey
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    // Database version and name
    private static final String DATABASE_NAME = "ecoQuest";
    private static final int DATABASE_VERSION = 1;

    // Define the fields (column names) for the badges table
    private static final String BADGES_TABLE = "badges";
    private static final String BADGES_KEY_FIELD_ID = "_id";
    private static final String BADGES_FIELD_NAME = "name";
    private static final String BADGES_FIELD_DESCRIPTION = "description";
    private static final String BADGES_FIELD_IMAGE_NAME = "image_name";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + BADGES_TABLE);
        onCreate(database);
    }

    /* START OF BADGE ADDING CODE */

    public void addBadge(Badge badge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BADGES_FIELD_NAME, badge.getName());
        values.put(BADGES_FIELD_DESCRIPTION, badge.getDescription());
        values.put(BADGES_FIELD_IMAGE_NAME, badge.getImageName());

        db.insert(BADGES_TABLE, null, values);

        db.close();
    }



    /* END OF BADGE ADDING CODE*/
}
