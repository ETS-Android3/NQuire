package com.adityagunjal.sdl_project.ui.recent;

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
import com.adityagunjal.sdl_project.adapters.AdapterQuestion;
import com.adityagunjal.sdl_project.models.ModelQuestion;

import java.util.ArrayList;

public class RecentFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelQuestion> modelQuestionArrayList = new ArrayList<>();
    AdapterQuestion adapterQuestion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        recyclerView = view.findViewById(R.id.recent_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapterQuestion = new AdapterQuestion(getActivity(), modelQuestionArrayList);
        recyclerView.setAdapter(adapterQuestion);

        populateRecyclerView();

        return view;
    }

    public void populateRecyclerView(){
        modelQuestionArrayList.add(new ModelQuestion(1, 1, 0, "Question", "Username", "12/12/12"));
        modelQuestionArrayList.add(new ModelQuestion(1, 1, 0, "Question", "Username", "12/12/12"));
        modelQuestionArrayList.add(new ModelQuestion(1, 1, 0, "Question", "Username", "12/12/12"));
        modelQuestionArrayList.add(new ModelQuestion(1, 1, 0, "Question", "Username", "12/12/12"));
        modelQuestionArrayList.add(new ModelQuestion(1, 1, 0, "Question", "Username", "12/12/12"));
    }
}
