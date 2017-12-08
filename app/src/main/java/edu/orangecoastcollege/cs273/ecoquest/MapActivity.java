package edu.orangecoastcollege.cs273.ecoquest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

//import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.List;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    public static final int COARSE_LOCATION_REQUEST_CODE = 100;

    private DBHelper db;
    private List<Quest> mAllQuestList;
    private ListView mAllQuestListView;
    private QuestsListAdapter mQuestListAdapter;
    private GoogleMap mMap;
    private DBHelper locationDataBase;

    private List<QuestLocations> mAllLocations;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importQuestsFromCSV("quests.csv");

        mAllQuestList = db.getAllQuests();
        mAllQuestListView = (ListView) findViewById(R.id.mapQuestListView);
        mQuestListAdapter = new QuestsListAdapter(this, R.layout.quest_list_item, mAllQuestList);
        mAllQuestListView.setAdapter(mQuestListAdapter);

        locationDataBase = new DBHelper(this);
        locationDataBase.importLocationsFromCSV("quest_locations.csv");
        mAllLocations = locationDataBase.getAllQuestLocations();


        if (mGoogleApiClient == null)
        {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

        }

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(30 * 1000)
                .setFastestInterval(1 * 1000);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);




    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mGoogleApiClient.connect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
    }

    private void handleNewLocation(Location newLocation)
    {
        mLastLocation = newLocation;
        mMap.clear();

        // Add markers for locations
        LatLng myCoordinate = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(myCoordinate)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.current_location)));    // Add a custom marker in drawable file

        CameraPosition cameraPosition = new CameraPosition.Builder().target(myCoordinate).zoom(15.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

        // Add standard markers for all locations Create list of locations to loop through
        for (QuestLocations questLocations : mAllLocations)
        {
            LatLng coordinate = new LatLng(questLocations.getLatitude(), questLocations.getLongitude());
            mMap.addMarker((new MarkerOptions()
                    .position(coordinate)
                    .title(questLocations.getName()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_quest_icon)));

            //Log.i("For each Loop", "Looping through");
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);

    }

    /*
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    } */

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        // Get the last location from Google services
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // Don't have either COARSE or FINE permissions, so request them:
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    COARSE_LOCATION_REQUEST_CODE);
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        handleNewLocation(mLastLocation);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("ecoQuest", "Connection to Location services failed: " + connectionResult.getErrorMessage());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            mLastLocation = new Location("");
            mLastLocation.setLatitude(0.0);
            mLastLocation.setLongitude(0.0);
        }
        else
        {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        handleNewLocation(mLastLocation);
    }

    public void viewQuestDetails(View view)
    {
        if (view instanceof RelativeLayout)
        {
            RelativeLayout selectedLayout = (RelativeLayout) view;
            Quest quest = (Quest) selectedLayout.getTag();
            Intent questIntent = new Intent(this, QuestDetailsActivity.class);
            questIntent.putExtra("quest", quest);
            Log.i("viewQuestDetails", quest.toString());
            startActivity(questIntent);
        }
    }
}

