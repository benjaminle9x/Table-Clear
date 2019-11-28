package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManagerPage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_page);


        getSupportActionBar().setTitle("Manager Page");

        ArrayList<WaitListStructure> waitlist = new ArrayList<>();
        waitlist.add(new WaitListStructure("Benjamin Le", "437-235-6499", "3", "Subway", "15:05", "15"));
        waitlist.add(new WaitListStructure("Daniel West", "111-111-1111", "2", "KFC", "22:00", "9"));
        waitlist.add(new WaitListStructure("Hyoje Lee", "010-212-4344", "4", "Huong Que", "13:01", "21"));

        mRecyclerView = findViewById(R.id.r_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new WaitListAdapter(waitlist);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.logout:
                onBackPressed();
                return true;
            case R.id.adding:
                openAddRestaurant();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are about to Logout. Do you want to continue?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        openMainActivity();
    }

    public void openAddRestaurant(){
        Intent i0 = new Intent(this,AddRestaurant.class);
        startActivity(i0);
    }

    public void openMainActivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
