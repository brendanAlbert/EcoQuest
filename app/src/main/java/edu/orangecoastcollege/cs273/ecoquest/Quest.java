package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Quest is a Model class of the quest that can be completed.
 *
 * Quest can be accessed from the QuestActivity and the MapActivity displaying each quest
 * inside of a listview.
 *
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
    // quest types from the QuestType class will populate this array
    private List<Integer> mQuestTypes;

    /**
     * Default constructor of the Quest class using default arguments to be passed.
     *
     */
    public Quest()
    {
        this(-1, "", "", "avatar.png", -1, new ArrayList<Integer>());
    }

    /**
     * Parameterize constructor using all the member variables except id and current progress to be
     * used as arguments.
     *
     * Current progress set to 0 since no progress can be logged if quest has just been created.
     *
     * @param name
     * @param description
     * @param imageName
     * @param maxProgress
     * @param questTypes
     */
    public Quest(String name, String description, String imageName, int maxProgress, List<Integer> questTypes)
    {
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = 0;
        mMaxProgress = maxProgress;
        mQuestTypes = questTypes;
    }

    /**
     * Parameterize constructor using all member variables except current progress to be
     * used as arguments.
     *
     * Current progress set to 0 since no progress can be logged if quest has just been created.
     *
     * @param id
     * @param name
     * @param description
     * @param imageName
     * @param maxProgress
     * @param questTypes
     */
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

    /**
     * Parameterize constructor using all member variables to be used as arguments.
     * @param id
     * @param name
     * @param description
     * @param imageName
     * @param currentProgress
     * @param maxProgress
     * @param questTypes
     */
    public Quest(long id, String name, String description, String imageName, int currentProgress, int maxProgress, List<Integer> questTypes)
    {
        mId = id;
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = currentProgress;
        mMaxProgress = maxProgress;
        mQuestTypes = questTypes;

    }

    /**
     * Parcelable interface uses a private constructor to instantiate objects
     * @param parcel
     */
    private Quest(Parcel parcel)
    {
        mId = parcel.readLong();
        mName = parcel.readString();
        mDescription = parcel.readString();
        mImageName = parcel.readString();
        mCurrentProgress = parcel.readInt();
        mMaxProgress = parcel.readInt();
        //parcel.readList(mQuestTypes, null);
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
     * method to set the id of the quest
     * @param id
     */
    public void setId(long id) { mId = id; }

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

    /**
     * Method that gets the current progress of the quest and returns it as an int.
     * @return
     */
    public int getCurrentProgress() {
        return mCurrentProgress;
    }

    /**
     *  Method that sets the current progress of the quest as an int argument.
     * @param currentProgress
     */
    public void setCurrentProgress(int currentProgress) {
        mCurrentProgress = currentProgress;
    }

    /**
     * Method that returns the max progress of the quest as an int.
     * @return
     */
    public int getMaxProgress() {
        return mMaxProgress;
    }

    /**
     * Method to set the max progress of the quest with an int argument.
     * @param maxProgress
     */
    public void setMaxProgress(int maxProgress) {
        mMaxProgress = maxProgress;
    }

    public List<Integer> getQuestTypes() { return mQuestTypes; }

    public void setQuestTypes(List<Integer> questTypes) { mQuestTypes = questTypes; }

    /**
     * toString is used to provide a String representation of the User object.
     * This method is very helpful for debugging.
     * @return
     */
    @Override
    public String toString() {
        return "Quest{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mImageName='" + mImageName + '\'' +
                ", mCurrentProgress=" + mCurrentProgress +
                ", mMaxProgress=" + mMaxProgress +
                ", mQuestTypes=" + mQuestTypes +
                '}';
    }

    // Parcel methods
    /**
     * writeToParcel is a method that must be implemented to make a User parcelable.
     * It is important to note that the order the Parcel is written in
     * is the same order it must be read.
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mImageName);
        dest.writeInt(mCurrentProgress);
        dest.writeInt(mMaxProgress);
        dest.writeList(mQuestTypes);
    }

    /**
     * describeContents must be implemented when using Parcelable.
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }


    /**
     * Creator<User> is an anonymous inner class that must be implemented
     * when using Parcelable.
     */
    public static final Creator<Quest> CREATOR = new Creator<Quest>() {

        /**
         * createFromParcel must be implemented when using the Creator inner class.
         * @param in
         * @return
         */
        @Override
        public Quest createFromParcel(Parcel in) {
            Log.i("createFromParcel", in.toString());
            return new Quest(in);
        }

        /**
         * newArray must be implemented when using the Creator inner class.
         * @param size
         * @return
         */
        @Override
        public Quest[] newArray(int size) {
            return new Quest[size];
        }
    };
}
