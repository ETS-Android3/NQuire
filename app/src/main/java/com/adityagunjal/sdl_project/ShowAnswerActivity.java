package com.adityagunjal.sdl_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adityagunjal.sdl_project.models.ModelAnswer;
import com.adityagunjal.sdl_project.models.ModelQuestion;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.adityagunjal.sdl_project.ui.all_answers.AllAnswersFragment;
import com.adityagunjal.sdl_project.ui.comment.CommentFragment;
import com.adityagunjal.sdl_project.ui.one_answer.OneAnswerFragment;

import java.io.Serializable;

public class ShowAnswerActivity extends AppCompatActivity {

    Fragment currentFragment;
    boolean showComments = false;

    Fragment currentAnswer;

    ModelQuestion modelQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        currentFragment = new OneAnswerFragment();

        Intent i = getIntent();
        modelQuestion = (ModelQuestion) i.getSerializableExtra("EXTRA_QUESTION");

        Bundle bundle = new Bundle();
        bundle.putSerializable("ModelUser", i.getSerializableExtra("EXTRA_USER"));
        bundle.putSerializable("ModelAnswer", i.getSerializableExtra("EXTRA_ANSWER"));
        bundle.putSerializable("ModelQuestion", i.getSerializableExtra("EXTRA_QUESTION"));

        currentFragment.setArguments(bundle);

        currentAnswer = currentFragment;

        getSupportFragmentManager().beginTransaction().replace(R.id.answer_frame_container, currentFragment).commit();

    }

    public void onCommentClick(View view){
        showComments = !showComments;
        if(showComments){
            FragmentTransaction ft = currentFragment.getChildFragmentManager().beginTransaction();
            ft.replace(R.id.comment_container, new CommentFragment());
            ft.addToBackStack(null);
            ft.commit();
        }
        else{
            FragmentManager fm = currentFragment.getChildFragmentManager();
            if(fm.getBackStackEntryCount() > 0)
                fm.popBackStack();
        }
    }

    public void showAnswer(ModelAnswer answer, ModelUser user){
        Bundle bundle = new Bundle();
        bundle.putSerializable("ModelUser", user);
        bundle.putSerializable("ModelAnswer", answer);
        bundle.putSerializable("ModelQuestion", modelQuestion);

        currentFragment = new OneAnswerFragment();
        currentFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.answer_frame_container, currentFragment).commit();
    }


    public void onViewMoreAnswers(View view){

        Fragment allAnswersFragment = new AllAnswersFragment(this);

        Bundle bundle = new Bundle();
        bundle.putSerializable("ModelQuestion", modelQuestion);

        allAnswersFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().remove(currentAnswer);
        currentAnswer = currentFragment;

        getSupportFragmentManager().beginTransaction().replace(R.id.answer_frame_container, allAnswersFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();
    }

    public void onBackPressed(View view){
        this.finish();
    }

}
