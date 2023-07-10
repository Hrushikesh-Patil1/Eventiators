package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BirthdayByIntent extends AppCompatActivity {

    TextView t_to,t_birthday,t_date,t_time,t_at;
    Button btsave;
    LinearLayout content;
    OutputStream outputStream;
    ImageButton wp;
    ImageView imageresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_by_intent);


        content=findViewById(R.id.linear_id);
        String to=getIntent().getExtras().getString("to");
        String birthday=getIntent().getExtras().getString("birthday");
        String at=getIntent().getExtras().getString("add");
        String time=getIntent().getExtras().getString("time");
        String date=getIntent().getExtras().getString("date");

        t_to=findViewById(R.id.txtv_to);
        t_birthday=findViewById(R.id.txtv_birthday);
        t_at=findViewById(R.id.txtv_at);
        t_date=findViewById(R.id.txtv_date);
        t_time=findViewById(R.id.txtv_time);

        t_time.setText("Timing :\t"+time);
        t_birthday.setText(birthday);
        t_date.setText("On:\t"+date);
        t_to.setText("Dear  "+to+",\t");
        t_at.setText("Venue:\t"+at);



        btsave=findViewById(R.id.save_to_storage);
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageresult=findViewById(R.id.imageview_new);
                Bitmap image = getBitmapFromView(content);
                imageresult.setImageBitmap(image);
                imageresult.setVisibility(View.INVISIBLE);


                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageresult.getDrawable();


                Bitmap bitmap=bitmapDrawable.getBitmap();

                File filepath= Environment.getExternalStorageDirectory();

                File dir=new File(filepath.getAbsolutePath()+"/Invitation Cards NEW  : Eventiators/");

                dir.mkdir();

                File file=new File(dir,System.currentTimeMillis()+".jpg");

                try {
                    outputStream=new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                Toast.makeText(BirthdayByIntent.this, "Image Saved ", Toast.LENGTH_SHORT).show();

                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }




        });



    }

    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

}