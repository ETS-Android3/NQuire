package com.adityagunjal.sdl_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.ShowAnswerActivity;
import com.adityagunjal.sdl_project.models.ModelAnswer;
import com.adityagunjal.sdl_project.models.ModelFeed;
import com.adityagunjal.sdl_project.models.ModelQuestion;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;

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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ModelFeed modelFeed = modelFeedArrayList.get(position);

        ModelUser modelUser = modelFeed.getUser();
        ModelQuestion modelQuestion = modelFeed.getQuestion();
        ModelAnswer modelAnswer = modelFeed.getAnswer();

        holder.name.setText(modelUser.username);
        holder.question.setText(modelQuestion.getText());
        holder.lastUpdated.setText(modelAnswer.getDate());
        holder.likes.setText(modelAnswer.getUpvotes());
        holder.dislikes.setText(modelAnswer.getDownvotes());
        holder.comments.setText(modelAnswer.getComments());

        FirebaseStorage.getInstance().getReference(modelUser.getImagePath())
                .getBytes(1024 * 1024)
                .addOnCompleteListener(new OnCompleteListener<byte[]>() {
                    @Override
                    public void onComplete(@NonNull Task<byte[]> task) {
                        if(task.isSuccessful()) {
                            Bitmap image = BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length);
                            holder.profilePic.setImageBitmap(image);
                        }else{
                            holder.profilePic.setImageResource(R.drawable.ic_profile_pic);
                        }
                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ShowAnswerActivity.class);
                context.startActivity(i);
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

            profilePic = itemView.findViewById(R.id.card_profile_pic);

            name = itemView.findViewById(R.id.card_user_name);
            answer = itemView.findViewById(R.id.card_answer_text);
            question = itemView.findViewById(R.id.card_question);
            lastUpdated = itemView.findViewById(R.id.card_update_info);
            likes = itemView.findViewById(R.id.feed_answer_like);
            dislikes = itemView.findViewById(R.id.feed_answer_dislike);
            comments = itemView.findViewById(R.id.feed_answer_comment);
        }

    }

    public void addNewItem(ModelFeed modelFeed){
        modelFeedArrayList.add(modelFeed);
        notifyDataSetChanged();
    }
}
