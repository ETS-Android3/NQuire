package com.adityagunjal.sdl_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.adapters.AdapterFeed;
import com.adityagunjal.sdl_project.models.ModelAnswer;
import com.adityagunjal.sdl_project.models.ModelFeed;
import com.adityagunjal.sdl_project.models.ModelQuestion;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    AdapterFeed adapterFeed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapterFeed = new AdapterFeed(getActivity(), modelFeedArrayList);
        recyclerView.setAdapter(adapterFeed);

        TextView textView = new TextView(getActivity());



        populateRecyclerView();

        return  view;
    }

    public void populateRecyclerView() {
        /*FirebaseDatabase.getInstance().getReference("Answers")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().orderByChild("timestamp").limitToFirst(1)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final ModelAnswer modelAnswer = dataSnapshot.getValue(ModelAnswer.class);
                                            FirebaseDatabase.getInstance().getReference("Users")
                                                    .child(modelAnswer.getUserID())
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            final ModelUser modelUser = dataSnapshot.getValue(ModelUser.class);
                                                            FirebaseDatabase.getInstance().getReference("questions")
                                                                    .child(modelAnswer.getUserID())
                                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            ModelQuestion modelQuestion = dataSnapshot.getValue(ModelQuestion.class);
                                                                            ModelFeed modelFeed = new ModelFeed(modelQuestion, modelUser, modelAnswer);
                                                                            adapterFeed.addNewItem(modelFeed);
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
    }
}
