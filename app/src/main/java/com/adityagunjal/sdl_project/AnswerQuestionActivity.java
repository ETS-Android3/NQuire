package com.adityagunjal.sdl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerQuestionActivity extends AppCompatActivity {

    String questionID, questionText, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        Intent i = getIntent();
        questionID = i.getStringExtra("EXTRA_QUESTION_ID");
        questionText = i.getStringExtra("EXTRA_QUESTION_TEXT");
        userID = i.getStringExtra("EXTRA_USER_ID");

        TextView questionTextView = findViewById(R.id.question_text);
        questionTextView.setText(questionText);
    }

    public void onBackPressed(View view) {
        super.onBackPressed();
    }

}
