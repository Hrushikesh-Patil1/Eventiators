package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {

    EditText ResetMail;

    Button BtReset;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        ResetMail=findViewById(R.id.resetMail);
        BtReset=findViewById(R.id.resetBt);

        BtReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail_string = ResetMail.getText().toString();

                if (mail_string.isEmpty()) {
                    ResetMail.setError("Enter the Registered Email Address Here ");
                } else if (!mail_string.isEmpty()){


                    mAuth = FirebaseAuth.getInstance();
                mAuth.sendPasswordResetEmail(mail_string).addOnCompleteListener(new OnCompleteListener() {
                    @Override

                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Reset.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(Reset.this, "Invalid Email Id Entered ..!!!", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
            }
        });
    }
}