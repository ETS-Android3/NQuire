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

import java.util.ArrayList;

public class AllAnswersFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelAnswer> modelAnswerArrayList = new ArrayList<>();
    AdapterAnswer adapterAnswer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_answers, container, false);

        recyclerView = view.findViewById(R.id.all_answers_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapterAnswer = new AdapterAnswer(getActivity(), modelAnswerArrayList);
        recyclerView.setAdapter(adapterAnswer);

        populateRecyclerView();

        return  view;
    }

    public void populateRecyclerView(){
        modelAnswerArrayList.add(new ModelAnswer("Username", "12345", null, "12/12/12"));
        modelAnswerArrayList.add(new ModelAnswer("Username", "12345", null, "12/12/12"));
        modelAnswerArrayList.add(new ModelAnswer("Username", "12345", null, "12/12/12"));
        modelAnswerArrayList.add(new ModelAnswer("Username", "12345", null, "12/12/12"));
    }

}
