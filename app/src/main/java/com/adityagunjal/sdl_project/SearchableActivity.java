package com.adityagunjal.sdl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.SearchView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adityagunjal.sdl_project.adapters.AdapterSearchQuestion;
import com.adityagunjal.sdl_project.adapters.AdapterSearchUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> questionList;
    ArrayList<String> userNameList;
    ArrayList<String> fullNameList;
    ArrayList<String> profilePicList;
    EditText search_edit_text;
    //SearchView searchView;
   AdapterSearchUser searchAdapterUser;
   AdapterSearchQuestion searchAdapterQuestion;
    SearchView searchView;
    FrameLayout frameLayout;
    CardView questionsCard,usersCard;
    int mainFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_searchable);

        searchView = findViewById(R.id.search_toolbar);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(getResources().getColor(R.color.black));
        questionsCard = findViewById(R.id.question_card_filter);
        usersCard = findViewById(R.id.user_card_filter);


         //frameLayout = findViewById(R.id.search_fragment_container);
        recyclerView =  findViewById(R.id.recyclerView);
       // search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        //searchView = findViewById(R.id.search_toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        questionList = new ArrayList<>();
        userNameList = new ArrayList<>();
        fullNameList = new ArrayList<>();
        profilePicList = new ArrayList<>();

        questionsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersCard.setCardBackgroundColor(getResources().getColor((R.color.white)));
                questionsCard.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                questionsCard.setCardElevation(20);
                usersCard.setCardElevation(0);
                mainFlag = 1;
                fullNameList.clear();
                userNameList.clear();
                profilePicList.clear();
                recyclerView.removeAllViews();

            }
        });

                usersCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        questionsCard.setCardBackgroundColor(getResources().getColor((R.color.white)));
                        usersCard.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        usersCard.setCardElevation(20);
                        questionsCard.setCardElevation(0);
                        mainFlag = 0;
                        questionList.clear();
                        recyclerView.removeAllViews();
                    }
                });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s ) {
                if(mainFlag == 0) {
                    if (!s.isEmpty()) {
                        setAdapter(s);
                    }
                }
                else if(mainFlag == 1)
                {
                    if (!s.isEmpty()) {
                        setAdapterQuestions(s);
                    }
                }
                    //setAdapterQuestions(s.toString());

                return true;
            }
        });




        /*search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                    //setAdapterQuestions(s.toString());
                } else {
                    fullNameList.clear();
                    questionList.clear();
                    userNameList.clear();
                    profilePicList.clear();

                    recyclerView.removeAllViews();
                }
            }
        });*/
    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        fullNameList.clear();
                        userNameList.clear();
                        profilePicList.clear();
                        recyclerView.removeAllViews();

                        int counter = 0;


                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String uid = snapshot.getKey();
                            String fullName;
                            String firstName = snapshot.child("firstName").getValue(String.class);
                            String lastName = snapshot.child("lastName").getValue(String.class);
                         String user_name = snapshot.child("username").getValue(String.class);
                         String profilePicPath = snapshot.child("imagePath").getValue(String.class);
                            //String profile_pic = snapshot.child("profile_pic").getValue(String.class);

                            if (user_name.toLowerCase().contains(searchedString.toLowerCase())) {
                                //questionList.add(full_name);
                                fullName = firstName +" "+ lastName;
                                userNameList.add(user_name);
                                fullNameList.add(fullName);
                                profilePicList.add(profilePicPath);

                                counter++;
                            } else if (firstName.toLowerCase().contains(searchedString.toLowerCase())) {
                       //questionList.add(full_name);
                       // userNameList.add(user_name);
                                String lastName1 = snapshot.child("lastName").getValue(String.class);
                                 fullName = firstName +" "+ lastName1;
                                profilePicList.add(profilePicPath);
                                userNameList.add(user_name);
                                 fullNameList.add(fullName);

                        counter++;
                    }
                            else if(lastName.toLowerCase().contains(searchedString.toLowerCase()))
                            {
                                String firstName1 = snapshot.child("firstName").getValue(String.class);
                                fullName = firstName1 +" "+ lastName;
                                profilePicList.add(profilePicPath);
                                userNameList.add(user_name);
                                fullNameList.add(fullName);
                                counter++;
                            }


                            if (counter == 15)
                                break;
                        }
                        searchAdapterUser = new AdapterSearchUser(SearchableActivity.this, userNameList,fullNameList,profilePicList);
                        recyclerView.setAdapter(searchAdapterUser);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setAdapterQuestions(final String searchedString)
    {
        databaseReference.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                questionList.clear();
                recyclerView.removeAllViews();

                int counter = 0;


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String fullName;
                    String qtext = snapshot.child("text").getValue(String.class);

                    //String profile_pic = snapshot.child("profile_pic").getValue(String.class);

                    if (qtext.toLowerCase().contains(searchedString.toLowerCase())) {
                        questionList.add(qtext);


                        counter++;
                    }


                    if (counter == 15)
                        break;
                }
                searchAdapterQuestion = new AdapterSearchQuestion(SearchableActivity.this, questionList);
                recyclerView.setAdapter(searchAdapterQuestion);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.expandActionView();
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                SearchableActivity.super.onBackPressed();
                return false;
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        ComponentName componentName = new ComponentName(this, SearchableActivity.class);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return true;
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchQuery(query);
        }else{

        }
    }

    public void searchQuery(String query){
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
    }
}
