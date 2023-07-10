package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class AppointPhotographer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_photographer);





        GridLayout layout = (GridLayout) findViewById(R.id.appoint_photographer_gridlayout);
        setsingleEvent(layout);
    }

    private void setsingleEvent(GridLayout layout) {

        for (int i = 0; i < layout.getChildCount(); i++) {

            CardView cv = (CardView) layout.getChildAt(i);
            final int finalIntI = i;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalIntI == 0) {
                        Intent j = new Intent(AppointPhotographer.this, photographerByIntent.class);
                        j.putExtra("resId", R.drawable.weddingphotographer);
                        j.putExtra("photographer_type","Wedding Photographer");
                        j.putExtra("photographer_code","WP50");
                        j.putExtra("dis","Wedding Photographer discription box");
                        j.putExtra("price","50000 Rs/Day");

                        j.putExtra("icludes","Physical And Digital Prints Of Photos / video With Full Editing");
                        startActivity(j);

                    } else if (finalIntI == 1) {
                        Intent j = new Intent(AppointPhotographer.this, photographerByIntent.class);
                        j.putExtra("resId", R.drawable.eventphotographer);
                        j.putExtra("photographer_type","Event / Gathering Photographer");
                        j.putExtra("photographer_code","EGP20");
                        j.putExtra("dis","Event Photographer discription box");
                        j.putExtra("price","20000 Rs/Day");

                        j.putExtra("icludes","Digital Prints Of Photos / video");
                        startActivity(j);
                    }
                    else if (finalIntI == 2) {
                        Intent j = new Intent(AppointPhotographer.this, photographerByIntent.class);
                        j.putExtra("resId", R.drawable.generalphotographer);
                        j.putExtra("photographer_type","General Photographer");
                        j.putExtra("photographer_code","GP2");
                        j.putExtra("dis","General Photographer discription box");
                        j.putExtra("price","2000 Rs/hour");

                        j.putExtra("icludes","Digital Prints Of Photos / video");
                        startActivity(j);
                    }
                    else if (finalIntI == 3) {
                        Intent j = new Intent(AppointPhotographer.this, photographerByIntent.class);
                        j.putExtra("resId", R.drawable.fashionphotographer);
                        j.putExtra("photographer_type","Fashion Photographer");
                        j.putExtra("photographer_code","FP5");
                        j.putExtra("dis","Fashion Photographer discription box");
                        j.putExtra("price","5000 Rs/Hour");

                        j.putExtra("icludes","Physical And Digital Prints Of Complete Edited Photos / video");
                        startActivity(j);
                    }
                    else if (finalIntI == 4) {
                        Intent j = new Intent(AppointPhotographer.this, photographerByIntent.class);
                        j.putExtra("resId", R.drawable.formalphotographer);
                        j.putExtra("photographer_type","Formal Photographer");
                        j.putExtra("photographer_code","FP10");
                        j.putExtra("dis","Formal Photographer discription box");
                        j.putExtra("price","10000 Rs/Day");

                        j.putExtra("icludes","Physical And Digital Prints Of Photos / video");
                        startActivity(j);
                    }
                }
            });

        }

    }
}