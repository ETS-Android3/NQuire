package com.adityagunjal.sdl_project.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.adapters.AdapterChatUser;
import com.adityagunjal.sdl_project.models.ModelChatUser;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelChatUser> modelChatUserArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.chat_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterChatUser adapterChatUser = new AdapterChatUser(getActivity(), modelChatUserArrayList);

        recyclerView.setAdapter(adapterChatUser);
        populateRecyclerView();

        return  view;
    }

    public void populateRecyclerView(){
        modelChatUserArrayList.add(new ModelChatUser("Aditya Gunjal", "Good Night", "1 hr ago", "Default"));
        modelChatUserArrayList.add(new ModelChatUser("Aditya Gunjal", "Good Night", "1 hr ago", "Default"));
        modelChatUserArrayList.add(new ModelChatUser("Aditya Gunjal", "Good Night", "1 hr ago", "Default"));
        modelChatUserArrayList.add(new ModelChatUser("Aditya Gunjal", "Good Night", "1 hr ago", "Default"));
        modelChatUserArrayList.add(new ModelChatUser("Aditya Gunjal", "Good Night", "1 hr ago", "Default"));
    }

    public void getChats(){

    }

}
