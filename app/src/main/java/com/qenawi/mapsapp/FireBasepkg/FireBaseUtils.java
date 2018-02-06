package com.qenawi.mapsapp.FireBasepkg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qenawi.mapsapp.models.Latlungmodel;
import com.qenawi.mapsapp.models.travel_user_model;

import java.util.ArrayList;

/**
 * Created by Ahmed on 1/11/2018.
 */

public class FireBaseUtils
{
    private Context c;
    private intermediteclass_Pres intermediteclass_Pres;
    final FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    private ChildEventListener mListener;
    private ArrayList<travel_user_model> data = new ArrayList<>();
    final DatabaseReference fdbr = fdb.getReference().child("trip");
    private long children_cnt = 0;
    private int cur_cnt = 0;
    FireBaseUtils(Context c, intermediteclass_Pres intermediteclass_Pres) {
        this.c = c;
        this.intermediteclass_Pres = intermediteclass_Pres;
    }
    private void OnDataLoaded() {
        intermediteclass_Pres.onDataLoaded(data);
    }
    void Get_data_from_fire_base() {

        final DatabaseReference fdbr = fdb.getReference().child("trip");
        fdbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                children_cnt = dataSnapshot.getChildrenCount();
                Hook_up_fire_base();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                children_cnt = 0;
            }
        });
    }
    private void Hook_up_fire_base() {
        mListener = fdbr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                travel_user_model d;
                d = dataSnapshot.getValue(travel_user_model.class);
                d.setKey(dataSnapshot.getKey() + "");
                data.add(d);
                cur_cnt += 1;
                if (cur_cnt > children_cnt) {
                    intermediteclass_Pres.onItemUpdate(d);
                } else {
                    OnDataLoaded();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                travel_user_model d;
                d = dataSnapshot.getValue(travel_user_model.class);
                d.setKey(dataSnapshot.getKey());
                intermediteclass_Pres.onItemUpdate(d);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                travel_user_model d;
                d = dataSnapshot.getValue(travel_user_model.class);
                d.setKey(dataSnapshot.getKey() + "");
                intermediteclass_Pres.onItemRemoved(d);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void OnDetach() {
        if (mListener != null && fdbr != null) {
            try {
                fdbr.removeEventListener(mListener);
                Log.v("mvc", "Listner removed Sucessfully");
                intermediteclass_Pres.on_DeAttached(true);
            } catch (Exception e) {
                intermediteclass_Pres.on_DeAttached(false);

                e.printStackTrace();
            }

        }
    }
    void UpdateMylocation(LatLng location)
    {
        Latlungmodel latlungmodel = new Latlungmodel();
        latlungmodel.setLat(location.latitude);
        latlungmodel.setLung(location.longitude);
        fdbr.child("user_ahmed").child("myLocation").setValue(latlungmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                intermediteclass_Pres.onFailure();
            }
        });
    }
}
