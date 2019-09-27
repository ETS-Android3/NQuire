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
import com.adityagunjal.sdl_project.models.ModelAnswer;

import java.util.ArrayList;

public class AdapterAnswer extends RecyclerView.Adapter<AdapterAnswer.MyViewHolder> {

    Context context;
    ArrayList<ModelAnswer> modelAnswerArrayList;

    public AdapterAnswer(Context context, ArrayList<ModelAnswer> modelAnswerArrayList){
        this.context = context;
        this.modelAnswerArrayList = modelAnswerArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_card_2, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelAnswer modelAnswer = modelAnswerArrayList.get(position);

        holder.likes.setText(Integer.toString(modelAnswer.getUpvotes()));
        holder.dislikes.setText(Integer.toString(modelAnswer.getDownvotes()));
        holder.comments.setText(Integer.toString(modelAnswer.getComments()) + " comments");
        holder.answer.setText("Answer goes here");
        holder.lastUpdated.setText(modelAnswer.getDate());
        //holder.profilePic.setImageResource(modelAnswer.getProfile_pic());
        //holder.username.setText(modelAnswer.getUsername());
    }

    @Override
    public int getItemCount() {
        return modelAnswerArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView likes, dislikes, comments, username, lastUpdated, answer;
        ImageView profilePic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            likes = itemView.findViewById(R.id.all_answer_like);
            dislikes = itemView.findViewById(R.id.all_answer_dislike);
            comments = itemView.findViewById(R.id.all_answer_comments);
            username = itemView.findViewById(R.id.all_answer_user_name);
            lastUpdated = itemView.findViewById(R.id.all_answer_update_info);
            answer = itemView.findViewById(R.id.answer_text);
            profilePic = itemView.findViewById(R.id.all_answer_profile_pic);
        }
    }
}
