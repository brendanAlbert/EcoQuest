package edu.orangecoastcollege.cs273.ecoquest;

/**
 * Created by CaseyTea on 11/28/17.
 *
 * Titles class is an object of Titles a user can earn through completion
 * of Quest and Badges to show off their achievements.
 *
 * Titles have not yet been implemented into the final project to be turned in - Casey
 *
 */

public class Title
{

    private long mId;
    private String mName;

    /**
     * Constructor of the class Title. Sets the Id and the name
     * of the Title with the parameters
     * @param id the new Id of the Title.
     * @param name the new name of the Title.
     */
    public Title(long id, String name)
    {
        mId = id;
        mName = name;
    }

    /**
     * Method returns the Name of the Title.
     * @return The name of the title as a string.
     */
    public String getName() { return mName; }

    /**
     * Method return the Id of the Title.
     * @return Return the Title ID as a long.
     */
    public long getId() { return mId; }

    /**
     * Set the name of the title.
     * @param name the new name of the title.
     */
    public void setName(String name) { mName = name; }

    /**
     * The id of the title stored as a long.
     * @param id the new id of the title.
     */
    public void setId(long id) { mId = id; }



}
