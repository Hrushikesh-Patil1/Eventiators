package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminNewHome extends AppCompatActivity {

        Button view_user_list,view_details_with_orders,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_home);

        view_user_list=findViewById(R.id.view_user_list);
        view_details_with_orders=findViewById(R.id.view_user_list_with_details_and_orders);
        logout=findViewById(R.id.admin_log_out);



        view_user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminNewHome.this,AdminUserList.class);
                startActivity(i);
            }
        });

        view_details_with_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminNewHome.this,admin_home.class);
                startActivity(i);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog alertbox = new AlertDialog.Builder(AdminNewHome.this)
                        .setMessage("Admin , Do you want to LogOut?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {

                                finish();



                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface arg0, int arg1) {

                            }


                        })
                        .show();




            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Admin , Do you want to LogOut?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();



                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface arg0, int arg1) {

                    }


                })
                .show();

    }
}