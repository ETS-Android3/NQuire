package com.adityagunjal.sdl_project.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelChat;

import java.util.ArrayList;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.MyViewHolder>{

    Context context;
    public ArrayList<ModelChat> modelChatArrayList;

    public AdapterChat(Context context, ArrayList<ModelChat> modelChatArrayList){
        this.context = context;
        this.modelChatArrayList = modelChatArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelChat modelChat = modelChatArrayList.get(position);

        if(!modelChat.getSender().equals("Aditya")){
            holder.message.setTextColor(Color.parseColor("#000000"));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.LEFT;
            float factor = holder.card.getContext().getResources().getDisplayMetrics().density;
            layoutParams.leftMargin = (int)(20 * factor);
            layoutParams.rightMargin = (int)(20 * factor);
            layoutParams.topMargin = (int)(8 * factor);
            layoutParams.bottomMargin = (int)(8 * factor);
            holder.card.setLayoutParams(layoutParams);
            Drawable drawable = context.getResources().getDrawable(R.drawable.white_rectangle);
            holder.linearLayout.setBackground(drawable);
            holder.time.setTextColor(Color.parseColor("#696969"));
        } else {
            holder.message.setTextColor(Color.parseColor("#ffffff"));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.RIGHT;
            float factor = holder.card.getContext().getResources().getDisplayMetrics().density;
            layoutParams.leftMargin = (int)(20 * factor);
            layoutParams.rightMargin = (int)(20 * factor);
            layoutParams.topMargin = (int)(8 * factor);
            layoutParams.bottomMargin = (int)(8 * factor);
            holder.card.setLayoutParams(layoutParams);
            Drawable drawable = context.getResources().getDrawable(R.drawable.blue_rectangle);
            holder.linearLayout.setBackground(drawable);
            holder.time.setTextColor(Color.parseColor("#e4e4e4"));
        }

        holder.message.setText(modelChat.getMessage());
        holder.time.setText(modelChat.getTime());
    }

    @Override
    public int getItemCount() {
        return modelChatArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView message, time;
        CardView card;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            message = itemView.findViewById(R.id.chat_message_text);
            time = itemView.findViewById(R.id.chat_message_time);
            card = itemView.findViewById(R.id.chat_msg_card);
            linearLayout = itemView.findViewById(R.id.chat_card_linear_layout);
        }
    }

    public void addItem(ModelChat modelChat){
        modelChatArrayList.add(modelChat);
        notifyDataSetChanged();
    }

}
