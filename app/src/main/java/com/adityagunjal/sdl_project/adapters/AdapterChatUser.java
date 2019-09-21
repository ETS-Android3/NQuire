package com.adityagunjal.sdl_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelChatUser;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterChatUser extends RecyclerView.Adapter<AdapterChatUser.MyViewHolder> {

    Context context;
    ArrayList<ModelChatUser> modelChatUserArrayList;

    public AdapterChatUser(Context context, ArrayList<ModelChatUser> modelChatUserArrayList){
        this.context = context;
        this.modelChatUserArrayList = modelChatUserArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelChatUser modelChatUser = modelChatUserArrayList.get(position);

        holder.username.setText(modelChatUser.getUsername());
        holder.lastMessage.setText(modelChatUser.getLastMessage());
        holder.lastUpdated.setText(modelChatUser.getLastUpdated());
    }

    @Override
    public int getItemCount() {
        return  modelChatUserArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView username, lastMessage, lastUpdated;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.chat_profile_icon);
            username = itemView.findViewById(R.id.chat_username);
            lastMessage = itemView.findViewById(R.id.chat_last_message);
            lastUpdated = itemView.findViewById(R.id.chat_last_updated);
        }
    }
}
