package edu.orangecoastcollege.cs273.ecoquest;

/**
 * Created by brendantyleralbert on 11/19/17.
 */

public class User {

    private long mId;
    private String mUserName;
    private int mLevel;
    private int mPoints;
    private String mProfilePictureName;

    public User(long id, String userName, int level, int points, String profilePictureName) {
        mId = id;
        mUserName = userName;
        mLevel = level;
        mPoints = points;
        mProfilePictureName = profilePictureName;
    }

    public User(String userName, int level, int points, String profilePictureName)  { this(-1, userName, level, points, profilePictureName); }

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

    public void setProfilePictureName(String profilePictureName) {
        mProfilePictureName = profilePictureName;
    }

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
}
