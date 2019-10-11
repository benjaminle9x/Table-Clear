package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class CustomerPage extends AppCompatActivity {

    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        getSupportActionBar().setTitle("Customer Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int images[] = {R.drawable.pizza,R.drawable.delivery,R.drawable.walking};

        v_flipper = findViewById(R.id.v_flipper);

        //For loop
        for(int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }
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

    public void openHomePage(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }

    public void openSettingScreen(){
        Intent intent1= new Intent(this,SettingScreen.class);
        startActivity(intent1);
    }
}
