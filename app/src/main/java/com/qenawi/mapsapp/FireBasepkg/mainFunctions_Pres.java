package com.qenawi.mapsapp.FireBasepkg;

import com.qenawi.mapsapp.models.travel_user_model;

import java.util.ArrayList;

/**
 * Created by Ahmed on 1/11/2018.
 */

public interface mainFunctions_Pres
{
    //Z
    void OnSucess();
    void OnDetachListner(Boolean bool);
    void OnDataLoaded(ArrayList<travel_user_model> data);
    void OnUpdate(travel_user_model data);
    void OnRemove(travel_user_model data);
    void onFailure();

}
