package com.adityagunjal.sdl_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.MyViewHolder> {

    Context context;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();

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
    }

    @Override
    public int getItemCount() {
        return modelFeedArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, answer, question, lastUpdated;
        ImageView profilePic;

        public MyViewHolder(View itemView) {
            super(itemView);

            profilePic = (ImageView) itemView.findViewById(R.id.card_profile_pic);

            name = (TextView) itemView.findViewById(R.id.card_user_name);
            answer = (TextView) itemView.findViewById(R.id.card_answer_text);
            question = (TextView) itemView.findViewById(R.id.card_question);
            lastUpdated = (TextView) itemView.findViewById(R.id.card_update_info);
        }

    }
}
