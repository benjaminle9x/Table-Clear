package com.n01216688.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity {
    TextView tView;
    EditText mEmail, mPasswd;
    Button mSignup;
    FirebaseAuth fAuth;
    ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().setTitle("Register Page");

        tView = (TextView)findViewById(R.id.backtologin);
        mEmail = findViewById(R.id.email);
        mPasswd = findViewById(R.id.password);
        mSignup = findViewById(R.id.singup);
        fAuth = FirebaseAuth.getInstance();
        pBar = findViewById(R.id.progressBar2);

        if(fAuth.getCurrentUser() != null) {
            openMainActivity();
            finish();
        }

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String passwd = mPasswd.getText().toString().trim();

                if(email.isEmpty()) {
                    mEmail.setError("Email is required!");
                    mEmail.requestFocus();
                }
                else if(passwd.isEmpty()) {
                    mPasswd.setError("Password is required");
                    mPasswd.requestFocus();
                }
                else if(passwd.length() < 6) {
                    mPasswd.setError("Password must contain at least 6 characters");
                    mPasswd.requestFocus();
                }
                else {
                    pBar.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegisterPage.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                openModifyProfile();
                            }
                            else
                                Toast.makeText(RegisterPage.this, "Account is not created! Please refresh the page", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent i = new Intent(RegisterPage.this,MainActivity.class);
        startActivity(i);
    }

    public void openModifyProfile() {
        Intent i2 = new Intent(RegisterPage.this, ModifyProfile.class);
        startActivity(i2);
    }
}
