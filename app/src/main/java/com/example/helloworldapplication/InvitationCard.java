package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InvitationCard extends AppCompatActivity {


    Button wedding,anniversary,birthday,engagament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_card);

        wedding=findViewById(R.id.wedding_invitation);
        anniversary=findViewById(R.id.anniversary_invitation);
        birthday=findViewById(R.id.birthday_invitation);
        engagament=findViewById(R.id.engagament_invitation);


        wedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(InvitationCard.this,weddinginvitation.class);
                startActivity(i);
            }
        });


        anniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(InvitationCard.this,anniversaryinvitation.class);
                startActivity(i);
            }
        });


        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(InvitationCard.this,birthdayinvitation.class);
                startActivity(i);
            }
        });


        engagament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(InvitationCard.this,engagementinvitation.class);
                startActivity(i);
            }
        });

    }
}