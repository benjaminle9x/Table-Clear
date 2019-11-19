package com.n01216688.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileScreen1 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myref;
    DataStructure mData;
    Button password;
    Button edit;
    EditText name, phone, address, timestamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen1);

        getSupportActionBar().setTitle("View Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.ouName);
        phone = findViewById(R.id.ouPhone);
        address = findViewById(R.id.ouAddress);
        password = findViewById(R.id.password);
        edit = findViewById(R.id.edit);

        getDatabase();
        retrieveData();

        edit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent i1 = new Intent(ProfileScreen1.this, EditProfile1.class);
                startActivity(i1);
            }
        });


        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileScreen1.this, NewPassword1.class);
                startActivity(i);
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




}
