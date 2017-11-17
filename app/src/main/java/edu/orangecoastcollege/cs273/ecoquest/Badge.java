package edu.orangecoastcollege.cs273.ecoquest;

/**
 * Created by brendantyleralbert on 11/16/17.
 */

public class Badge {

    private String mName;
    private String mDescription;
    private String mImageName;

    public Badge(String name, String description, String imageName) {
        mName = name;
        mDescription = description;
        mImageName = imageName;
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
