package com.qenawi.mapsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by Ahmed on 1/11/2018.
 */

public class ActivitesModel implements Parcelable
{
  public   ActivitesModel(){}
    protected ActivitesModel(Parcel in)
    {
        Curent = in.readParcelable(MarkerOptions.class.getClassLoader());
        Start = in.readParcelable(MarkerOptions.class.getClassLoader());
        destination = in.readParcelable(MarkerOptions.class.getClassLoader());
        routeString = in.readString();
    }

    public static final Creator<ActivitesModel> CREATOR = new Creator<ActivitesModel>() {
        @Override
        public ActivitesModel createFromParcel(Parcel in) {
            return new ActivitesModel(in);
        }

        @Override
        public ActivitesModel[] newArray(int size) {
            return new ActivitesModel[size];
        }
    };

    public Marker getMyCurentpos() {
        return myCurentpos;
    }

    public void setMyCurentpos(Marker myCurentpos) {
        this.myCurentpos = myCurentpos;
    }

    public Marker getMydestination() {
        return Mydestination;
    }

    public void setMydestination(Marker mydestination) {
        Mydestination = mydestination;
    }

    public Marker getMyStartPoint() {
        return MyStartPoint;
    }

    public void setMyStartPoint(Marker myStartPoint) {
        MyStartPoint = myStartPoint;
    }

    public MarkerOptions getCurent() {
        return Curent;
    }

    public void setCurent(MarkerOptions curent) {
        Curent = curent;
    }

    public MarkerOptions getStart() {
        return Start;
    }

    public void setStart(MarkerOptions start) {
        Start = start;
    }

    public MarkerOptions getDestination() {
        return destination;
    }

    public void setDestination(MarkerOptions destination) {
        this.destination = destination;
    }

    public Polyline getRoute() {
        return route;
    }

    public void setRoute(Polyline route) {
        this.route = route;
    }

    public String getRouteString() {
        return routeString;
    }

    public void setRouteString(String routeString) {
        this.routeString = routeString;
    }

    private Marker myCurentpos, Mydestination, MyStartPoint;
    private MarkerOptions Curent,Start,destination;
    private Polyline route;
    private PolylineOptions routeOptions;
    private String routeString;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(Curent, i);
        parcel.writeParcelable(Start, i);
        parcel.writeParcelable(destination, i);
        parcel.writeString(routeString);
    }

    public PolylineOptions getRouteOptions() {
        return routeOptions;
    }

    public void setRouteOptions(PolylineOptions routeOptions) {
        this.routeOptions = routeOptions;
    }
    //--------------------------

}
