package com.n01216688.testing;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditProfile1 extends AppCompatActivity {

//commit

    FirebaseDatabase database;
    DatabaseReference myref;
    DataStructure mData;
    Button save;
    EditText name, phone, address, timestamp;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile1);
        dialog = new ProgressDialog(this);

        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.ouName);
        phone = findViewById(R.id.ouPhone);
        address = findViewById(R.id.ouAddress);
        save = findViewById(R.id.save);

        getDatabase();
        retrieveData();

        save.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                dialog.setMessage("Changing profile, please wait");
                dialog.show();
                writeData(name.getText(), phone.getText(), address.getText());
                finish();

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
                name.setText(ds.getName());
                phone.setText(ds.getPhone());
                address.setText(ds.getAddress());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                name.setText(ds.getName());
                phone.setText(ds.getPhone());
                address.setText(ds.getAddress());
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

    private DataStructure createData(Editable name, Editable phone, Editable address){
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
        return new DataStructure(String.valueOf(name),
                String.valueOf(phone),
                String.valueOf(address),
                timestamp);
    }

    private void writeData(Editable name, Editable phone, Editable address) {
        DataStructure mData = createData(name,phone,address);
        myref.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Changes saved", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Saving failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}

