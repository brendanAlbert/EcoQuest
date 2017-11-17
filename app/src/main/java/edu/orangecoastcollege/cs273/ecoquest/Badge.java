package edu.orangecoastcollege.cs273.ecoquest;

/**
 * Created by brendantyleralbert on 11/16/17.
 */

public class Badge {

    private long mId;
    private String mName;
    private String mDescription;
    private String mImageName;

    public Badge(long id, String name, String description, String imageName) {
        mId = id;
        mName = name;
        mDescription = description;
        mImageName = imageName;
    }

    public Badge(String name, String description, String imageName) { this(-1, name, description, imageName); }

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
}
