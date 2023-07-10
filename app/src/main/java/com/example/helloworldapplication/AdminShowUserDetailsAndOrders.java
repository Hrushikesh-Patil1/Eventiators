package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminShowUserDetailsAndOrders extends AppCompatActivity {


    TextView tv_uname,tv_uid,tv_uemail,tv_uphone,tv_uadd;
    Button decoration_new,catering_new,cake_new,photographer_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_user_details_and_orders);

        tv_uname=findViewById(R.id.tvNameAdmin_new);
        tv_uid=findViewById(R.id.tvUidAdmin_new);
        tv_uemail=findViewById(R.id.tvEmailAdmin_new);
        tv_uphone=findViewById(R.id.tvPhoneAdmin_new);
        tv_uadd=findViewById(R.id.tvaddressAdmin_new);



        decoration_new=findViewById(R.id.admin_view_decoration_new);
        catering_new=findViewById(R.id.admin_view_catering_new);
        cake_new=findViewById(R.id.admin_view_cake_new);
        photographer_new=findViewById(R.id.admin_view_photo_new);




        tv_uid.setText(getIntent().getExtras().getString("uid"));
        tv_uname.setText(getIntent().getExtras().getString("uname"));
        tv_uphone.setText(getIntent().getExtras().getString("uphone"));
        tv_uadd.setText(getIntent().getExtras().getString("uadd"));
        tv_uemail.setText(getIntent().getExtras().getString("mail"));


        decoration_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid=tv_uid.getText().toString();
                Intent i=new Intent(AdminShowUserDetailsAndOrders.this,AdminDecoration.class);
                i.putExtra("uid",uid);
                startActivity(i);


            }
        });

            photographer_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String uid=tv_uid.getText().toString();
                Intent i=new Intent(AdminShowUserDetailsAndOrders.this,AdminViewPhotographer.class);
                i.putExtra("uid",uid);
                startActivity(i);

            }
        });


        cake_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String uid=tv_uid.getText().toString();
                Intent i=new Intent(AdminShowUserDetailsAndOrders.this,AdminShowCakeOrders.class);
                i.putExtra("uid",uid);
                startActivity(i);


            }
        });


        catering_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String uid=tv_uid.getText().toString();
                Intent i=new Intent(AdminShowUserDetailsAndOrders.this,AdminViewCatering.class);
                i.putExtra("uid",uid);
                startActivity(i);


            }
        });










    }
}