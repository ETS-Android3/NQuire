package com.adityagunjal.sdl_project.ui.all_answers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.adapters.AdapterAnswer;
import com.adityagunjal.sdl_project.models.ModelAnswer;
import com.adityagunjal.sdl_project.models.ModelQuestion;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllAnswersFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelAnswer> modelAnswerArrayList = new ArrayList<>();
    AdapterAnswer adapterAnswer;

    ModelQuestion modelQuestion;

    int pageLimit = 10;
    String offset;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_answers, container, false);

        recyclerView = view.findViewById(R.id.all_answers_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapterAnswer = new AdapterAnswer(getActivity(), modelAnswerArrayList);
        recyclerView.setAdapter(adapterAnswer);

        Bundle bundle = this.getArguments();

        modelQuestion = (ModelQuestion) bundle.getSerializable("ModelQuestion");

        populateRecyclerView();

        return  view;
    }

    public void populateRecyclerView(){

        FirebaseDatabase.getInstance().getReference("Answers")
                .orderByChild("questionID")
                .startAt(modelQuestion.getqID())
                .limitToFirst(pageLimit)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

}
