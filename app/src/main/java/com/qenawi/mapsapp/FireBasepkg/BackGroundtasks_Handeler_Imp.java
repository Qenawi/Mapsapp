package com.qenawi.mapsapp.FireBasepkg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.qenawi.mapsapp.R;
import com.qenawi.mapsapp.models.ActivitesModel;
import com.qenawi.mapsapp.models.travel_user_model;
import com.qenawi.mapsapp.utils.PolyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Ahmed on 1/13/2018.
 */

public class BackGroundtasks_Handeler_Imp
{
   private Context c;
   private BackGroundtasks_Handeler_Pres backGroundtasks_handeler_pres;
   private Map_toData myAsync1;
    public BackGroundtasks_Handeler_Imp(Context c,BackGroundtasks_Handeler_Pres backGroundtasks_handeler_pres)
    {
        this.c=c;
        this.backGroundtasks_handeler_pres=backGroundtasks_handeler_pres;

    }

    public void get_MapData(ArrayList<travel_user_model>Data)
    {
        if (myAsync1!=null)
        {
            myAsync1.cancel(true);
        }
        myAsync1=new Map_toData();
        myAsync1.execute(Data);

    }
    public  BitmapDescriptor getMarkerIcon(String titlest, String Descst, String speedst)
    {
      return getMarkerBitmapFromView (titlest,Descst,speedst);
    }
    public  BitmapDescriptor getMarkerIconBasic(final float Color)
    {
        return getBasicBitmap (Color);
    }
    @SuppressLint("StaticFieldLeak")
    private  class Map_toData extends AsyncTask<ArrayList<travel_user_model>, Integer, HashMap<String, ActivitesModel>>
    {
        @SafeVarargs
        @Override
        protected final HashMap<String, ActivitesModel> doInBackground(ArrayList<travel_user_model>... arrayLists) {
            ArrayList<travel_user_model> mapData = arrayLists[0];
            HashMap<String, ActivitesModel> res = new HashMap<>();
            Random random = new Random();
            for (int i = 0; i < mapData.size(); i++) {
                ActivitesModel d = new ActivitesModel();
                //-------------------------
                MarkerOptions curntloc = new MarkerOptions()
                        .position(new LatLng(mapData.get(i).getMyLocation().getLat(), mapData.get(i).getMyLocation().getLung()))
                        .icon(getMarkerBitmapFromView(mapData.get(i).getKey(), "......", String.valueOf(random.nextInt(99) + 1)));
                MarkerOptions endloc = new MarkerOptions()
                        .position(new LatLng(mapData.get(i).getEndLocation().getLat(), mapData.get(i).getEndLocation().getLung()))
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                MarkerOptions startloc = new MarkerOptions()
                        .position(new LatLng(mapData.get(i).getStartLocation().getLat(), mapData.get(i).getStartLocation().getLung()))
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                d.setRouteString(mapData.get(i).getRoute());
                startloc.visible(false);
                endloc.visible(false);
                d.setCurent(curntloc);
                d.setDestination(endloc);
                d.setStart(startloc);
                d.setRouteString(mapData.get(i).getRoute());
                PolylineOptions polylineOptions = new PolylineOptions();
                String[] arr = mapData.get(i).getRoute().split(" ");
                for (int ii = 0; ii < arr.length; ii++) {
                    polylineOptions.addAll(PolyUtil.decode(arr[ii]));
                }
                polylineOptions.color(Color.BLACK);
                polylineOptions.visible(false);
                d.setRouteOptions(polylineOptions);
                res.put(mapData.get(i).getKey(), d);
            }
            return res;
        }
        @Override
        protected void onPostExecute(HashMap<String, ActivitesModel> stringActivitesModelHashMap)
        {
            if (!isCancelled())
            backGroundtasks_handeler_pres.On_HashMapReady(stringActivitesModelHashMap);
            super.onPostExecute(stringActivitesModelHashMap);
        }
    }
    private BitmapDescriptor getMarkerBitmapFromView(String titlest, String Descst, String speedst)
    {

        View customMarkerView = ((LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_contents,null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.iconde);
        TextView title = customMarkerView.findViewById(R.id.title);
        TextView speed = customMarkerView.findViewById(R.id.speed);
        TextView snippet = customMarkerView.findViewById(R.id.snippet);
        //--------markerImageView.setImageResource(resId);
        title.setText(titlest);
        snippet.setText(Descst);
        speed.setText(speedst);
        speed.append(" k/m");
        //------------------------------------------------
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return   BitmapDescriptorFactory.fromBitmap(returnedBitmap);
    }
    private BitmapDescriptor getBasicBitmap(final float color)
    {
return BitmapDescriptorFactory
        .defaultMarker(color);
    }
}
