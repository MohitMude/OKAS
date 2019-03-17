package com.example.android.okas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
        FirebaseAuth firebaseAuth;
        private Button btnOpenOverInternet;
        private Button btn_Refresh;
        private Button btn_Change_Status;
        private TextView txtViewRoomNo;
        SharedPreferences sharedPreferences;
        String email_room;
        String ip;
        String roomno;
        Users user;

        ArrayList<Users> data;
        DatabaseReference databaseUsers=FirebaseDatabase.getInstance().getReference();
        DatabaseReference rootdatabaseUser=databaseUsers.child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // email=sharedPreferences.getString("email",null);
        //ip=sharedPreferences.getString("ip",null);

        data=new ArrayList<>();
        //Log.e("Tag","Success");
        //Intent j;
        //j = getIntent();
        Bundle extras=getIntent().getExtras();
        if(extras!=null)
        {
            email_room=extras.getString("str");
        }


       txtViewRoomNo=findViewById(R.id.txtview_room_no);
        txtViewRoomNo.setText(" HI ");

        btn_Change_Status=findViewById(R.id.btn_change_status);
        btn_Change_Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        btn_Refresh=(Button)findViewById(R.id.btn_refresh);
        btn_Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RoomActivity.this, "STATUS WILL BE REFRESHED", Toast.LENGTH_SHORT).show();
            }
        });

        btnOpenOverInternet=(Button)findViewById(R.id.Btn_open_over_internet);
        btnOpenOverInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OpenOverInternet.class));

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rootdatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Users>> t=new GenericTypeIndicator<ArrayList<Users>>();
                data=dataSnapshot.getValue(t);

                if (data==null || data.size()==0) {
                    Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                    Log.e("Tag","no data");
                    return;
                }
                for (int i=0;i<data.size();i++) Log.e("Tag",data.get(i).getUsername());

                for (int i=0;i<data.size();i++) {
                    if (data.get(i).getUsername().trim().equals(email_room)) {

                        Toast.makeText(getApplicationContext(), ip, Toast.LENGTH_SHORT).show();
                        return;

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        txtViewRoomNo.setText(roomno);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_logout) {
               firebaseAuth.signOut();
               finish();
               startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
