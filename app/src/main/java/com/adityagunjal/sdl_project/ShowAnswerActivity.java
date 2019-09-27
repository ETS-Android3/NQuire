package com.adityagunjal.sdl_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adityagunjal.sdl_project.ui.all_answers.AllAnswersFragment;
import com.adityagunjal.sdl_project.ui.comment.CommentFragment;
import com.adityagunjal.sdl_project.ui.one_answer.OneAnswerFragment;

public class ShowAnswerActivity extends AppCompatActivity {

    Fragment currentFragment;
    boolean showComments = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        currentFragment = new OneAnswerFragment();

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

    public void onViewMoreAnswers(View view){

        getSupportFragmentManager().beginTransaction().replace(R.id.answer_frame_container, new AllAnswersFragment()).addToBackStack(null).commit();
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
