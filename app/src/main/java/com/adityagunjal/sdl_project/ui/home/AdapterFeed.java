package com.adityagunjal.sdl_project.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.ShowAnswerActivity;
import com.adityagunjal.sdl_project.models.ModelFeed;

import java.util.ArrayList;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.MyViewHolder> {

    Context context;
    ArrayList<ModelFeed> modelFeedArrayList;

    public AdapterFeed(Context context, ArrayList<ModelFeed> modelFeedArrayList){
        this.context = context;
        this.modelFeedArrayList = modelFeedArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelFeed modelFeed = modelFeedArrayList.get(position);

        holder.name.setText(modelFeed.getName());
        holder.question.setText(modelFeed.getQuestion());
        holder.answer.setText(modelFeed.getAnswer());
        holder.lastUpdated.setText(modelFeed.getLastUpdate());

        holder.profilePic.setImageResource(R.drawable.ic_profile_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(view.getContext(), ShowAnswerActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelFeedArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        final Context itemViewContext;

        TextView name, answer, question, lastUpdated, likes, dislikes, comments;
        ImageView profilePic;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemViewContext = itemView.getContext();

            profilePic = (ImageView) itemView.findViewById(R.id.card_profile_pic);

            name = (TextView) itemView.findViewById(R.id.card_user_name);
            answer = (TextView) itemView.findViewById(R.id.card_answer_text);
            question = (TextView) itemView.findViewById(R.id.card_question);
            lastUpdated = (TextView) itemView.findViewById(R.id.card_update_info);
            likes = itemView.findViewById(R.id.feed_answer_like);
            dislikes = itemView.findViewById(R.id.feed_answer_dislike);
            comments = itemView.findViewById(R.id.feed_answer_comment);
        }
    }
}
