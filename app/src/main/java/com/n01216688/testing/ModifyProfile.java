package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifyProfile extends AppCompatActivity {
    Button save;
    FirebaseDatabase database;
    DatabaseReference myref;
    DataStructure mData;
    EditText name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        save = findViewById(R.id.save);
        name = findViewById(R.id.inName);
        phone = findViewById(R.id.inPhone);
        address = findViewById(R.id.inAdd);

        getDatabase();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(name.getText(), phone.getText(), address.getText());
            }
        });
    }

    private void getDatabase() {
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String path = "userdata/" + mAuth.getUid();
        myref = database.getReference(path);
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
                openCustomerPage();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Saving failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openCustomerPage() {
        Intent i = new Intent(this,CustomerPage.class);
        startActivity(i);
    }
}
