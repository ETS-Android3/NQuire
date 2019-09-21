package com.adityagunjal.sdl_project.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowProfileFragment extends Fragment {

    FirebaseUser firebaseUser;

    ModelUser modelUser;
    CircleImageView circleImageView;
    TextView username, bio, answerCount, questionCount, name, emailID, registrationID;
    String userID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_profile, container, false);
        circleImageView = view.findViewById(R.id.show_profile_circle_image);
        username = view.findViewById(R.id.tv_name);
        bio = view.findViewById(R.id.tv_bio);
        questionCount = view.findViewById(R.id.question_count);
        answerCount = view.findViewById(R.id.answer_count);
        name = view.findViewById(R.id.full_name);
        emailID = view.findViewById(R.id.user_email);
        registrationID = view.findViewById(R.id.user_registration_id);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid();

        getUserInfo();

        return view;
    }

    public void getUserInfo(){

        FirebaseDatabase.getInstance().getReference("Users")
                .child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        modelUser = dataSnapshot.getValue(ModelUser.class);
                        setUserInfo();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    public void setUserInfo(){
        if(modelUser.getImagePath().equals("default")){
            circleImageView.setImageResource(R.drawable.ic_profile_icon);
        }else{

        }

        username.setText(modelUser.getUsername());
        emailID.setText(modelUser.getEmail());
        name.setText(modelUser.getFirstName() + " " + modelUser.getLastName());
        bio.setText(modelUser.getBio());
        registrationID.setText(modelUser.getRegistrationID());
        questionCount.setText(Integer.toString(modelUser.getQuestionCount()));
        answerCount.setText(Integer.toString(modelUser.getAnswerCount()));

    }
}
