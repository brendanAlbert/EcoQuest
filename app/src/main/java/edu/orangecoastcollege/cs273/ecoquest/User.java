package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brendantyleralbert on 11/19/17.
 */

public class User implements Parcelable {

    private long mId;
    private String mUserName;
    private int mLevel;
    private int mPoints;
    private int mHowManyBadges;
    private String mProfilePictureName;  // Storing URI in this variable - Casey

    public User(long id, String userName, int level, int points, int howManyBadges, String profilePictureName) {
        mId = id;
        mUserName = userName;
        mLevel = level;
        mPoints = points;
        mHowManyBadges = howManyBadges;
        mProfilePictureName = profilePictureName;
    }

    public User(String userName, int level, int points, int howManyBadges, String profilePictureName)  { this(-1, userName, level, points, howManyBadges, profilePictureName); }

    private User(Parcel parcel)
    {
        mId = parcel.readLong();
        mUserName = parcel.readString();
        mLevel = parcel.readInt();
        mPoints = parcel.readInt();
        mHowManyBadges = parcel.readInt();
        mProfilePictureName = parcel.readString();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        mPoints = points;
    }

    public String getProfilePictureName() {
        return mProfilePictureName;
    }

    public void setProfilePictureName(String profilePictureName) { mProfilePictureName = profilePictureName; }

    public int getHowManyBadges() { return mHowManyBadges; }

    public void setHowManyBadges(int howManyBadges) { mHowManyBadges = howManyBadges; }

    @Override
    public String toString() {
        return "User{" +
                "mId=" + mId +
                ", mUserName='" + mUserName + '\'' +
                ", mLevel=" + mLevel +
                ", mPoints=" + mPoints +
                ", mProfilePictureName='" + mProfilePictureName + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(mId);
        dest.writeString(mUserName);
        dest.writeInt(mLevel);
        dest.writeInt(mPoints);
        dest.writeInt(mHowManyBadges);
        dest.writeString(mProfilePictureName);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<User> CREATOR = new Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel parcel) { return new User(parcel); }

        @Override
        public User[] newArray(int size) { return new User[size]; }
    };
}
