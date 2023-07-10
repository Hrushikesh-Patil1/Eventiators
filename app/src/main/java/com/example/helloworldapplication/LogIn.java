package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {


    EditText EditMail;
    EditText EditPass;
    Button BtLogin;
    Button BtNew;
    ProgressBar bar1;
    TextView Forgot;
    CheckBox show_password;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        EditMail=findViewById(R.id.editMail);
        EditPass=findViewById(R.id.editPass);
        BtLogin=findViewById(R.id.btLogin);
        BtNew=findViewById(R.id.btNew);
        bar1=findViewById(R.id.loginBar);
        Forgot=findViewById(R.id.forgot);

        bar1.setVisibility(View.INVISIBLE);
        show_password=findViewById(R.id.check_box_show_password);

        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    EditPass.setTransformationMethod(null);
                }
                else
                {
                    EditPass.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });






        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LogIn.this, Reset.class);
                startActivity(i);
            }
        });


        BtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LogIn.this, NewReg.class);
                startActivity(i);
            }
        });
        BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String checkMail=EditMail.getText().toString();
               String checkPass=EditPass.getText().toString();
               firebaseAuth=FirebaseAuth.getInstance();
               if(checkMail.isEmpty()){
                   EditMail.setError("Email Required");
                   EditMail.requestFocus();
               }
              else if(checkPass.isEmpty()){
                    EditPass.setError("Password Required");
                    EditPass.requestFocus();
                }
              else if (!checkMail.isEmpty() && !checkPass.isEmpty()) {
                   bar1.setVisibility(View.VISIBLE);
                   if (checkMail.equals("admin@gmail.com")&&checkPass.equals("admin@gmail.com")) {
                       Toast.makeText(LogIn.this, "Admin Logged-In", Toast.LENGTH_SHORT).show();
                       bar1.setVisibility(View.GONE);
                       Intent i = new Intent(LogIn.this, AdminNewHome.class);
                       startActivity(i);

                   } else {
                       firebaseAuth.signInWithEmailAndPassword(checkMail, checkPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   Toast.makeText(LogIn.this, "Successfully Logged-In", Toast.LENGTH_SHORT).show();
                                   bar1.setVisibility(View.GONE);
                                   Intent i = new Intent(LogIn.this, nav_act_home.class);
                                   startActivity(i);
                               } else {
                                   bar1.setVisibility(View.GONE);
                                   Toast.makeText(LogIn.this, "Incorrect Email Or Password !!", Toast.LENGTH_SHORT).show();

                               }
                           }
                       });
                   }
               }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EditMail.setText("");
        EditPass.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditMail.setText("");
        EditPass.setText("");
    }
}