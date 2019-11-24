package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomerPage extends AppCompatActivity {
    ViewPager viewPager;
    CustomerpageAdapter cpAdapter;
    Integer[] colors = null;
    List<Model> models;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private static final String TAG = "CustomerPage";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mScore = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        initImageBitmaps();

        models = new ArrayList<>();
        models.add(new Model(R.drawable.burger,"Burger"));
        models.add(new Model(R.drawable.pizza,"Pizza"));
        models.add(new Model(R.drawable.mexican,"Mexican"));
        models.add(new Model(R.drawable.vietnamese,"Vietnamese"));

        cpAdapter = new CustomerpageAdapter(models,this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(cpAdapter);
        viewPager.setPadding(40,0,40,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (cpAdapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer)argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getSupportActionBar().setTitle("Customer Page");

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
            case R.id.viewprofile:
                openCheckProfile();
                return true;
            case R.id.lout:
                onBackPressed();
                return true;
            case R.id.managerpage:
                openManagerPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        openMainActivity();
    }

    public void openManagerPage(){
        Intent i1 = new Intent(this,ManagerPage.class);
        startActivity(i1);
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

    public void openCheckProfile() {
        Intent i4 = new Intent(this, checkProfile.class);
        startActivity(i4);
    }

    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://s3-eu-central-1.amazonaws.com/centaur-wp/designweek/prod/content/uploads/2016/08/09165704/new-subway%C2%AE-retaurants-logo-5-HR.jpg");
        mNames.add("Subway");
        mScore.add("7.8");

        mImageUrls.add("data:image/png;base64,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");
        mNames.add("Pizza Hut");
        mScore.add("8.1");

        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/1200px-Burger_King_Logo.svg.png");
        mNames.add("Burger King");
        mScore.add("7.9");

        mImageUrls.add("https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Starbucks_Corporation_Logo_2011.svg/1200px-Starbucks_Corporation_Logo_2011.svg.png");
        mNames.add("Starbucks");
        mScore.add("8.3");

        mImageUrls.add("https://upload.wikimedia.org/wikipedia/en/thumb/7/76/Mr._Sub_logo.svg/1280px-Mr._Sub_logo.svg.png");
        mNames.add("Mr.Sub");
        mScore.add("8.3");

        mImageUrls.add("https://pbs.twimg.com/profile_images/1143522262546616320/LXNN8Owe_400x400.png");
        mNames.add("Pizza Pizza");
        mScore.add("8.0");

        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvF3VCiIt6eM_GpUYM2A7wI2yliG7EJdx8B-xSYr0LbT1bSm8A&s");
        mNames.add("Yes Hue");
        mScore.add("9.0");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recview);
        RestaurantlistAdapter adapter = new RestaurantlistAdapter(this,mNames,mScore,mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
