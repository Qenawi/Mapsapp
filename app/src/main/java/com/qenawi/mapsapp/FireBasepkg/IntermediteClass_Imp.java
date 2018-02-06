package com.qenawi.mapsapp.FireBasepkg;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.qenawi.mapsapp.models.travel_user_model;

import java.util.ArrayList;

/**
 * Created by Ahmed on 1/11/2018.
 */

public class IntermediteClass_Imp implements intermediteclass_Pres {
    private FireBaseUtils fireBaseUtils;
    private Context c;
    private mainFunctions_Pres mainFunctions_Pres;

    public IntermediteClass_Imp(Context c, mainFunctions_Pres mainFunctions_Pres) {
        this.mainFunctions_Pres = mainFunctions_Pres;
        fireBaseUtils = new FireBaseUtils(c, this);
    }

    public void Load_data() {
        fireBaseUtils.Get_data_from_fire_base();
    }

    public void updateMyLoc(LatLng location) {
        fireBaseUtils.UpdateMylocation(location);
    }

    @Override
    public void onDataLoaded(ArrayList<travel_user_model> data)
    {
        mainFunctions_Pres.OnDataLoaded(data);
    }

    @Override
    public void onItemUpdate(travel_user_model data) {
        mainFunctions_Pres.OnUpdate(data);
    }

    @Override
    public void onItemRemoved(travel_user_model data) {
        mainFunctions_Pres.OnRemove(data);
    }

    @Override
    public void on_DeAttached(Boolean bool) {
        mainFunctions_Pres.OnDetachListner(bool);
    }

    @Override
    public void onFailure()
    {

    }
}
