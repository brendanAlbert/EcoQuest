package edu.orangecoastcollege.cs273.ecoquest;

import android.os.Parcel;
import android.os.Parcelable;

/**
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

    public QuestLocations(String name, String address, String city, String state, String zipCode, double latitude, double longitude) {
        this(-1, name, address, city, state, zipCode, latitude, longitude);
    }

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

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public String getFullAddress()
    {
        return mAddress + "\n" + mCity + ", " + mState + "  " + mZipCode;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

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

    public static final Creator<QuestLocations> CREATOR = new Creator<QuestLocations>() {
        @Override
        public QuestLocations createFromParcel(Parcel in) {
            return new QuestLocations(in);
        }

        @Override
        public QuestLocations[] newArray(int size) {
            return new QuestLocations[size];
        }
    };

}
