package com.n01216688.testing;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class SettingScreen extends AppCompatActivity implements RecyclerAdapter.RecyclerViewClickListener {

    List<CardItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        addItem(getDrawable(R.drawable.profile), "User Profile") ;
        addItem(getDrawable(R.drawable.notification), "Notification Setting") ;
        addItem(getDrawable(R.drawable.language), "Language Change") ;
        addItem(getDrawable(R.drawable.logout), "Logout") ;

        RecyclerAdapter adapter = new RecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(this);
    }

    public void addItem(Drawable icon, String title) {
        CardItem item = new CardItem();

        item.setIconDrawable(icon);
        item.setTitle(title);
        dataList.add(item);

    }


    @Override
    public void onItemClicked(int position) {

        switch (position){
            case 0:
                Intent i = new Intent(SettingScreen.this, ProfileScreen1.class);
                startActivity(i);
                break;
            case 1:
                Intent i1 = new Intent(SettingScreen.this, NotificationScreen.class);
                startActivity(i1);
                break;
            case 2:
                Intent i2 = new Intent(SettingScreen.this, ModifyProfile.class);
                startActivity(i2);
                break;
            case 3:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You are about to Logout. Do you want to continue?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent i3 = new Intent(SettingScreen.this, MainActivity.class);
                                startActivity(i3);

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
    }


}
