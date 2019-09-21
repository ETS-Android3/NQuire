package com.adityagunjal.sdl_project.ui.draft;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.adapters.AdapterDraft;
import com.adityagunjal.sdl_project.models.ModelDraft;



import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class DraftFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    ArrayList<ModelDraft> modelDraftArrayList = new ArrayList<>();
    AdapterDraft adapterDraft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draft, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_drafts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        CircleImageView imageButton = view.findViewById(R.id.back_button_icon);
        imageButton.setOnClickListener(this);

        adapterDraft = new AdapterDraft(getActivity(), modelDraftArrayList);
        recyclerView.setAdapter(adapterDraft);

        populateRecyclerView();

        return  view;
    }

    public void populateRecyclerView(){
        modelDraftArrayList.add(new ModelDraft(1,1,"Question1","Answer1"));
        modelDraftArrayList.add(new ModelDraft(2,2,"Question2","Answer2"));
        modelDraftArrayList.add(new ModelDraft(3,3,"Question3","Answer3"));
        modelDraftArrayList.add(new ModelDraft(4,4,"Question4","Answer4"));
        modelDraftArrayList.add(new ModelDraft(5,5,"Question5","Answer5"));
        modelDraftArrayList.add(new ModelDraft(6,6,"Question6","Answer6"));
        modelDraftArrayList.add(new ModelDraft(7,7,"Question7","Answer7"));
        modelDraftArrayList.add(new ModelDraft(8,8,"Question8","Answer8"));
        modelDraftArrayList.add(new ModelDraft(9,9,"Question9","Answer9"));

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back_button_icon){
            getActivity().onBackPressed();
        }
    }
}
