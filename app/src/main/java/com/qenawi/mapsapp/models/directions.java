package com.qenawi.mapsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Ahmed on 1/7/2018.
 */

public class directions implements Parcelable {


    private String status;
    private String error_message;
    private List<GeocodedWaypointsBean> geocoded_waypoints;
    private List<RoutesBean> routes;

    protected directions(Parcel in)
    {
        status = in.readString();
        if (!status.equals("OK"))
        error_message=in.readString();
        geocoded_waypoints = in.createTypedArrayList(GeocodedWaypointsBean.CREATOR);
        routes = in.createTypedArrayList(RoutesBean.CREATOR);
    }

    public static final Creator<directions> CREATOR = new Creator<directions>() {
        @Override
        public directions createFromParcel(Parcel in) {
            return new directions(in);
        }

        @Override
        public directions[] newArray(int size) {
            return new directions[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeocodedWaypointsBean> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public void setGeocoded_waypoints(List<GeocodedWaypointsBean> geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public List<RoutesBean> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesBean> routes) {
        this.routes = routes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(status);
        if (!status.equals("OK"))
            parcel.writeString(error_message);
        parcel.writeTypedList(geocoded_waypoints);
        parcel.writeTypedList(routes);
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public static class GeocodedWaypointsBean implements Parcelable {
        /**
         * geocoder_status : OK
         * place_id : ChIJZRy0GMZAWBQRq1mS10hus6k
         * types : ["route"]
         */

        private String geocoder_status;
        private String place_id;
        private List<String> types;

        protected GeocodedWaypointsBean(Parcel in) {
            geocoder_status = in.readString();
            place_id = in.readString();
            types = in.createStringArrayList();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(geocoder_status);
            dest.writeString(place_id);
            dest.writeStringList(types);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<GeocodedWaypointsBean> CREATOR = new Creator<GeocodedWaypointsBean>() {
            @Override
            public GeocodedWaypointsBean createFromParcel(Parcel in) {
                return new GeocodedWaypointsBean(in);
            }

            @Override
            public GeocodedWaypointsBean[] newArray(int size) {
                return new GeocodedWaypointsBean[size];
            }
        };

        public String getGeocoder_status() {
            return geocoder_status;
        }

        public void setGeocoder_status(String geocoder_status) {
            this.geocoder_status = geocoder_status;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }
    }

    public static class RoutesBean implements Parcelable {
        /**
         * bounds : {"northeast":{"lat":30.0448313,"lng":31.2361744},"southwest":{"lat":30.0440281,"lng":31.2344945}}
         * copyrights : Map data ©2018 Google, ORION-ME
         * legs : [{"distance":{"text":"0.3 km","value":286},"duration":{"text":"2 mins","value":100},"end_address":"92 El Tahrir, Ad Dawawin, Abdeen, Cairo Governorate, Egypt","end_location":{"lat":30.0444114,"lng":31.2344945},"start_address":"El-Tahrir Square, Qasr Ad Dobarah, Qasr an Nile, Cairo Governorate, Egypt","start_location":{"lat":30.044044,"lng":31.2355882},"steps":[{"distance":{"text":"0.2 km","value":210},"duration":{"text":"1 min","value":88},"end_location":{"lat":30.0445393,"lng":31.2352658},"html_instructions":"Head <b>east<\/b> toward <b>Al Kasr Al Aini<\/b>/<b>Cairo - Al Wosta<\/b>","polyline":{"points":"g~jvDmus}D@G?O?OCOEOGIGKCACCAACCCACAGAGCM?K?K@KDIDIHGHGLENCN?N@NBNDLHLNN@?@@B@@?@@@@B?@?"},"start_location":{"lat":30.044044,"lng":31.2355882},"travel_mode":"DRIVING"},{"distance":{"text":"76 m","value":76},"duration":{"text":"1 min","value":12},"end_location":{"lat":30.0444114,"lng":31.2344945},"html_instructions":"Exit the roundabout onto <b>El Tahrir<\/b> heading to <b>Egyptian Musueum<\/b><div style=\"font-size:0.9em\">Destination will be on the right<\/div>","polyline":{"points":"kakvDmss}DP|AF|@"},"start_location":{"lat":30.0445393,"lng":31.2352658},"travel_mode":"DRIVING"}],"traffic_speed_entry":[],"via_waypoint":[]}]
         * overview_polyline : {"points":"g~jvDmus}D@WC_@MYKMMKa@GW@UJQRM\\C^D^NZPNHDD@R|AF|@"}
         * summary : El Tahrir
         * warnings : []
         * waypoint_order : []
         */

        private BoundsBean bounds;
        private String copyrights;
        private OverviewPolylineBean overview_polyline;
        private String summary;
        private List<LegsBean> legs;
        private List<?> warnings;
        private List<?> waypoint_order;

        protected RoutesBean(Parcel in) {
            copyrights = in.readString();
            summary = in.readString();
        }

        public static final Creator<RoutesBean> CREATOR = new Creator<RoutesBean>() {
            @Override
            public RoutesBean createFromParcel(Parcel in) {
                return new RoutesBean(in);
            }

            @Override
            public RoutesBean[] newArray(int size) {
                return new RoutesBean[size];
            }
        };

        public BoundsBean getBounds() {
            return bounds;
        }

        public void setBounds(BoundsBean bounds) {
            this.bounds = bounds;
        }

        public String getCopyrights() {
            return copyrights;
        }

        public void setCopyrights(String copyrights) {
            this.copyrights = copyrights;
        }

        public OverviewPolylineBean getOverview_polyline() {
            return overview_polyline;
        }

        public void setOverview_polyline(OverviewPolylineBean overview_polyline) {
            this.overview_polyline = overview_polyline;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<LegsBean> getLegs() {
            return legs;
        }

        public void setLegs(List<LegsBean> legs) {
            this.legs = legs;
        }

        public List<?> getWarnings() {
            return warnings;
        }

        public void setWarnings(List<?> warnings) {
            this.warnings = warnings;
        }

        public List<?> getWaypoint_order() {
            return waypoint_order;
        }

        public void setWaypoint_order(List<?> waypoint_order) {
            this.waypoint_order = waypoint_order;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(copyrights);
            parcel.writeString(summary);
        }

        public static class BoundsBean {
            /**
             * northeast : {"lat":30.0448313,"lng":31.2361744}
             * southwest : {"lat":30.0440281,"lng":31.2344945}
             */

            private NortheastBean northeast;
            private SouthwestBean southwest;

            public NortheastBean getNortheast() {
                return northeast;
            }

            public void setNortheast(NortheastBean northeast) {
                this.northeast = northeast;
            }

            public SouthwestBean getSouthwest() {
                return southwest;
            }

            public void setSouthwest(SouthwestBean southwest) {
                this.southwest = southwest;
            }

            public static class NortheastBean {
                /**
                 * lat : 30.0448313
                 * lng : 31.2361744
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class SouthwestBean {
                /**
                 * lat : 30.0440281
                 * lng : 31.2344945
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }

        public static class OverviewPolylineBean {
            /**
             * points : g~jvDmus}D@WC_@MYKMMKa@GW@UJQRM\C^D^NZPNHDD@R|AF|@
             */

            private String points;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }

        public static class LegsBean {

            private DistanceBean distance;
            private DurationBean duration;
            private String end_address;
            private EndLocationBean end_location;
            private String start_address;
            private StartLocationBean start_location;
            private List<StepsBean> steps;
            private List<?> traffic_speed_entry;
            private List<?> via_waypoint;

            public DistanceBean getDistance() {
                return distance;
            }

            public void setDistance(DistanceBean distance) {
                this.distance = distance;
            }

            public DurationBean getDuration() {
                return duration;
            }

            public void setDuration(DurationBean duration) {
                this.duration = duration;
            }

            public String getEnd_address() {
                return end_address;
            }

            public void setEnd_address(String end_address) {
                this.end_address = end_address;
            }

            public EndLocationBean getEnd_location() {
                return end_location;
            }

            public void setEnd_location(EndLocationBean end_location) {
                this.end_location = end_location;
            }

            public String getStart_address() {
                return start_address;
            }

            public void setStart_address(String start_address) {
                this.start_address = start_address;
            }

            public StartLocationBean getStart_location() {
                return start_location;
            }

            public void setStart_location(StartLocationBean start_location) {
                this.start_location = start_location;
            }

            public List<StepsBean> getSteps() {
                return steps;
            }

            public void setSteps(List<StepsBean> steps) {
                this.steps = steps;
            }

            public List<?> getTraffic_speed_entry() {
                return traffic_speed_entry;
            }

            public void setTraffic_speed_entry(List<?> traffic_speed_entry) {
                this.traffic_speed_entry = traffic_speed_entry;
            }

            public List<?> getVia_waypoint() {
                return via_waypoint;
            }

            public void setVia_waypoint(List<?> via_waypoint) {
                this.via_waypoint = via_waypoint;
            }

            public static class DistanceBean {
                /**
                 * text : 0.3 km
                 * value : 286
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public static class DurationBean {
                /**
                 * text : 2 mins
                 * value : 100
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public static class EndLocationBean {
                /**
                 * lat : 30.0444114
                 * lng : 31.2344945
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class StartLocationBean {
                /**
                 * lat : 30.044044
                 * lng : 31.2355882
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class StepsBean {
                /**
                 * distance : {"text":"0.2 km","value":210}
                 * duration : {"text":"1 min","value":88}
                 * end_location : {"lat":30.0445393,"lng":31.2352658}
                 * html_instructions : Head <b>east</b> toward <b>Al Kasr Al Aini</b>/<b>Cairo - Al Wosta</b>
                 * polyline : {"points":"g~jvDmus}D@G?O?OCOEOGIGKCACCAACCCACAGAGCM?K?K@KDIDIHGHGLENCN?N@NBNDLHLNN@?@@B@@?@@@@B?@?"}
                 * start_location : {"lat":30.044044,"lng":31.2355882}
                 * travel_mode : DRIVING
                 */

                private DistanceBeanX distance;
                private DurationBeanX duration;
                private EndLocationBeanX end_location;
                private String html_instructions;
                private PolylineBean polyline;
                private StartLocationBeanX start_location;
                private String travel_mode;

                public DistanceBeanX getDistance() {
                    return distance;
                }

                public void setDistance(DistanceBeanX distance) {
                    this.distance = distance;
                }

                public DurationBeanX getDuration() {
                    return duration;
                }

                public void setDuration(DurationBeanX duration) {
                    this.duration = duration;
                }

                public EndLocationBeanX getEnd_location() {
                    return end_location;
                }

                public void setEnd_location(EndLocationBeanX end_location) {
                    this.end_location = end_location;
                }

                public String getHtml_instructions() {
                    return html_instructions;
                }

                public void setHtml_instructions(String html_instructions) {
                    this.html_instructions = html_instructions;
                }

                public PolylineBean getPolyline() {
                    return polyline;
                }

                public void setPolyline(PolylineBean polyline) {
                    this.polyline = polyline;
                }

                public StartLocationBeanX getStart_location() {
                    return start_location;
                }

                public void setStart_location(StartLocationBeanX start_location) {
                    this.start_location = start_location;
                }

                public String getTravel_mode() {
                    return travel_mode;
                }

                public void setTravel_mode(String travel_mode) {
                    this.travel_mode = travel_mode;
                }

                public static class DistanceBeanX {
                    /**
                     * text : 0.2 km
                     * value : 210
                     */

                    private String text;
                    private int value;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public int getValue() {
                        return value;
                    }

                    public void setValue(int value) {
                        this.value = value;
                    }
                }

                public static class DurationBeanX {
                    /**
                     * text : 1 min
                     * value : 88
                     */

                    private String text;
                    private int value;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public int getValue() {
                        return value;
                    }

                    public void setValue(int value) {
                        this.value = value;
                    }
                }

                public static class EndLocationBeanX {
                    /**
                     * lat : 30.0445393
                     * lng : 31.2352658
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class PolylineBean {
                    /**
                     * points : g~jvDmus}D@G?O?OCOEOGIGKCACCAACCCACAGAGCM?K?K@KDIDIHGHGLENCN?N@NBNDLHLNN@?@@B@@?@@@@B?@?
                     */

                    private String points;

                    public String getPoints() {
                        return points;
                    }

                    public void setPoints(String points) {
                        this.points = points;
                    }
                }

                public static class StartLocationBeanX {
                    /**
                     * lat : 30.044044
                     * lng : 31.2355882
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }
    }
}
