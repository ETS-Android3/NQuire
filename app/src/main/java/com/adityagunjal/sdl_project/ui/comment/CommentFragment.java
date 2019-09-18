package com.adityagunjal.sdl_project.ui.comment;

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
import com.adityagunjal.sdl_project.adapters.AdapterComment;
import com.adityagunjal.sdl_project.models.ModelComment;

import java.util.ArrayList;

public class CommentFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelComment> modelCommentArrayList = new ArrayList<>();
    AdapterComment adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View commentFragmentView = inflater.inflate(R.layout.fragment_comment, container, false);

        recyclerView = commentFragmentView.findViewById(R.id.recycler_view_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new AdapterComment(getActivity(), modelCommentArrayList);

        recyclerView.setAdapter(adapter);

        populateRecyclerView();

        return commentFragmentView;
    }

    public void populateRecyclerView(){
        modelCommentArrayList.add(new ModelComment(R.drawable.ic_profile_pic, "Aditya Gunjal","12 hrs ago", "Test Comment"));
        modelCommentArrayList.add(new ModelComment(R.drawable.ic_profile_pic, "Aditya Gunjal","12 hrs ago", "Test Comment"));
        modelCommentArrayList.add(new ModelComment(R.drawable.ic_profile_pic, "Aditya Gunjal","12 hrs ago", "Test Comment"));
        modelCommentArrayList.add(new ModelComment(R.drawable.ic_profile_pic, "Aditya Gunjal","12 hrs ago", "Test Comment"));
        modelCommentArrayList.add(new ModelComment(R.drawable.ic_profile_pic, "Aditya Gunjal","12 hrs ago", "Test Comment"));
    }

}
