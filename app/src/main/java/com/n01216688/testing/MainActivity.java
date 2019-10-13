package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.textView4);
        text.setMovementMethod(LinkMovementMethod.getInstance());

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCustomerPage();
            }
        });

        tView = (TextView)findViewById(R.id.register);
        tView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1){
                openRegisterPage();
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                openHomePage();
                return true;
            case R.id.setting:
                openSettingPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openCustomerPage() {
        Intent intent = new Intent(this, CustomerPage.class);
        startActivity(intent);
    }

    public void openSettingPage(){
        Intent intent1 = new Intent(this,SettingScreen.class);
        startActivity(intent1);
    }

    public void openHomePage(){
        Intent intent2 = new Intent(this,HomePage.class);
        startActivity(intent2);
    }

    public void openRegisterPage(){
        Intent intent3 = new Intent(this,RegisterPage.class);
        startActivity(intent3);
    }
}
