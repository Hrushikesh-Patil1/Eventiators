package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class birthdayinvitation extends AppCompatActivity {
    EditText to,birthday,at;
    DatePicker dp;
    TimePicker tp;
    TextView txt_date;
    Button get_date,get_time,get_card;
    String s_to="";
    String s_birtday="";

    String s_at="";
    String s_date="";

    String s_time="";
    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdayinvitation);

        to=findViewById(R.id.to);
        birthday=findViewById(R.id.of);
        at=findViewById(R.id.at);

        dp=findViewById(R.id.birthdaydatepicke);
        timePicker1=findViewById(R.id.birthdaytimepicke);

        txt_date=findViewById(R.id.birthday_textView_Date);
        time=findViewById(R.id.birthday_textView_time);

        get_date=findViewById(R.id.birthday_button_date);

        get_card=findViewById(R.id.grab_birthday);
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        dp.setMinDate(now);


        calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);


        get_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer sb = new StringBuffer();
                sb.append(dp.getDayOfMonth() + "/");
                sb.append((dp.getMonth() + 1) + "/");
                sb.append(dp.getYear());

                txt_date.setText(sb.toString());

                s_date=sb.toString();

            }
        });


        get_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                s_to=to.getText().toString();
                s_birtday=birthday.getText().toString();
                s_at=at.getText().toString();
                s_date=txt_date.getText().toString();
                s_time=time.getText().toString();

                if(s_to.isEmpty()){
                    to.setError("Enter Name To Whom You Are Writing Invitation Card");
                    to.requestFocus();
                }
                else if(s_birtday.isEmpty()){
                    birthday.setError("Enter Name Of Whom Birthday Party Is");
                    birthday.requestFocus();
                }
                else if(s_at.isEmpty()){
                    at.setError("Enter Address Of Your Party");
                    at.requestFocus();
                }
                else if(s_time.isEmpty())
                {
                    Toast.makeText(birthdayinvitation.this, "Select Time ", Toast.LENGTH_SHORT).show();
                }
                else if(s_date.isEmpty()){
                    Toast.makeText(birthdayinvitation.this, "Select Date", Toast.LENGTH_SHORT).show();
                }
                else{
                   Intent i=new Intent(birthdayinvitation.this,BirthdayByIntent.class);

                    i.putExtra("to",s_to);
                    i.putExtra("birthday",s_birtday);
                    i.putExtra("add",s_at);
                    i.putExtra("date",s_date);
                    i.putExtra("time",s_time);
                    startActivity(i);

                }


            }
        });




    }

    public void setTime(View view) {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }
}