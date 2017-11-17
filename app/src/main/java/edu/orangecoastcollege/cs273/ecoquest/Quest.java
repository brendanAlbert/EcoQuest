package edu.orangecoastcollege.cs273.ecoquest;

/**
 * Created by CaseyTea on 11/16/17.
 */

public class Quest {

    private String mName;
    private String mDescription;
    private String mImageName;

    public Quest(String name, String description, String imageName)
    {
        mName = name;
        mDescription = description;
        mImageName = imageName;
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


}
