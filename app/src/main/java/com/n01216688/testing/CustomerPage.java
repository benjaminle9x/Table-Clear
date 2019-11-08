package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerPage extends AppCompatActivity {

    ViewFlipper v_flipper;
    Button logout, save, viewProf;
    FirebaseDatabase database;
    DatabaseReference myref;
    DataStructure mData;
    EditText name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        getSupportActionBar().setTitle("Customer Page");

        int images[] = {R.drawable.pizza,R.drawable.delivery,R.drawable.walking};

        v_flipper = findViewById(R.id.v_flipper);
        logout = findViewById(R.id.logout);
        save = findViewById(R.id.save);
        name = findViewById(R.id.inName);
        phone = findViewById(R.id.inPhone);
        address = findViewById(R.id.inAdd);
        viewProf = findViewById(R.id.viewProf);

        getDatabase();

        //For loop
        for(int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(name.getText(), phone.getText(), address.getText());
            }
        });

        viewProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCheckProfile();
            }
        });

    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);    //4 seconds
        v_flipper.setAutoStart(true);

        //Animation
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
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

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        openMainActivity();
    }

    public void openHomePage(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }

    public void openSettingScreen(){
        Intent intent1= new Intent(this,SettingScreen.class);
        startActivity(intent1);
    }

    public void openMainActivity() {
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
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
                openRetrievingPage();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Saving failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openRetrievingPage() {
        Intent i3 = new Intent(CustomerPage.this,checkProfile.class);
        startActivity(i3);
    }

    public void openCheckProfile() {
        Intent i4 = new Intent(this, checkProfile.class);
        startActivity(i4);
    }
}
