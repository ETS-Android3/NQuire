package com.adityagunjal.sdl_project.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;

import java.util.ArrayList;

public class AdapterSearchQuestion extends RecyclerView.Adapter<AdapterSearchQuestion.QuestionSearchViewHolder> {

    Context context;
    ArrayList<String> questionList;






    class QuestionSearchViewHolder extends RecyclerView.ViewHolder {

        TextView question;


        public QuestionSearchViewHolder(View itemView) {
            super(itemView);


            question = itemView.findViewById(R.id.question_list_text);
        }
    }



    public AdapterSearchQuestion(Context context, ArrayList<String> questionList)
    {
        this.context = context;
        this.questionList = questionList;

    }

    @Override
    public QuestionSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(context).inflate(R.layout.search_list_questions, parent, false);
        return new QuestionSearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final QuestionSearchViewHolder holder, int position) {
         holder.question.setText(questionList.get(position));

        holder.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Question Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return questionList.size();}



}
