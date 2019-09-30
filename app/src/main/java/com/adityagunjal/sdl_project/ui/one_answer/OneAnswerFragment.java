package com.adityagunjal.sdl_project.ui.one_answer;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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

import com.adityagunjal.sdl_project.ProfileActivity;
import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelAnswer;
import com.adityagunjal.sdl_project.models.ModelQuestion;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class OneAnswerFragment extends Fragment implements View.OnClickListener {

    ModelUser modelUser;
    ModelQuestion modelQuestion;
    ModelAnswer modelAnswer;

    TextView questionText, username, date, upvoteCount, downvoteCount, commentCount, allAnswersCount;
    LinearLayout answerLinearLayout;

    RelativeLayout userInfo;

    ImageView upvoteImage, downvoteImage;
    CircleImageView profilePic;

    DatabaseReference answerRef;

    boolean isAnswerUpvoted = false;
    boolean isAnswerDownvoted = false;

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

        upvoteImage.setOnClickListener(this);
        downvoteImage.setOnClickListener(this);

        userInfo.setOnClickListener(showProfile);

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

        answerRef = FirebaseDatabase.getInstance().getReference("Answers/" + modelAnswer.getAnswerID());

        answerRef.addValueEventListener(listener);
    }

    View.OnClickListener showProfile = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getActivity(), ProfileActivity.class);
            i.putExtra("EXTRA_USER", modelUser);
            i.putExtra("EXTRA_USER_ID", modelAnswer.getUserID());
            startActivity(i);
        }
    };

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            ModelAnswer currentAnswer = dataSnapshot.getValue(ModelAnswer.class);
            currentAnswer.setAnswerID(dataSnapshot.getKey());
            modelAnswer = currentAnswer;

            upvoteCount.setText(Integer.toString(currentAnswer.getUpvotes()));
            downvoteCount.setText(Integer.toString(currentAnswer.getDownvotes()));
            commentCount.setText(Integer.toString(currentAnswer.getComments()) + " comments");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.one_answer_upvote_image){
            if(isAnswerUpvoted && !isAnswerDownvoted){
                isAnswerUpvoted = false;
                upvoteCount.setText(Integer.toString(modelAnswer.getUpvotes() - 1));
                upvoteImage.setImageResource(R.drawable.ic_upvote_icon);

                FirebaseDatabase.getInstance().getReference("Answers/" + modelAnswer.getAnswerID() + "/upvotes")
                        .setValue(modelAnswer.getUpvotes() - 1);
            }else if(!isAnswerDownvoted){
                isAnswerUpvoted = true;
                upvoteCount.setText(Integer.toString(modelAnswer.getUpvotes() + 1));
                upvoteImage.setImageResource(R.drawable.ic_upvote_active);
                FirebaseDatabase.getInstance().getReference("Answers/" + modelAnswer.getAnswerID() + "/upvotes")
                        .setValue(modelAnswer.getUpvotes() + 1);
            }
        }
        if(view.getId() == R.id.one_answer_downvote_image){
            if(isAnswerDownvoted && !isAnswerUpvoted){
                isAnswerDownvoted = false;
                downvoteCount.setText(Integer.toString(modelAnswer.getDownvotes() - 1));
                downvoteImage.setImageResource(R.drawable.ic_downvote_icon);

                FirebaseDatabase.getInstance().getReference("Answers/" + modelAnswer.getAnswerID() + "/downvotes")
                        .setValue(modelAnswer.getDownvotes() - 1);

            }else if(!isAnswerUpvoted){
                isAnswerDownvoted = true;
                downvoteCount.setText(Integer.toString(modelAnswer.getDownvotes() + 1));
                downvoteImage.setImageResource(R.drawable.ic_downvote_active);

                FirebaseDatabase.getInstance().getReference("Answers/" + modelAnswer.getAnswerID() + "/downvotes")
                        .setValue(modelAnswer.getDownvotes() + 1);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        answerRef.removeEventListener(listener);
    }
}
