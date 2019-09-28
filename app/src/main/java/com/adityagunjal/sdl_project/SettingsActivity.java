package com.adityagunjal.sdl_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SettingsActivity extends AppCompatActivity {
    TextView changePassword,delete_account;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    TextView tv_message,change_pass,del_message;
    SharedPreferences pref;
    AppCompatButton btn_change_password,btn_logout;
    EditText et_old_password,et_new_password,et_confirm_password,del_pass,del_cpass;
    AlertDialog dialog;
    ProgressBar progress,del_p;
    View view,delView;

    FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();

    void changePasswordFunction(String oldPassword, String newPassword)
    {
        final String email = fuser.getEmail();
        final String newPass = newPassword;

        AuthCredential credential = EmailAuthProvider.getCredential(email,oldPassword);

        fuser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    fuser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progress.setVisibility(View.GONE);
                                tv_message.setVisibility(View.GONE);
                                dialog.dismiss();
                                Toast.makeText(SettingsActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SettingsActivity.this, "Error in Updating password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {

                    progress.setVisibility(View.GONE);
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Invalid Password");
                    et_old_password.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(SettingsActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void deleteAccount(String password)
    {

        final String email = fuser.getEmail();


        AuthCredential credential = EmailAuthProvider.getCredential(email,password);

        fuser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e("TAG", "onComplete: authentication complete");
                            fuser.delete()
                                    .addOnCompleteListener (new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.e("TAG", "User account deleted.");
                                            } else {
                                                Log.e("TAG", "User account deletion unsucessful.");
                                            }
                                        }
                                    });
                        } else {
                            del_p.setVisibility(View.GONE);
                            del_message.setVisibility(View.VISIBLE);
                            del_message.setText("Invalid Password");
                            del_pass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                            Toast.makeText(SettingsActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(SettingsActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void deactivateAccount(String password)
    {
        final String email = fuser.getEmail();
        String userId = fuser.getUid();


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        change_pass = findViewById(R.id.change_password_text);
        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        delete_account = findViewById(R.id.delete_account_text);
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAccountConfirm();
            }
        });


    }

    public void showDialog(){


        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this,android.R.style.Theme_Material_Light_Dialog_Alert);

        view = getLayoutInflater().inflate(R.layout.change_password,null);
        et_old_password = view.findViewById(R.id.et_old_password);
        et_new_password = view.findViewById(R.id.et_new_password);
        et_confirm_password = view.findViewById(R.id.et_confirm_password);
        tv_message = view.findViewById(R.id.tv_message);
        progress = view.findViewById(R.id.progress);
        builder.setView(view);

        String titleText = "Change Password";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
        SpannableStringBuilder sBuilder = new SpannableStringBuilder(titleText);
        sBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        builder.setTitle(titleText);
        builder.setPositiveButton("Change Password", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_password = et_old_password.getText().toString();
                String new_password = et_new_password.getText().toString();
                String confirm_password = et_confirm_password.getText().toString();

                if(!old_password.isEmpty() && !new_password.isEmpty() && new_password.length()>=6  && new_password.equals(confirm_password)){

                    progress.setVisibility(View.VISIBLE);
                    changePasswordFunction(old_password,new_password);

                }else if(new_password.length()<6) {
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Password Should Be Atleast 6 Characters");
                    et_new_password.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);


                }
                else if(!new_password.equals(confirm_password))
                {
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Passwords Do Not Match");
                    et_new_password.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    et_confirm_password.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                }
                else{
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Fields cannot empty");
                    et_old_password.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                }
            }
        });

        et_old_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_message.setVisibility(view.GONE);
                et_old_password.getBackground().clearColorFilter();
            }
        });

        et_new_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_message.setVisibility(view.GONE);
                et_new_password.getBackground().clearColorFilter();
            }
        });

        et_confirm_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_message.setVisibility(view.GONE);
                et_confirm_password.getBackground().clearColorFilter();
            }
        });
    }

    public void showDeleteAccountDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this,android.R.style.Theme_Material_Light_Dialog_Alert);

        delView = getLayoutInflater().inflate(R.layout.delete_account,null);
        del_pass = delView.findViewById(R.id.del_password);
        del_cpass= delView.findViewById(R.id.del_confirm_password);

        del_message = delView.findViewById(R.id.del_message);
        del_p = delView.findViewById(R.id.del_progress);
        builder.setView(delView);

        String titleText = "Delete Account";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
        SpannableStringBuilder sBuilder = new SpannableStringBuilder(titleText);
        sBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        builder.setTitle(titleText);
        builder.setPositiveButton("Delete Account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = del_pass.getText().toString();
                String cpass = del_cpass.getText().toString();


                if(!pass.isEmpty() && !cpass.isEmpty()   && pass.equals(cpass)){

                    del_p.setVisibility(delView.VISIBLE);
                    deleteAccount(pass);

                }
                else if(!pass.equals(cpass))
                {
                    del_message.setVisibility(delView.VISIBLE);
                    del_message.setText("Passwords Do Not Match");
                    del_pass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    del_cpass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                }
                else{
                    del_message.setVisibility(delView.VISIBLE);
                    del_message.setText("Fields cannot empty");
                    del_pass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                }
            }
        });

        del_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del_message.setVisibility(delView.GONE);
                del_pass.getBackground().clearColorFilter();
            }
        });

        del_cpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del_message.setVisibility(delView.GONE);
                del_cpass.getBackground().clearColorFilter();
            }
        });


    }

    void delAccountConfirm()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this,android.R.style.Theme_Material_Light_Dialog_Alert);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure,you want to delete your account?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                showDeleteAccountDialog();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    public void onBackPressed(View view){
        super.onBackPressed();
    }

}
