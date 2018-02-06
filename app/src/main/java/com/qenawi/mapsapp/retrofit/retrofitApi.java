package com.qenawi.mapsapp.retrofit;


import com.qenawi.mapsapp.models.directions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ahmed on 1/7/2018.
 */

public interface retrofitApi
{
    @GET("json")
    Call<directions> getdata
            (
                    @Query("origin") String lat_lng_curnt,
                    @Query("destination") String lat_lng_dest,
                    @Query("optimize") String optimize,
                    @Query("key") String key);
}
