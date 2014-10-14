package com.avoidthewolf;

import android.app.Application;

import com.firebase.client.Firebase;

public class AWApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
