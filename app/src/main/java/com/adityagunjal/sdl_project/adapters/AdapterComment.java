package com.adityagunjal.sdl_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelComment;

import java.util.ArrayList;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.MyViewHolder> {

    Context context;
    ArrayList<ModelComment> modelCommentArrayList;

    public AdapterComment(Context context, ArrayList<ModelComment> modelCommentArrayList){
        this.context = context;
        this.modelCommentArrayList = modelCommentArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelComment modelComment = modelCommentArrayList.get(position);

        holder.profilePic.setImageResource(modelComment.getCommentProfilePic());
        holder.username.setText(modelComment.getUsername());
        holder.comment.setText(modelComment.getComment());
        holder.lastUpdated.setText(modelComment.getLastUpdated());
    }

    @Override
    public int getItemCount() {
        return modelCommentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView profilePic;
        TextView comment;
        TextView username;
        TextView lastUpdated;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.comment_profile_pic);
            username = itemView.findViewById(R.id.comment_username);
            comment = itemView.findViewById(R.id.comment);
            lastUpdated = itemView.findViewById(R.id.comment_last_updated);
        }
    }
}
