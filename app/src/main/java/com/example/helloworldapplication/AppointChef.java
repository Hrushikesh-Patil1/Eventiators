package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppointChef extends AppCompatActivity {
    TextView no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_chef);
        GridLayout layout = (GridLayout) findViewById(R.id.gridlayout_catering_service);
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
                        Intent j = new Intent(AppointChef.this, CateringByIntent.class);
                        int intvalue=100;
                        j.putExtra("catering_type","Wedding Catering Service");
                        j.putExtra("extra_price",intvalue);

                        startActivity(j);


                    } else if (finalIntI == 1) {
                        Intent j = new Intent(AppointChef.this,CateringByIntent.class );
                        j.putExtra("catering_type","Corporate Catering Service");
                        j.putExtra("extra_price",70);

                        startActivity(j);


                    }
                    else if (finalIntI ==2) {
                        Intent j = new Intent(AppointChef.this,CateringByIntent.class );
                        j.putExtra("catering_type","Buffet Vatering Service");
                        j.putExtra("extra_price",70);

                        startActivity(j);


                    }
                    else if (finalIntI == 3) {
                        Intent j = new Intent(AppointChef.this,CateringByIntent.class );
                        j.putExtra("catering_type","Food Truck Catering");
                        j.putExtra("extra_price",75);

                        startActivity(j);

                    }
                }
            });

        }
    }
}