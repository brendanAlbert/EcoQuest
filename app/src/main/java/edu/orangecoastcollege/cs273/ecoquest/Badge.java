package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brendantyleralbert on 11/16/17.
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


    public Badge()
    {
        this(-1, "", "", "avatar.png", 0, 0);
    }

    public Badge(long id, String name, String description, String imageName, int currentProgress, int maxProgress) {
        mId = id;
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = currentProgress;
        mMaxProgress = maxProgress;
    }

    public Badge(String name, String description, String imageName, int maxProgress)
    {
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = 0;
        mMaxProgress = maxProgress;
    }

    public Badge(String name, String description, String imageName, int currentProgress,int maxProgress)
    {
        mName = name;
        mDescription = description;
        mImageName = imageName;
        mCurrentProgress = currentProgress;
        mMaxProgress = maxProgress;
    }

    // Parcelable interface uses a private constructor to instantiate objects
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

    public long getId() { return mId; }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImageName() {
        return mImageName;
    }

    public void setImageName(String imageName) {
        mImageName = imageName;
    }

    public void setProgress(int progress) {mCurrentProgress = progress; }

    public int getProgress() { return mCurrentProgress; }

    public void setMaxProgress(int maxProgress) { mMaxProgress = maxProgress; }

    public int getMaxProgress() { return mMaxProgress; }

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

    public static final Creator<Badge> CREATOR = new Creator<Badge>() {
        @Override
        public Badge createFromParcel(Parcel in) { return new Badge(in); }

        @Override
        public Badge[] newArray(int size) { return new Badge[size]; }
    };


}
