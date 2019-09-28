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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.adapters.AdapterAnswer;
import com.adityagunjal.sdl_project.adapters.AdapterDraft;
import com.adityagunjal.sdl_project.models.ModelAnswer;
import com.adityagunjal.sdl_project.models.ModelDraft;
import com.adityagunjal.sdl_project.models.ModelQuestion;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class DraftFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    ArrayList<ModelDraft> modelDraftArrayList = new ArrayList<>();
    AdapterDraft adapterDraft;
    ModelUser modelUser;
    ModelQuestion modelQuestion;
    ModelAnswer modelAnswer;




    int pageLimit = 10;
    String offset;

    TextView questionText;
    LinearLayout answerLinearLayout;

    RelativeLayout userInfo;

    ImageView upvoteImage, downvoteImage;
    CircleImageView profilePic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draft, container, false);
        Bundle bundle = this.getArguments();
        modelUser = (ModelUser) bundle.getSerializable("ModelUser");
        modelQuestion = (ModelQuestion) bundle.getSerializable("ModelQuestion");
        modelAnswer = (ModelAnswer) bundle.getSerializable("ModelAnswer");
        recyclerView = view.findViewById(R.id.recycler_view_drafts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        questionText = view.findViewById(R.id.draft_card_question);

        adapterDraft = new AdapterDraft(getActivity(), modelDraftArrayList);
        recyclerView.setAdapter(adapterDraft);

        populateRecyclerView();

        return  view;
    }

    public void populateRecyclerView(){

        FirebaseDatabase.getInstance().getReference("Drafts")
                .orderByChild("draftID")
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

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back_button_icon){
            getActivity().onBackPressed();
        }
    }



}
