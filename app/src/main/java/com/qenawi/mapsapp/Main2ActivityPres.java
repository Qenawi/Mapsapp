package com.qenawi.mapsapp;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.qenawi.mapsapp.FireBasepkg.BackGroundtasks_Handeler_Imp;
import com.qenawi.mapsapp.FireBasepkg.BackGroundtasks_Handeler_Pres;
import com.qenawi.mapsapp.FireBasepkg.IntermediteClass_Imp;
import com.qenawi.mapsapp.FireBasepkg.mainFunctions_Pres;
import com.qenawi.mapsapp.models.ActivitesModel;
import com.qenawi.mapsapp.models.travel_user_model;

import java.util.ArrayList;
import java.util.HashMap;

import static com.qenawi.mapsapp.R.id.map;


public class Main2ActivityPres extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, mainFunctions_Pres, BackGroundtasks_Handeler_Pres {
    private static final float DEFAULT_ZOOM = 15;
    private HashMap<String, ActivitesModel> GroupActivites = new HashMap<>();
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private GoogleMap mMap;
    private Location mLastKnownLocation;
    private boolean mLocationPermissionGranted;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private String TAG = "Main2ActivityPres";
    private Marker myLocation;
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    //----mvc-------
    private IntermediteClass_Imp intermediteClassImp;
    private ArrayList<travel_user_model> mapData;
    BackGroundtasks_Handeler_Imp backGroundtasksHandelerImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        setContentView(R.layout.activity_main2);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        intermediteClassImp = new IntermediteClass_Imp(this, this);
        backGroundtasksHandelerImp = new BackGroundtasks_Handeler_Imp(this, this);
        Syncmap();
    }

    private void Syncmap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
    }
    //------------------------------------------------------------------------------------
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setFastestInterval(5000);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        intermediteClassImp.updateMyLoc(new LatLng(location.getLatitude() + 0.00090, location.getLongitude() + 0.00090));
        getDeviceLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker)
            {

                if (marker.getTag() != null && marker.getTag().equals("user_ahmed"))
                {
                    if (GroupActivites.get("user_ahmed").getRoute() != null)
                    {
                        Log.v("exo", "show");
                        GroupActivites.get("user_ahmed").getRoute().setVisible(!GroupActivites.get("user_ahmed").getRoute().isVisible());
                        GroupActivites.get("user_ahmed").getMydestination().setVisible(GroupActivites.get("user_ahmed").getRoute().isVisible());
                        GroupActivites.get("user_ahmed").getMyStartPoint().setVisible(GroupActivites.get("user_ahmed").getRoute().isVisible());

                    } else {
                        Log.v("exo", "create");
                        GroupActivites.get("user_ahmed").setRoute(mMap.addPolyline(GroupActivites.get("user_ahmed").getRouteOptions()));
                        GroupActivites.get("user_ahmed").getRoute().setVisible(true);
                        GroupActivites.get("user_ahmed").setMyStartPoint(mMap.addMarker(GroupActivites.get("user_ahmed").getStart()));
                        GroupActivites.get("user_ahmed").setMydestination(mMap.addMarker(GroupActivites.get("user_ahmed").getDestination()));

                               /*
                            mMap.addMarker(new MarkerOptions()
                                    .title("your location")
                                    .position(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()))
                                    .icon(backGroundtasksHandelerImp.getMarkerIconBasic(BitmapDescriptorFactory.HUE_GREEN)));
                                  */
                    }

                } else {
                    if (GroupActivites.isEmpty()) {
                        intermediteClassImp.Load_data();
                    }
                }
                return false;
            }
        });
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(Main2ActivityPres.this, "gpsBtn is clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // Prompt the user for permission
        //      getLocationPermission();
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                hot_fix();
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void hot_fix() {
        Toast.makeText(this, "hot fix", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void getLocationPermission()
    {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            mLocationPermissionGranted = true;
            updateLocationUI();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLocationRequest != null && mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            if (mLastKnownLocation == null) {

                                Toast.makeText(Main2ActivityPres.this, "we cant get location", Toast.LENGTH_SHORT).show();
                                if (mGoogleApiClient != null && !mGoogleApiClient.isConnected()) {
                                    hot_fix();
                                }
                                return;
                            }
                            Update_my_marker();
                            //      mMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                            //            (new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.v(TAG, "Current location is null. Using defaults.");
                            Log.v(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v(TAG, "Exception: %s" + e.getMessage());
                    }
                });
            } else {
                Toast.makeText(Main2ActivityPres.this, "faild to pick up location", Toast.LENGTH_SHORT).show();
            }
        } catch (SecurityException e) {
            Log.v("Exception: %s", e.getMessage());
        }
    }
    private void Update_my_marker()
    {
                                /*
                            mMap.addMarker(new MarkerOptions()
                                    .title("your location")
                                    .position(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()))
                                    .icon(backGroundtasksHandelerImp.getMarkerIconBasic(BitmapDescriptorFactory.HUE_GREEN)));
                                  */
        if (myLocation != null) {
            myLocation.remove();
        }
        MarkerOptions ops = new MarkerOptions()
                .position(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()))
                .icon(backGroundtasksHandelerImp.getMarkerIcon("my Location", "no data so far -_- ", "0"));
        myLocation = mMap.addMarker(ops);
    }
    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    updateLocationUI();
                } else {
                    getLocationPermission();
                }
            }
        }
    }

    @Override
    public void OnDataLoaded(ArrayList<travel_user_model> map) {
        Log.v("mvc", "Data Loaded with pleasure" + map.size() + " " + map.get(0).getRoute() + " " + map.get(0).getKey() + " " + map.get(0).getMyLocation().getLat() + " ");
        mapData = map;
        backGroundtasksHandelerImp.get_MapData(mapData);
    }

    @Override
    public void OnSucess() {
    }

    @Override
    public void OnDetachListner(Boolean bool) {
        Log.v("mvc", "Listner Staus" + bool.toString());
    }

    @Override
    public void OnUpdate(travel_user_model map) {
        Log.v("mvc", "Listner Staus  Update");
        if (GroupActivites.containsKey(map.getKey())) {
            if (GroupActivites.get(map.getKey()).getMyCurentpos() != null) {
                Log.v("mvc", "Listner Staus  Update0");
                GroupActivites.get(map.getKey()).getCurent().position(new LatLng(map.getMyLocation().getLat(), map.getMyLocation().getLung()));
                GroupActivites.get(map.getKey()).getMyCurentpos().remove();
                GroupActivites.get(map.getKey()).setMyCurentpos(mMap.addMarker(GroupActivites.get(map.getKey()).getCurent()));
                GroupActivites.get(map.getKey()).getMyCurentpos().setTag(GroupActivites.get(map.getKey()));
            } else {
                Log.v("mvc", "Listner Staus  Update 1");
                GroupActivites.get(map.getKey()).getCurent().position(new LatLng(map.getMyLocation().getLat(), map.getMyLocation().getLung()));
                GroupActivites.get(map.getKey()).setMyCurentpos(mMap.addMarker(GroupActivites.get(map.getKey()).getCurent()));
                GroupActivites.get(map.getKey()).getMyCurentpos().setTag(GroupActivites.get(map.getKey()));
            }

        } else {

        }
    }

    @Override
    public void OnRemove(travel_user_model map) {

    }

    @Override
    public void onFailure() {
    }

    @Override
    public void On_HashMapReady(HashMap<String, ActivitesModel> stringActivitesModelHashMap) {
        GroupActivites = stringActivitesModelHashMap;
        GroupActivites.get("user_ahmed").setMyCurentpos(
                mMap.addMarker(GroupActivites.get("user_ahmed").getCurent()));
        GroupActivites.get("user_ahmed").getMyCurentpos().setTag("user_ahmed");
    }

}
