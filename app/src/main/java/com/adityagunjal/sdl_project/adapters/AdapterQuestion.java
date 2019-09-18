package com.adityagunjal.sdl_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelQuestion;

import java.util.ArrayList;

public class AdapterQuestion extends RecyclerView.Adapter<AdapterQuestion.MyViewHolder>{

    Context context;
    ArrayList<ModelQuestion> modelQuestionArrayList;

    public AdapterQuestion(Context context, ArrayList<ModelQuestion> modelQuestionArrayList){
        this.context = context;
        this.modelQuestionArrayList = modelQuestionArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelQuestion modelQuestion = modelQuestionArrayList.get(position);

        holder.user.setText("Asked by " + modelQuestion.getUsername());
        holder.question.setText(modelQuestion.getQuestion());
        String answerString = (modelQuestion.getAnswers() == 0) ? "Not Answers yet": Integer.toString(modelQuestion.getAnswers()) + " Answers";
        holder.answers.setText(answerString);
        holder.askDate.setText(modelQuestion.getDateOfAnswer());
    }

    @Override
    public int getItemCount() {
        return modelQuestionArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        final Context itemViewContext;

        TextView question, user, askDate, answers;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemViewContext = itemView.getContext();

            question = itemView.findViewById(R.id.question);
            user = itemView.findViewById(R.id.username);
            askDate = itemView.findViewById(R.id.question_date);
            answers = itemView.findViewById(R.id.no_of_answers);
        }
    }

}
