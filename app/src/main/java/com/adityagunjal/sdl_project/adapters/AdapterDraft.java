package com.adityagunjal.sdl_project.adapters;
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
import com.adityagunjal.sdl_project.models.ModelDraft;
import com.adityagunjal.sdl_project.models.ModelFeed;

import java.util.ArrayList;
public class AdapterDraft extends RecyclerView.Adapter<AdapterDraft.MyViewHolder>{

    Context context;
    ArrayList<ModelDraft> modelDraftArrayList;

    public AdapterDraft(Context context, ArrayList<ModelDraft> modelDraftArrayList){
        this.context = context;
        this.modelDraftArrayList = modelDraftArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.draft_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelDraft modelDraft = modelDraftArrayList.get(position);

        holder.question.setText(modelDraft.getQuestion());
        holder.answer.setText(modelDraft.getAnswer());

    }

    @Override
    public int getItemCount() {
        return modelDraftArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        final Context itemViewContext;

        TextView answer, question;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemViewContext = itemView.getContext();

            question = (TextView) itemView.findViewById(R.id.draft_card_question);
            answer = (TextView) itemView.findViewById(R.id.draft_card_answer_text);

        }
    }
}
