package com.qenawi.mapsapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahmed on 1/13/2018.
 */

public class retrofitBaseClass
{
  public static Retrofit  getBase()
  {
      return new Retrofit.Builder()
              .baseUrl("https://maps.googleapis.com/maps/api/directions/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();
  }
  }

