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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView tView;
    EditText inputEmail, inputPasswd;
    Button button;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.textView4);
        text.setMovementMethod(LinkMovementMethod.getInstance());

        tView = findViewById(R.id.register);
        inputEmail = findViewById(R.id.editText2);
        inputPasswd = findViewById(R.id.editText3);
        button = findViewById(R.id.button);
        fAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String passwd = inputPasswd.getText().toString().trim();

                if(email.isEmpty()) {
                    inputEmail.setError("Email is required!");
                    inputEmail.requestFocus();
                }
                else if(passwd.isEmpty()) {
                    inputPasswd.setError("Password is required");
                    inputPasswd.requestFocus();
                }
                else {
                    fAuth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                openCustomerPage();
                            }
                            else
                                Toast.makeText(MainActivity.this, "Email or Password Incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterPage();
            }
        });
    }

    public void openRegisterPage(){
        Intent i = new Intent(MainActivity.this, RegisterPage.class);
        startActivity(i);
    }

    public void openCustomerPage(){
        Intent i1 = new Intent(MainActivity.this, CustomerPage.class);
        startActivity(i1);
    }
}
