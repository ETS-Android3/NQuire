package com.adityagunjal.sdl_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.ProfileActivity;
import com.adityagunjal.sdl_project.R;
import com.adityagunjal.sdl_project.models.ModelUser;
import com.adityagunjal.sdl_project.ui.profile.ShowProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AdapterSearchUser extends RecyclerView.Adapter<AdapterSearchUser.UserSearchViewHolder> {

    Context context;

    ArrayList<String> userNameList;
    ArrayList<String> fullNameList;
    ArrayList<String> profilePicList;
    String uid;
    ModelUser user;



    class UserSearchViewHolder extends RecyclerView.ViewHolder {

        TextView  user_name,full_name;
        ImageView profilePic;


        public UserSearchViewHolder(View itemView) {
            super(itemView);

            full_name = (TextView) itemView.findViewById(R.id.full_name);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            profilePic = itemView.findViewById(R.id.profileImage);

        }
    }

    public AdapterSearchUser(Context context, ArrayList<String> userNameList, ArrayList<String> fullNameList, ArrayList<String> profilePicList) {
        this.context = context;
        this.userNameList = userNameList;
        this.fullNameList = fullNameList;
        this.profilePicList = profilePicList;

    }



    @Override
    public UserSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


             view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
             return new UserSearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final UserSearchViewHolder holder, int position) {
        // holder.question.setText(questionList.get(position));

            holder.user_name.setText(userNameList.get(position));
            holder.full_name.setText(fullNameList.get(position));

        FirebaseStorage.getInstance().getReference(profilePicList.get(position))
                .getBytes(1024 * 1024)
                .addOnCompleteListener(new OnCompleteListener<byte[]>() {
                    @Override
                    public void onComplete(@NonNull Task<byte[]> task) {
                        if (task.isSuccessful()) {
                            Bitmap image = BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length);
                            holder.profilePic.setImageBitmap(image);
                        } else {
                            holder.profilePic.setImageResource(R.drawable.ic_profile_pic);
                        }
                    }
                });
        FirebaseDatabase.getInstance().getReference("Usernames/"+userNameList.get(position)+"/uid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uid = dataSnapshot.getValue(String.class);
                //Toast.makeText(context, uid, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();

                FirebaseDatabase.getInstance().getReference(uid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(ModelUser.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent i = new Intent(context, ProfileActivity.class);
                i.putExtra("EXTRA_USER",user);
                i.putExtra("EXTRA_USER_ID",uid);
                context.startActivity(i);

            }



        });




    }

    @Override
    public int getItemCount() {
        return userNameList.size();}
}



