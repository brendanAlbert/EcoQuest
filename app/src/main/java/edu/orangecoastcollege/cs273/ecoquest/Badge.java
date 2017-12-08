package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brendantyleralbert on 11/16/17.
 *
 * Badges is a Model class of Badges that can be earned.
 *
 * Badge implements Parcelable because there are many places in the app
 * where a Badge object is passed via Intent between Activities.
 *
 * Casey Thatsanaphonh:
 * Adding Progress and max Progress
 */


public class Badge implements Parcelable{

    private long mId;
    private String mName;
    private String mDescription;
    private String mImageName;
    private int mCurrentProgress;
    private int mMaxProgress;


    /**
     * Default constructor calling a parametrized constructor to set default data to Badge.
     *
     */
    public Badge()
    {
        this(-1, "", "", "avatar.png", 0, 0);
    }

    /**
     * Parametrized constructor that accepts all member variables as arguments
     *
     * @param id
     * @param name
     * @param description
     * @param imageName
     * @param currentProgress
     * @param maxProgress
     */
    public Badge(long id, String name, String description, String imageName, int currentProgress, int maxProgress) {
        mId = id;
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = currentProgress;
        mMaxProgress = maxProgress;
    }

    /**
     * Parametrized constructor that accepts all member variables except id and current progress
     * as arguments.
     *
     * @param name
     * @param description
     * @param imageName
     * @param maxProgress
     */
    public Badge(String name, String description, String imageName, int maxProgress)
    {
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = 0;
        mMaxProgress = maxProgress;
    }

    /**
     * Parametrized constructor that accepts all member variables except id as arguments.
     *
     * @param name
     * @param description
     * @param imageName
     * @param currentProgress
     * @param maxProgress
     */
    public Badge(String name, String description, String imageName, int currentProgress,int maxProgress)
    {
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = currentProgress;
        mMaxProgress = maxProgress;
    }

    // Parcelable interface uses a private constructor to instantiate objects
    /**
     * Parcelable interface uses a private constructor to instantiate objects
     * @param parcel
     */
    private Badge(Parcel parcel)
    {
        mId = parcel.readLong();
        mName = parcel.readString();
        mDescription = parcel.readString();
        mImageName = parcel.readString();
        mCurrentProgress = parcel.readInt();
        mMaxProgress = parcel.readInt();
    }



    //public Badge(String name, String description, String imageName) { this(-1, name, description, imageName); }

    /**
     * getId method returns the id of the Badge as a long.
     * @return
     */
    public long getId() { return mId; }

    /**
     * setId method accepts a long id as an argument to set the id of the Badge.
     * @param id
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * getName method returns the name of the Badge as a String.
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * setName method accepts a string as an argument to set the name of the Badge.
     * @param name
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * getDescription method returns the description of how to earn the badge as a string.
     * @return
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * setDescription method sets the description of how to earn a badge as a string.
     * @param description
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * getImageName method returns the image name as a string.
     * @return
     */
    public String getImageName() {
        return mImageName;
    }

    /**
     * setImageName method accepts a string argument to set the name of an image of the object
     * Badge.
     * @param imageName
     */
    public void setImageName(String imageName) {
        mImageName = imageName;
    }

    /**
     * setProgress method sets the current progress of the Badge.
     * @param progress argument passed as integer to set progress of a Badge.
     */
    public void setProgress(int progress) {mCurrentProgress = progress; }

    /**
     * getProgress method returns the current progress of the Badge as an integer.
     * @return
     */
    public int getProgress() { return mCurrentProgress; }

    /**
     * setMaxProgress method sets the max progress needed to complete a Badge.
     * @param maxProgress argument passed as an integer.
     */
    public void setMaxProgress(int maxProgress) { mMaxProgress = maxProgress; }

    /**
     * getMaxProgress method returns the max progress needed to complete a Badge.
     * @return
     */
    public int getMaxProgress() { return mMaxProgress; }

    /**
     * writeToParcel is a method that must be implemented to make a Badge parcelable.
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
    }

    /**
     * describeContents must be implemented when using Parcelable.
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Badge> CREATOR = new Creator<Badge>() {
        /**
         * createFromParcel must be implemented when using the Creator inner class.
         * @param in
         * @return
         */
        @Override
        public Badge createFromParcel(Parcel in) { return new Badge(in); }

        /**
         * newArray must be implemented when using the Creator inner class.
         * @param size
         * @return
         */
        @Override
        public Badge[] newArray(int size) { return new Badge[size]; }
    };


}
