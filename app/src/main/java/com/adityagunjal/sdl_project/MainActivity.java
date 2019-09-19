package com.adityagunjal.sdl_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adityagunjal.sdl_project.models.ModelUser;
import com.adityagunjal.sdl_project.ui.chat.ChatFragment;
import com.adityagunjal.sdl_project.ui.home.HomeFragment;
import com.adityagunjal.sdl_project.ui.ask.AskFragment;
import com.adityagunjal.sdl_project.ui.recent.RecentFragment;
import com.adityagunjal.sdl_project.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    Fragment currentFragment;
    NavigationView navigationView;
    BottomNavigationView bottomNavigation;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavListener);

        getSupportActionBar().setTitle("Home");
        currentFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, currentFragment).commit();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navListener);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        setUser();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private NavigationView.OnNavigationItemSelectedListener navListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch(menuItem.getItemId()){
                        case R.id.drawable_nav_profile:
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                            break;
                        case R.id.drawable_nav_bookmarks:
                            break;
                        case R.id.drawable_nav_drafts:
                            break;
                        case R.id.drawable_nav_settings:
                            break;
                        case R.id.drawable_nav_logout:
                            firebaseAuth.signOut();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                            finish();
                            break;
                    }
                    return true;
                }
            };

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            getSupportActionBar().setTitle("Home");
                            break;
                        case R.id.nav_ask:
                            selectedFragment = new AskFragment();
                            getSupportActionBar().setTitle("Post Question");
                            break;
                        case R.id.nav_recent:
                            selectedFragment = new RecentFragment();
                            getSupportActionBar().setTitle("Recent");
                            break;
                        case R.id.nav_chat:
                            selectedFragment = new ChatFragment();
                            getSupportActionBar().setTitle("Chats");
                            break;
                    }

                    if(!currentFragment.getClass().equals(selectedFragment.getClass())){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        currentFragment = selectedFragment;
                        return true;
                    }else{
                        return false;
                    }
                }
            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.top_search:
                startActivity(new Intent(this, SearchableActivity.class));
                break;
            case R.id.top_notifications_icon:
                Toast.makeText(this, "Notifications Clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    public void setUser(){
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        FirebaseDatabase.getInstance().getReference("Users")
                .child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String username = dataSnapshot.child("username").getValue(String.class);
                        View navHeaderView = navigationView.getHeaderView(0);
                        TextView navHeaderUsernameTextView = navHeaderView.findViewById(R.id.nav_header_username);
                        navHeaderUsernameTextView.setText(username);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
