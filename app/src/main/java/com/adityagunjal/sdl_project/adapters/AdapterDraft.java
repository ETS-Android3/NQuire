package com.adityagunjal.sdl_project.adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.ShowAnswerActivity;
import com.adityagunjal.sdl_project.models.ModelDraft;
import com.adityagunjal.sdl_project.models.ModelFeed;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AdapterDraft extends RecyclerView.Adapter<AdapterDraft.MyViewHolder>{

    ModelUser user;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ModelDraft modelDraft = modelDraftArrayList.get(position);
        HashMap<String, String> answerMap = modelDraft.getAnswer();
        Iterator answerIterator = answerMap.entrySet().iterator();
        float factor = holder.answer.getContext().getResources().getDisplayMetrics().density;
        String answerText = "";
        int flag0 = 0, flag1 = 0;
        while(answerIterator.hasNext()){
            Map.Entry<String, String> answerElement = (Map.Entry) answerIterator.next();
            String key = answerElement.getKey();
            if(key.charAt(0) == 't'){
                String text = answerElement.getValue();
                for(int i = 0; i < text.length() && answerText.length() < 100; i++){
                    answerText += text.charAt(i);
                }
            }else {
                if(flag1 == 0){
                    FirebaseStorage.getInstance().getReference(answerElement.getValue())
                            .getBytes(1024 * 1024)
                            .addOnCompleteListener(new OnCompleteListener<byte[]>() {
                                @Override
                                public void onComplete(@NonNull Task<byte[]> task) {
                                    holder.answerImage.setImageBitmap(BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length));
                                    holder.answerImage.setVisibility(View.VISIBLE);
                                }
                            });
                    flag1 = 1;
                }
            }
            if(flag0 == 1 && flag1 == 1){
                break;
            }
        }

        if(flag1 == 1){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = (int)(factor * 10);
            holder.answer.setLayoutParams(layoutParams);
        }

        holder.answer.setText(answerText + " ...");

        final String userID = modelDraft.getUserID();




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return modelDraftArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        final Context itemViewContext;
        ImageView answerImage;
        TextView answer, question;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemViewContext = itemView.getContext();

            answerImage = itemView.findViewById(R.id.imageView);
            question = (TextView) itemView.findViewById(R.id.draft_card_question);
            answer = (TextView) itemView.findViewById(R.id.draft_card_answer_text);

        }
    }
}
