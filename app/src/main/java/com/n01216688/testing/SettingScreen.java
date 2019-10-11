package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class SettingScreen extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Item> exampleList = new ArrayList<>();
        exampleList.add(new Item(R.drawable.ic_noti,"Line1","Line2"));
        exampleList.add(new Item(R.drawable.ic_zoomin,"Line 3","Line 4"));
        exampleList.add(new Item(R.drawable.ic_warning,"Line 5","Line 6"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.item1:
                openHomePage();
                return true;
            case R.id.setting:
                openSettingScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openHomePage(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }

    public void openSettingScreen(){
        Intent intent1= new Intent(this,SettingScreen.class);
        startActivity(intent1);
    }
}
