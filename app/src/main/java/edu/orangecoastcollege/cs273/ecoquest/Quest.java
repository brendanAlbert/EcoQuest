package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by CaseyTea on 11/16/17.
 *
 * Edits made by Brendan include:
 *  added current and max progress.
 *  I needed these for quest completion icon selection.
 */

public class Quest implements Parcelable {

    private long mId;
    private String mName;
    private String mDescription;
    private String mImageName;
    private int mCurrentProgress; // quests can be 0/1, 0/5, 0/10, 0/25, etc..
    private int mMaxProgress;
    // quest types from the QuestType class will populate this array at their respective indices
    private List<Integer> mQuestTypes;

    public Quest()
    {
        this(-1, "", "", "avatar.png", -1, null);
    }

    public Quest(String name, String description, String imageName, int maxProgress, List<Integer> questTypes)
    {
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = 0;
        mMaxProgress = maxProgress;
        mQuestTypes = questTypes;
    }

    public Quest(long id, String name, String description, String imageName, int maxProgress, List<Integer> questTypes)
    {
        mId = id;
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = 0;
        mMaxProgress = maxProgress;
        mQuestTypes = questTypes;

    }

    // Parcelable interface uses a private constructor to instantiate objects
    private Quest(Parcel parcel)
    {
        mId = parcel.readLong();
        mName = parcel.readString();
        mDescription = parcel.readString();
        mImageName = parcel.readString();
        mCurrentProgress = parcel.readInt();
        mMaxProgress = parcel.readInt();

    }

    // Getter methods:


    /**
     * Method to get name of quest description.
     * @return Returns the name of the quest.
     */
    public String getName() { return mName; }

    /**
     * Method to get the description of the quest.
     * @return Returns the description of the quest.
     */
    public String getDescription() { return mDescription; }

    /**
     * Method to get the image name of the quest icon.
     * @return Returns the image file name for the quest icon.
     */
    public String getImageName() { return mImageName; }

    // Setter methods:

    /**
     * Method that sets the name of the quest.
     * @param name Name of the quest.
     */
    public void setName(String name) { mName = name; }

    /**
     * Method that sets the description of the quest.
     * @param description Description of the quest.
     */
    public void setDescription(String description) { mDescription = description; }

    /**
     * Method that sets the image name of the quest.
     * @param imageName image file name of quest icon.
     */
    public void setImageName(String imageName) { mImageName = imageName; }

    public int getCurrentProgress() {
        return mCurrentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        mCurrentProgress = currentProgress;
    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        mMaxProgress = maxProgress;
    }
    // Parcel methods

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mImageName);
        dest.writeInt(mCurrentProgress);
        dest.writeInt(mMaxProgress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quest> CREATOR = new Creator<Quest>() {
        @Override
        public Quest createFromParcel(Parcel in) {
            return new Quest(in);
        }

        @Override
        public Quest[] newArray(int size) {
            return new Quest[size];
        }
    };
}
