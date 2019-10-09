package com.n01216688.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCustomerPage();
            }
        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context2 = getApplicationContext();
                CharSequence text2 = "YOU SIGNED UP!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast2 = Toast.makeText(context2, text2, duration);
                toast2.show();
            }
        });
    }

    public void openCustomerPage() {
        Intent intent = new Intent(this, CustomerPage.class);
        startActivity(intent);
    }
}
