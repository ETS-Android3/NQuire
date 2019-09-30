package com.adityagunjal.sdl_project.ui.login_sinup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adityagunjal.sdl_project.MainActivity;
import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.SplashActivity;
import com.adityagunjal.sdl_project.models.ModelUsernameEmail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment implements Button.OnClickListener{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextInputEditText usename, password;
    Button login;
    ProgressBar progressBar;

    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usename = view.findViewById(R.id.login_username);
        password = view.findViewById(R.id.login_password);
        login = view.findViewById(R.id.login_button);
        progressBar = view.findViewById(R.id.login_progress_bar);

        login.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onStop() {
        super.onStop();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        loginUser();
    }

    public void loginUser(){
        final String uname = usename.getText().toString().trim();
        final String pword = password.getText().toString().trim();

        if(uname.isEmpty() || pword.isEmpty()){
            Toast.makeText(getActivity(), "Invalid Login Credentials !", Toast.LENGTH_LONG).show();
            return;
        }else{
            progressBar.setVisibility(View.VISIBLE);
            firebaseDatabase = FirebaseDatabase.getInstance();
            FirebaseDatabase.getInstance().getReference("Usernames")
                    .child(uname)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try{
                                email = dataSnapshot.getValue(ModelUsernameEmail.class).getEmail();
                                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pword)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if(task.isSuccessful()){

                                                    /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                                    SharedPreferences.Editor editor = preferences.edit();
                                                    editor.putString("username", uname);
                                                    editor.putString("email" ,email);
                                                    editor.putString("password", pword);
                                                    editor.apply();
                                                    editor.commit();*/

                                                    Intent i = new Intent(getActivity(), SplashActivity.class);
                                                    startActivity(i);
                                                    getActivity().finish();
                                                }else{
                                                    Log.i("Login","Invalid Password");
                                                    Toast.makeText(getActivity(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                            }
                            catch (Exception e){
                                Log.i("Login","Invalid Username");
                                Toast.makeText(getActivity(), "Invalid Username", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        }
    }

}
