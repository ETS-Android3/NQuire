package com.adityagunjal.sdl_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.adityagunjal.sdl_project.ui.login_sinup.ForgotPasswordFragment;
import com.adityagunjal.sdl_project.ui.login_sinup.LoginFragment;
import com.adityagunjal.sdl_project.ui.login_sinup.SinupFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Fragment currentFragment = null;
    LoginFragment loginFragment;
    SinupFragment sinupFragment;
    ForgotPasswordFragment forgotPasswordFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginFragment = new LoginFragment();
        sinupFragment = new SinupFragment();
        forgotPasswordFragment = new ForgotPasswordFragment();

        currentFragment = loginFragment;

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_login_frame_container, currentFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = preferences.getString("username", null);
        String email = preferences.getString("email", null);
        String password = preferences.getString("password", null);

        /*if(username != null && password != null){
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            finish();
        }*/
    }

    public void onSignUpClick(View view){
        currentFragment = sinupFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_login_frame_container, currentFragment).commit();
    }

    public void onLoginClick(View view){
        currentFragment = loginFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_login_frame_container, currentFragment).commit();
    }

    public void onForgotPasswordClick(View view) {
        currentFragment = forgotPasswordFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_login_frame_container, currentFragment).commit();
    }
}
