package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class checkProfile extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref;
    DataStructure mData;
    Button show1;

    EditText name, phone, address, timestamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_profile);

        getSupportActionBar().setTitle("View Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.ouName);
        phone = findViewById(R.id.ouPhone);
        address = findViewById(R.id.ouAddress);
        show1 = findViewById(R.id.show);

        getDatabase();

        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveData();
            }
        });
    }

    private void getDatabase(){
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "userdata/" + mAuth.getUid();
        myref = database.getReference(path);
    }

    private void retrieveData(){
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                name.setText("Fullname: " + ds.getName());
                phone.setText("Phone number: " + ds.getPhone());
                address.setText("Address: " + ds.getAddress());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                name.setText("Fullname: " + ds.getName());
                phone.setText("Phone number: " + ds.getPhone());
                address.setText("Address: " + ds.getAddress());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataStructure> arraylist= new ArrayList<DataStructure>();

                if(dataSnapshot != null && dataSnapshot.getValue() != null){
                    for(DataSnapshot a : dataSnapshot.getChildren()) {
                        DataStructure dataStructure = new DataStructure();
                        dataStructure.setName(a.getValue(DataStructure.class).getName());
                        dataStructure.setPhone(a.getValue(DataStructure.class).getPhone());
                        dataStructure.setAddress(a.getValue(DataStructure.class).getAddress());

                        arraylist.add(dataStructure);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No data available on Database", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Retrieving data failed", Toast.LENGTH_LONG).show();
            }
        });
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
            case R.id.setting:
                openSettingScreen();
                return true;
            case R.id.lout:
                onBackPressed();
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


    public void openSettingScreen(){
        Intent intent1= new Intent(this,SettingScreen.class);
        startActivity(intent1);
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
