package com.adityagunjal.sdl_project;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ApplicationStartup extends Application {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void onCreate() {
        super.onCreate();

        firebaseAuth  = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser == null){
            Intent i = new Intent(ApplicationStartup.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}
