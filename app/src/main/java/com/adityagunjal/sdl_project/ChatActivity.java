package com.adityagunjal.sdl_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.adityagunjal.sdl_project.adapters.AdapterChat;
import com.adityagunjal.sdl_project.models.ModelChat;

import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelChat> modelChatArrayList = new ArrayList<>();
    AdapterChat adapterChat;

    EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.chat_recycler_view);
        adapterChat = new AdapterChat(this, modelChatArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterChat);

        messageText = findViewById(R.id.entered_message);

        populateRecyclerView();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void populateRecyclerView(){
        modelChatArrayList.add(new ModelChat("Aditya", "Hi ...", "12:45 AM"));
        modelChatArrayList.add(new ModelChat("Ad", "Hello ...", "12:45 AM"));
        modelChatArrayList.add(new ModelChat("Aditya123", "Good Night", "12:45 AM"));
        modelChatArrayList.add(new ModelChat("Aditya", "Good Morning", "12:45 AM"));
    }

    public void onSendMessageClicked(View view){
        String message = messageText.getText().toString();
        if(message.length() == 0)
            return;
        ModelChat modelChat = new ModelChat("Aditya", message, "11:32 AM");
        adapterChat.addItem(modelChat);
        recyclerView.scrollToPosition(adapterChat.getItemCount() - 1);
        messageText.setText("");
        messageText.clearFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

}
