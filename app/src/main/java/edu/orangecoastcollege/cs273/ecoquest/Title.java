package edu.orangecoastcollege.cs273.ecoquest;

/**
 * Created by CaseyTea on 11/28/17.
 *
 * I totally forgot about implementing titles, I have created this simple class
 * based on the data table we turned in.
 *
 */

public class Title
{

    private long mId;
    private String mName;

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

    public void setName(String name) { mName = name; }

    public void setId(long id) { mId = id; }



}
