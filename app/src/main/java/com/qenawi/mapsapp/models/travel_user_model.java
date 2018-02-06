package com.qenawi.mapsapp.models;

/**
 * Created by Ahmed on 1/11/2018.
 */

public class travel_user_model
{


    /**
     * endLocation : {"lat":223.22335,"lung":778.333}
     * myLocation : {"lat":22.2598,"lung":22.48963}
     * route : asdasdasdasdas5d1a6s5d1
     * startLocation : {"lat":77.223785,"lung":33225.25666}
     */

    private EndLocationBean endLocation;
    private MyLocationBean myLocation;
    private String route;

    public String getKey()
    {
        if (key==null||key.equals(""))key="user";
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key="user";
    private StartLocationBean startLocation;

    public travel_user_model(){}
    public EndLocationBean getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocationBean endLocation) {
        this.endLocation = endLocation;
    }

    public MyLocationBean getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(MyLocationBean myLocation) {
        this.myLocation = myLocation;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public StartLocationBean getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocationBean startLocation) {
        this.startLocation = startLocation;
    }

    public static class EndLocationBean
    {
        /**
         * lat : 223.22335
         * lung : 778.333
         */
        private double lat;
        private double lung;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLung() {
            return lung;
        }

        public void setLung(double lung) {
            this.lung = lung;
        }
    }

    public static class MyLocationBean {
        /**
         * lat : 22.2598
         * lung : 22.48963
         */

        private double lat;
        private double lung;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLung() {
            return lung;
        }

        public void setLung(double lung) {
            this.lung = lung;
        }
    }

    public static class StartLocationBean
    {
        /**
         * lat : 77.223785
         * lung : 33225.25666
         */

        private double lat;
        private double lung;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLung() {
            return lung;
        }

        public void setLung(double lung) {
            this.lung = lung;
        }
    }
}
