package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * QuestLocation Model class of the locations each some Quests
 * can be completed at.
 * QuestLocations implements Parcelable because there are many places in the app
 * where a QuestLocations object is passed via Intent between Activities.
 *
 *
 * Created by CaseyTea on 12/6/17.
 */

public class QuestLocations implements Parcelable {

    private long mId;
    private String mName;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZipCode;
    private double mLatitude;
    private double mLongitude;

    /**
     * QuestLocation is a parameterize constructor that accepts all member variables as arguments.
     *
     * @param id
     * @param name
     * @param address
     * @param city
     * @param state
     * @param zipCode
     * @param latitude
     * @param longitude
     */
    public QuestLocations(long id, String name, String address, String city, String state, String zipCode, double latitude, double longitude) {
        mId = id;
        mName = name;
        mAddress = address;
        mCity = city;
        mState = state;
        mZipCode = zipCode;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    /**
     * Another parameterize constructor which does not include id as an argument.
     *
     * @param name
     * @param address
     * @param city
     * @param state
     * @param zipCode
     * @param latitude
     * @param longitude
     */
    public QuestLocations(String name, String address, String city, String state, String zipCode, double latitude, double longitude) {
        this(-1, name, address, city, state, zipCode, latitude, longitude);
    }

    /**
     * This protected class is used by Parcelable interface.
     * All of the member variables are read from parcel arguments.
     *
     * @param in
     */
    protected QuestLocations(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mAddress = in.readString();
        mCity = in.readString();
        mState = in.readString();
        mZipCode = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    /**
     * getId method returns the Id of the QuestLocation as a long.
     * @return
     */
    public long getId() {
        return mId;
    }

    /**
     * setId method sets the QuestLocation id with the provided long data typed
     * argument
     * @param id
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * getName method returns the name of the QuestLocation as a string.
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * setName method sets the QuestLocation name with the provided string argument.
     * @param name
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * getAddress method returns QuestLocation address as a string.
     * @return
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * setAddress method sets the QuestLocation address with the provided string argument.
     * @param address
     */
    public void setAddress(String address) {
        mAddress = address;
    }

    /**
     * getCity method returns the QuestLocation city as a string.
     * @return
     */
    public String getCity() {
        return mCity;
    }

    /**
     * setCity method sets the QuestLocation city with the provided string argument.
     * @param city
     */
    public void setCity(String city) {
        mCity = city;
    }

    /**
     * getState method returns the QuestLocation state as a string.
     * @return
     */
    public String getState() {
        return mState;
    }

    /**
     * setState method sets the QuestLocation with the provided string argument.
     * @param state
     */
    public void setState(String state) {
        mState = state;
    }

    /**
     * getZipCode method returns the QuestLocation zip code as a string.
     * @return
     */
    public String getZipCode() {
        return mZipCode;
    }

    /**
     * setZipCode method sets the QuestLocation zip code with the provided string argument
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

    /**
     * getLatitude returns the QuestLocation latitude as a double.
     * This method is used in the MapActivity to set markers on the map.
     * @return
     */
    public double getLatitude() {
        return mLatitude;
    }

    /**
     * setLatitude method sets the QuestLocation latitude with the provided double argument
     * @param latitude
     */
    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    /**
     * getLongitude method returns the QuestLocation longitude as a double.
     * This method is used in the MapActivity to set markers on the map.
     * @return
     */
    public double getLongitude() {
        return mLongitude;
    }

    /**
     * setLongitude method sets the QuestLocation longitude with the provided double argument
     * @param longitude
     */
    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    /**
     * getFullAddress method concatenates member variables to display the full
     * address of a QuestLocation.
     * @return
     */
    public String getFullAddress()
    {
        return mAddress + "\n" + mCity + ", " + mState + "  " + mZipCode;
    }

    /**
     * getFormattedLatLng method changes the doubles with north, south, east and west
     * substitutions to make more read able
     *
     * This method has not been used.
     * @return
     */
    public String getFormattedLatLng()
    {
        String latLng = String.valueOf(Math.abs(mLatitude));
        latLng += ((mLatitude < 0.0) ? " S  " : " N  ");
        latLng += String.valueOf(Math.abs(mLongitude));
        latLng += ((mLongitude < 0.0) ? " W" : "E");
        return latLng;
    }

    @Override
    public String toString() {
        return "CaffeineLocation{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", Address='" + mAddress + '\'' +
                ", City='" + mCity + '\'' +
                ", State='" + mState + '\'' +
                ", Zip Code='" + mZipCode + '\'' +
                ", Latitude=" + mLatitude +
                ", Longitude=" + mLongitude +
                '}';
    }

    /**
     * describeContents must be implemented when using Parcelable.
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Creator<User> is an anonymous inner class that must be implemented
     * when using Parcelable.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mName);
        parcel.writeString(mAddress);
        parcel.writeString(mCity);
        parcel.writeString(mState);
        parcel.writeString(mZipCode);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
    }

    /**
     * createFromParcel must be implemented when using the Creator inner class.
     * @param parcel
     * @return
     */
    public static final Creator<QuestLocations> CREATOR = new Creator<QuestLocations>() {
        @Override
        public QuestLocations createFromParcel(Parcel in) {
            return new QuestLocations(in);
        }

        /**
         * newArray must be implemented when using the Creator inner class.
         * @param size
         * @return
         */
        @Override
        public QuestLocations[] newArray(int size) {
            return new QuestLocations[size];
        }
    };

}
