package com.n01216688.testing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewPassword1 extends AppCompatActivity {

    EditText newPassword;
    FirebaseAuth auth;
    ProgressDialog dialog;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password1);
        dialog = new ProgressDialog(this);
        newPassword = (EditText)findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                change();
            }
        });
    }


    public void change(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            dialog.setMessage("Changing password, please wait");
            dialog.show();
            user.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(getApplicationContext(),"Your password has been changed",Toast.LENGTH_LONG);
                        dialog.dismiss();
                        auth.signOut();
                        finish();
                        Intent i = new Intent(NewPassword1.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Password could not be changed",Toast.LENGTH_LONG);
                    }
                }
            });

        }

    }
}

