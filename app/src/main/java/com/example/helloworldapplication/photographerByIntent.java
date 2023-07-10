package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.Calendar;

import static android.widget.Toast.*;

public class photographerByIntent extends AppCompatActivity {

    String check_for_time = "1 Day/Hour";
    DatePicker dp;
    Button b1, appoint_photographer;
    TextView tv, photographer_type, photographer_code, photographer_price, photographer_dis, photographer_includes;
    EditText event_address;
    TextView account_details,amount_bill;
    ImageView imageView;
    ProgressBar pbar;
    String string_date="";
    final int UPI_PAYMENT = 0;
    Button paynow;
    int multiplication_value=1;
    String payment_status_photo="";
    long price_pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_by_intent);

        pbar = findViewById(R.id.progressBar_photographer);
        pbar.setVisibility(View.INVISIBLE);
        appoint_photographer = findViewById(R.id.button_for_appoint_photographer);
        event_address = findViewById(R.id.photographer_order_event_address);
        imageView = findViewById(R.id.photographerimageinintent);
        paynow=findViewById(R.id.pay_now_photo);
        paynow.setVisibility(View.INVISIBLE);


        amount_bill=findViewById(R.id.bill_amount_photo);
        account_details=findViewById(R.id.account_details_photo);

        amount_bill.setVisibility(View.INVISIBLE);
        account_details.setVisibility(View.INVISIBLE);


        photographer_type = findViewById(R.id.photographer_type);
        photographer_code = findViewById(R.id.photographer_code);
        photographer_price = findViewById(R.id.photographer_Price);

        photographer_includes = findViewById(R.id.photographer_includes);
        Bundle bundle = getIntent().getExtras();

        int resId = bundle.getInt("resId");
        imageView.setImageResource(resId);


        String String_photographer_type = getIntent().getExtras().getString("photographer_type");
        photographer_type.setText(String_photographer_type);

        String String_photographer_code = getIntent().getExtras().getString("photographer_code");
        photographer_code.setText("Photographer Code :\t" + String_photographer_code);




        String String_photographer_price = getIntent().getExtras().getString("price");
        photographer_price.setText("Photographer Price :\t" + String_photographer_price);


        String String_photographer_includes = getIntent().getExtras().getString("icludes");
        photographer_includes.setText("Package Includes :\t" + String_photographer_includes);




        Spinner dropdown = findViewById(R.id.spinner1);

        String[] items = new String[]{"1 Day/Hour", "2 Days/Hour", "3 Days/Hour"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        check_for_time="1";
                        multiplication_value=1;
                        break;
                    case 1:
                        check_for_time="2";
                        multiplication_value=2;
                        break;
                    case 2:
                        check_for_time="3";
                        multiplication_value=3;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        if(String_photographer_code.equals("WP50")){
            price_pay=50000;
        }
        else if(String_photographer_code.equals("EGP20")){
            price_pay=20000;
        }
        else if(String_photographer_code.equals("GP2")){
            price_pay=2000;
        }
        else if(String_photographer_code.equals("FP5")){
            price_pay=5000;
        }
        else if(String_photographer_code.equals("FP10")){
            price_pay=10000;
        }






        dp = (DatePicker) findViewById(R.id.datePicker);
        b1 = (Button) findViewById(R.id.button_to_display_date);
        tv = (TextView) findViewById(R.id.textview);


        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        dp.setMinDate(now);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                sb.append(dp.getDayOfMonth() + "/");
                sb.append((dp.getMonth() + 1) + "/");
                sb.append(dp.getYear());

                tv.setText(sb.toString());

                string_date=sb.toString();
            }
        });



        paynow=findViewById(R.id.pay_now_photo);
        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long int_amount=price_pay*multiplication_value;
               String amount=String.valueOf(int_amount);
               String note = "Photographer Booking Payment Of User -"+FirebaseAuth.getInstance().getCurrentUser().getUid();
                String name = FirebaseAuth.getInstance().getCurrentUser().getEmail();
               String upiId = "8999971354@ybl";
                payUsingUpi(amount, upiId, name, note);


            }

            private void payUsingUpi(String amount, String upiId, String name, String note) {


                Uri uri = Uri.parse("upi://pay").buildUpon()
                        .appendQueryParameter("pa", upiId)
                        .appendQueryParameter("pn", name)
                        .appendQueryParameter("tn", note)
                        .appendQueryParameter("am", amount)
                        .appendQueryParameter("cu", "INR")
                        .build();


                Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
                upiPayIntent.setData(uri);

                // will always show a dialog to user to choose an app
                Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

                // check if intent resolves
                if(null != chooser.resolveActivity(getPackageManager())) {
                    startActivityForResult(chooser,UPI_PAYMENT);
                } else {
                    Toast.makeText(photographerByIntent.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
                }
            }
        });


        appoint_photographer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long time=System.currentTimeMillis();
               // String time_string=Long.toString(time);
                String string_for_event_address = event_address.getText().toString();
                String time_string = time+"";
                if(string_for_event_address.isEmpty()){
                    event_address.setError("Address Required !");
                    event_address.requestFocus();
                }
                else if(string_date.isEmpty()){
                    makeText(photographerByIntent.this, "Select Date", LENGTH_SHORT).show();
                }
                else if(payment_status_photo.isEmpty()){
                    makeText(photographerByIntent.this, "Payment Is Not Done Yet...You can choose Pay On Service Option If ANy Issue..", LENGTH_SHORT).show();
                }
                else if(!string_for_event_address.isEmpty() && ! string_date.isEmpty()&&!payment_status_photo.isEmpty()) {




                    AlertDialog.Builder builder=new AlertDialog.Builder(photographerByIntent.this);
                    builder.setTitle("Order Confirmation");
                    builder.setMessage("Do you want to confirm your order ?");
                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes,Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {





                            pbar.setVisibility(View.VISIBLE);

                            String status="Submitted ...Waiting To Accpeted ";

                            AppointPhotographerDatabase order = new AppointPhotographerDatabase(String_photographer_type,String_photographer_code,String_photographer_price,check_for_time,string_date,string_for_event_address,status,String_photographer_includes,time_string,payment_status_photo,FirebaseAuth.getInstance().getCurrentUser().getUid());
                            String path = "Photographer_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseDatabase.getInstance().getReference(path).child(String.valueOf(time_string)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        pbar.setVisibility(View.GONE);
                                        //Toast.makeText(photographerByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();









                                        AlertDialog.Builder builder_new=new AlertDialog.Builder(photographerByIntent.this);
                                        builder_new.setTitle("Booking Execution");
                                        builder_new.setMessage("Your booking has been done successfully.\nYou can check your order status from my order section.\nOnce your order get accepted our team will contact you soon.\nYou can pay at time of service delievery\nThank you for booking !");
                                        builder.setCancelable(false);

                                        builder_new.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        });

                                        builder_new.create().show();



                                    } else {
                                        pbar.setVisibility(View.GONE);
                                        Toast.makeText(photographerByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });














                        }
                    });

                    builder.setNegativeButton("Recheck", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();















                }
            }
        });



    }





        public void onRadioButtonClicked (View  view) {


            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.radio_1:
                    if (checked)
                        check_for_time = "1";
                    multiplication_value=1;


                    break;
                case R.id.radio_2:
                    if (checked)
                        check_for_time = "2";
                    multiplication_value=2;
                    break;
                case R.id.radio_3:
                    if (checked)
                        check_for_time = "3";
                    multiplication_value=3;
                    break;
                case R.id.pay_on_delievery_photo:
                    if (checked)
                        account_details.setVisibility(View.INVISIBLE);
                    amount_bill.setVisibility(View.INVISIBLE);
                        payment_status_photo="On Delievery";
                    paynow.setVisibility(View.INVISIBLE);
                    break;

                case R.id.pay_upi_photo:
                    if (checked)
                        paynow.setVisibility(View.VISIBLE);
                    account_details.setVisibility(View.INVISIBLE);
                    amount_bill.setVisibility(View.INVISIBLE);

                    break;
                case R.id.pay_on_credentials_photo:
                    if (checked)

                    {

                        long final_price=price_pay * multiplication_value;





                        account_details.setVisibility(View.VISIBLE);
                    amount_bill.setVisibility(View.VISIBLE);
                    paynow.setVisibility(View.INVISIBLE);
                    payment_status_photo="To Admin Credential";



                    amount_bill.setText("Your Bill Amount :\t"+final_price); }
                    break;


            }
        }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {



        if (isConnectionAvailable(photographerByIntent.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(photographerByIntent.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                payment_status_photo="Paid";
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(photographerByIntent.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(photographerByIntent.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(photographerByIntent.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }


}
