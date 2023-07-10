package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class DecoreEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decore_events);
        GridLayout layout = (GridLayout) findViewById(R.id.decore_event_layout);
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
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.classicaldecore);
                        j.putExtra("set_name","Classical Decore Set");
                        j.putExtra("set_code","CDS15");
                        j.putExtra("dis","Classical Decore Set is available with glorious decoration components with classical vibes.It includes pair of celebration chairs and other lightning and designing decorative elements ");
                        j.putExtra("price","15000 Rs.");
                        startActivity(j);


                    } else if (finalIntI == 1) {
                        Intent j = new Intent(DecoreEvents.this,DecoreEventByIntent.class );
                        j.putExtra("resId", R.drawable.youth_sensational_decore);
                        j.putExtra("set_name","Youth Choiced Decore Set");
                        j.putExtra("set_code","YCDS20");
                        j.putExtra("dis","These Youth Choiced Decore Set is all based on the youth prefrenses ,including all the classical , simplisity vibes . It includes all the lightning and designing decorative elements which all makes your event memorable.");
                        j.putExtra("price","20000 Rs.");
                        startActivity(j);

                    } else if (finalIntI == 2) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.premiumdecore);
                        j.putExtra("set_name","Premium Decore Set");
                        j.putExtra("set_code","PDS25");
                        j.putExtra("dis","These Premium Decore set is all good to decore your event with the premium Receptions Vibes.");
                        j.putExtra("price","25000 Rs.");
                        startActivity(j);


                    } else if (finalIntI == 3) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.royaldecore);
                        j.putExtra("set_name","Royal Decore Set");
                        j.putExtra("set_code","RDS30");
                        j.putExtra("dis","These Royal  Decore set is all good to decore your event with the Royal Reception Vibes.");
                        j.putExtra("price","30000 Rs.");
                        startActivity(j);


                    } else if (finalIntI == 4) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.stunningdecore);
                        j.putExtra("set_name","Stunning Decore Set ");
                        j.putExtra("set_code","SDS35");
                        j.putExtra("dis","These Stunning Decore set is all good to decore your event with the Stunning Looks  Receptions Vibes. ");
                        j.putExtra("price","35000 Rs.");
                        startActivity(j);


                    } else if (finalIntI == 5) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.premiumlightedroyaldecore);
                        j.putExtra("set_name","Premium Lighted Royal Decore Set");
                        j.putExtra("set_code","PLRDS50");
                        j.putExtra("dis","These Premium Lighted Royal  Decore set is all best to decore your event with the Stuuning royal vibes with the mindblowing sights. ");
                        j.putExtra("price","50000 Rs.");
                        startActivity(j);


                    }
                }
            });

        }
    }
}