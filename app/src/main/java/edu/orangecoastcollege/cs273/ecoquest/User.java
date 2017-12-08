package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brendantyleralbert on 11/19/17.
 *
 * User is a model class that represents a user of ecoQuest.
 *
 * User implements Parcelable because there are many places in the app
 * where a User object is passed via Intent between Activities.
 */

public class User implements Parcelable {

    private long mId;
    private String mUserName;
    private int mLevel;
    private int mPoints;
    private int mHowManyBadges;
    private String mProfilePictureName;  // Storing URI in this variable - Casey

    /**
     * User is a parameterized constructor that accepts all of the member variables as arguments.
     * @param id
     * @param userName
     * @param level
     * @param points
     * @param howManyBadges
     * @param profilePictureName
     */
    public User(long id, String userName, int level, int points, int howManyBadges, String profilePictureName) {
        mId = id;
        mUserName = userName;
        mLevel = level;
        mPoints = points;
        mHowManyBadges = howManyBadges;
        mProfilePictureName = profilePictureName;
    }

    /**
     * This is another parameterized constructor which does not include the user's id as an argument.
     * Inside this constructor we call the above constructor using 'this' and pass in a junk id of
     * -1.  The id will be set in DBhelper when we call addUser().
     * @param userName
     * @param level
     * @param points
     * @param howManyBadges
     * @param profilePictureName
     */
    public User(String userName, int level, int points, int howManyBadges, String profilePictureName)  { this(-1, userName, level, points, howManyBadges, profilePictureName); }

    /**
     * This private constructor is used by the Parcelable interface.
     * All of the member variables are read from the Parcel argument
     * and used to populate a User.
     * @param parcel
     */
    private User(Parcel parcel)
    {
        mId = parcel.readLong();
        mUserName = parcel.readString();
        mLevel = parcel.readInt();
        mPoints = parcel.readInt();
        mHowManyBadges = parcel.readInt();
        mProfilePictureName = parcel.readString();
    }

    /**
     * getId returns the User's id
     * @return
     */
    public long getId() {
        return mId;
    }

    /**
     * setId sets the User's id with the provided long id argument.
     * @param id
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * getUserName returns the User's userName
     * @return
     */
    public String getUserName() {
        return mUserName;
    }

    /**
     * setUserName sets the User's userName. This method is not used currently because the userName
     * is set in the constructor.
     * @param userName
     */
    public void setUserName(String userName) {
        mUserName = userName;
    }

    /**
     * getLevel returns the User's level
     * @return
     */
    public int getLevel() {
        return mLevel;
    }

    /**
     * setLevel sets the User's level using the provided int argument.
     * The method is not used currently because the level is set in the constructor.
     * @param level
     */
    public void setLevel(int level) {
        mLevel = level;
    }

    /**
     * getPoints returns the User's points.
     * @return
     */
    public int getPoints() {
        return mPoints;
    }

    /**
     * setPoints sets the User's points using the provided int argument.
     * The method is unused currently because we set the points in the constructor.
     * @param points
     */
    public void setPoints(int points) {
        mPoints = points;
    }

    /**
     * getProfilePictureName returns the User's profile picture name.
     * @return
     */
    public String getProfilePictureName() {
        return mProfilePictureName;
    }

    /**
     * setProfilePictureName sets the User's profile picture name using the provided String argument.
     * @param profilePictureName
     */
    public void setProfilePictureName(String profilePictureName) { mProfilePictureName = profilePictureName; }

    /**
     * getHowManyBadges returns how many badges the User has earned.
     * @return
     */
    public int getHowManyBadges() { return mHowManyBadges; }

    /**
     * setHowManyBadges sets the User's badge amount.
     * This method is unused at the moment.  The badge amount is set in the constructor.
     * @param howManyBadges
     */
    public void setHowManyBadges(int howManyBadges) { mHowManyBadges = howManyBadges; }

    /**
     * toString is used to provide a String representation of the User object.
     * This method is very helpful for debugging.
     * @return
     */
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

    /**
     * writeToParcel is a method that must be implemented to make a User parcelable.
     * It is important to note that the order the Parcel is written in
     * is the same order it must be read.
     * @param dest
     * @param i
     */
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(mId);
        dest.writeString(mUserName);
        dest.writeInt(mLevel);
        dest.writeInt(mPoints);
        dest.writeInt(mHowManyBadges);
        dest.writeString(mProfilePictureName);
    }

    /**
     * describeContents must be implemented when using Parcelable.
     * @return
     */
    @Override
    public int describeContents() { return 0; }

    /**
     * Creator<User> is an anonymous inner class that must be implemented
     * when using Parcelable.
     */
    public static final Creator<User> CREATOR = new Creator<User>()
    {
        /**
         * createFromParcel must be implemented when using the Creator inner class.
         * @param parcel
         * @return
         */
        @Override
        public User createFromParcel(Parcel parcel) { return new User(parcel); }

        /**
         * newArray must be implemented when using the Creator inner class.
         * @param size
         * @return
         */
        @Override
        public User[] newArray(int size) { return new User[size]; }
    };
}
