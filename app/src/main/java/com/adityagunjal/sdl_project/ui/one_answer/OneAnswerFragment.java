package com.adityagunjal.sdl_project.ui.one_answer;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelAnswer;
import com.adityagunjal.sdl_project.models.ModelQuestion;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class OneAnswerFragment extends Fragment {

    ModelUser modelUser;
    ModelQuestion modelQuestion;
    ModelAnswer modelAnswer;

    TextView questionText, username, date, upvoteCount, downvoteCount, commentCount, allAnswersCount;
    LinearLayout answerLinearLayout;

    RelativeLayout userInfo;

    ImageView upvoteImage, downvoteImage;
    CircleImageView profilePic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_one_answer, container, false);

        Bundle bundle = this.getArguments();

        modelUser = (ModelUser) bundle.getSerializable("ModelUser");
        modelQuestion = (ModelQuestion) bundle.getSerializable("ModelQuestion");
        modelAnswer = (ModelAnswer) bundle.getSerializable("ModelAnswer");

        questionText = rootView.findViewById(R.id.one_answer_question);
        username = rootView.findViewById(R.id.one_answer_user_name);
        date = rootView.findViewById(R.id.one_answer_date);
        upvoteCount = rootView.findViewById(R.id.one_answer_upvote_count);
        downvoteCount = rootView.findViewById(R.id.one_answer_downvote_count);
        commentCount = rootView.findViewById(R.id.one_answer_comment_count);
        allAnswersCount = rootView.findViewById(R.id.total_answers_count);

        answerLinearLayout = rootView.findViewById(R.id.one_answer_linear_layout);
        userInfo = rootView.findViewById(R.id.one_answer_rel);

        upvoteImage = rootView.findViewById(R.id.one_answer_upvote_image);
        downvoteImage = rootView.findViewById(R.id.one_answer_downvote_image);
        profilePic = rootView.findViewById(R.id.one_answer_profile_pic);

        setInfo();

        return rootView;
    }

    public void setInfo(){
        questionText.setText(modelQuestion.getText());
        username.setText(modelUser.getUsername());
        date.setText(modelAnswer.getDate());
        upvoteCount.setText(Integer.toString(modelAnswer.getUpvotes()));
        downvoteCount.setText(Integer.toString(modelAnswer.getDownvotes()));
        commentCount.setText(Integer.toString(modelAnswer.getComments()) + " comments");

        float factor =  getContext().getResources().getDisplayMetrics().density;

        HashMap<String, String> answerMap = modelAnswer.getAnswer();
        Iterator answerIterator = answerMap.entrySet().iterator();

        while(answerIterator.hasNext()){
            Map.Entry<String, String> answerElement = (Map.Entry) answerIterator.next();
            String key = answerElement.getKey();

            if(key.charAt(0) == 't'){
                TextView textView = new TextView(answerLinearLayout.getContext());
                textView.setText(answerElement.getValue());
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setTextSize(17);
                answerLinearLayout.addView(textView);
            }else{
                final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topMargin = (int)(factor * 7);
                layoutParams.bottomMargin = (int)(factor * 7);
                final ImageView imageView = new ImageView(answerLinearLayout.getContext());
                imageView.setLayoutParams(layoutParams);
                answerLinearLayout.addView(imageView);

                FirebaseStorage.getInstance().getReference(answerElement.getValue())
                        .getBytes(1024 * 1024)
                        .addOnCompleteListener(new OnCompleteListener<byte[]>() {
                            @Override
                            public void onComplete(@NonNull Task<byte[]> task) {
                                if(task.isSuccessful()){
                                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length));
                                }
                            }
                        });
            }
        }

        FirebaseStorage.getInstance().getReference(modelUser.getImagePath())
                .getBytes(1024 * 1024)
                .addOnCompleteListener(new OnCompleteListener<byte[]>() {
                    @Override
                    public void onComplete(@NonNull Task<byte[]> task) {
                        if(task.isSuccessful()){
                            profilePic.setImageBitmap(BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length));
                        }
                    }
                });
    }

}
