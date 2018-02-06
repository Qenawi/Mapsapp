package com.qenawi.mapsapp.FireBasepkg;

import com.qenawi.mapsapp.models.travel_user_model;

import java.util.ArrayList;

/**
 * Created by Ahmed on 1/11/2018.
 */

public interface intermediteclass_Pres
{
    void onDataLoaded(ArrayList<travel_user_model> data);
    void onItemUpdate(travel_user_model data);
    void onItemRemoved(travel_user_model data);
    void on_DeAttached(Boolean bool);
    void onFailure();
}
