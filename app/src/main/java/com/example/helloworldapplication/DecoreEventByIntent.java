package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.helloworldapplication.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class DecoreEventByIntent extends AppCompatActivity {
    ImageView imageView;
    String check_for_time = "1 Day";
    TextView set_name_tx,price,dis_tx,code,tv;
    TextView txt_account_details,txt_amount;
    int multiplication_value;
    String amount="";

    Button order_set_button,b1;
    EditText edit_add;
    FirebaseDatabase db;
    FirebaseAuth auth;
    ProgressBar bar;
    DatePicker dp;
    String string_date="" ;
    Button paynow;
    String payment_status="";
    final int UPI_PAYMENT = 0;
    String global_price;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decore_event_by_intent);

        txt_account_details=findViewById(R.id.account_details);
        txt_amount=findViewById(R.id.bill_amount);

        txt_amount.setVisibility(View.INVISIBLE);
        txt_account_details.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.imageinintent);
        set_name_tx=findViewById(R.id.set_name);
        price=findViewById(R.id.set_Price);
        code=findViewById(R.id.set_code);
        dis_tx=findViewById(R.id.set_dis);
        edit_add=findViewById(R.id.editText_for_add);

        bar=findViewById(R.id.progressBar_decore);
        paynow=findViewById(R.id.pay_now_decoration);
        paynow.setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
        String set_price = getIntent().getExtras().getString("price");
        global_price=set_price;

        price.setText("Price Charge :\t" + set_price);




        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price_string=set_price.substring(0,5);
                int price_convert=Integer.parseInt(price_string);

                double final_price=price_convert * multiplication_value;
                //int int_amount=(int)final_price-599;
                int int_amount=(int)final_price;

                amount=String.valueOf(int_amount);
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
                    Toast.makeText(DecoreEventByIntent.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
                }


            }
        });

        bar.setVisibility(View.INVISIBLE);

        int resId = bundle.getInt("resId");
        imageView.setImageResource(resId);


        Spinner dropdown = findViewById(R.id.spinner1);

        String[] items = new String[]{"1 Day", "2 Days", "3 Days"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                       check_for_time="1 Day";
                       multiplication_value=1;
                        break;
                    case 1:
                       check_for_time="2 Days";
                       multiplication_value=2;
                        break;
                    case 2:
                     check_for_time="3 Days";
                     multiplication_value=3;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        String set_name = getIntent().getExtras().getString("set_name");
        set_name_tx.setText(set_name);



        String set_disp = getIntent().getExtras().getString("dis");
        dis_tx.setText("Discrpition :\n" + set_disp);

        String set_code=getIntent().getExtras().getString("set_code");
        code.setText("Set Code :\t"+ set_code);





        dp = (DatePicker) findViewById(R.id.datePicker);
        b1 = (Button) findViewById(R.id.button_to_display_date);
        tv = (TextView) findViewById(R.id.textview_for_date_text);
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














        order_set_button=findViewById(R.id.button_for_order_decore_set);

        order_set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long time=System.currentTimeMillis();
                String time_string = time+"";

                String string_for_event_add=edit_add.getText().toString();

                if(string_for_event_add.isEmpty())
                {
                    edit_add.setError("Enter Full Address");
                    edit_add.requestFocus();
                }
                else if(string_date.isEmpty())
                {
                    Toast.makeText(DecoreEventByIntent.this, "Select Date OF Event", Toast.LENGTH_SHORT).show();
                }
                else if(payment_status.isEmpty()){
                    Toast.makeText(DecoreEventByIntent.this, "Payment Is Yet To Do", Toast.LENGTH_SHORT).show();
                }
                else if(!string_for_event_add.isEmpty() && !string_date.isEmpty()) {


                    AlertDialog.Builder builder=new AlertDialog.Builder(DecoreEventByIntent.this);
                    builder.setTitle("Order Confirmation");
                    builder.setMessage("Do you want to confirm your order ?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes,Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            bar.setVisibility(View.VISIBLE);

                            String status="Submitted ...Waiting To Accpeted ";

                            Decore_Set_Order order = new Decore_Set_Order(set_code, set_name, string_date, set_price, string_for_event_add,status,time_string,check_for_time,payment_status,FirebaseAuth.getInstance().getCurrentUser().getUid());
                            String path = "Decoration_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid();
                            db.getInstance().getReference(path).child(String.valueOf(time)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        bar.setVisibility(View.GONE);
                                     //   Toast.makeText(DecoreEventByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();




                                        AlertDialog.Builder builder_new=new AlertDialog.Builder(DecoreEventByIntent.this);
                                        builder_new.setTitle("Order Execution");
                                        builder_new.setMessage("Your Order has been placed successfully.\nYou can check your order status from my order section.\nOnce your order get accepted our team will contact you soon.\n.You can pay at time of service delievery\nThank you for ordering !");
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
                                        bar.setVisibility(View.GONE);
                                        Toast.makeText(DecoreEventByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

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
                    check_for_time = "1 Day";

                break;
            case R.id.radio_2:
                if (checked)
                    check_for_time = "2 Days";
                break;
            case R.id.radio_3:
                if (checked)
                    check_for_time = "3 Days";
                break;
            case R.id.pay_on_delievery_decoration:
                if (checked)
                    payment_status="On Delievery";
                txt_account_details.setVisibility(View.INVISIBLE);
                txt_amount.setVisibility(View.INVISIBLE);
                paynow.setVisibility(View.INVISIBLE);

                break;

            case R.id.pay_upi_decoration:
                if (checked)
                    paynow.setVisibility(View.VISIBLE);
                txt_account_details.setVisibility(View.INVISIBLE);
                txt_amount.setVisibility(View.INVISIBLE);

                break;
            case R.id.pay_on_credentials_decoration:
                if (checked)
                    txt_account_details.setVisibility(View.VISIBLE);
                    txt_amount.setVisibility(View.VISIBLE);
                    paynow.setVisibility(View.INVISIBLE);
                    payment_status="To Admin Credential";
                    String  price_string=global_price.substring(0,5);
                        int price_convert=Integer.parseInt(price_string);

                    double final_price=price_convert * multiplication_value;
                //int int_amount=(int)final_price-599;
                    int int_amount=(int)final_price;

                     amount=String.valueOf(int_amount);
                txt_amount.setText("Your Bill Amount :\t"+amount);
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



        if (isConnectionAvailable(DecoreEventByIntent.this)) {
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
                Toast.makeText(DecoreEventByIntent.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                payment_status="Paid";
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(DecoreEventByIntent.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(DecoreEventByIntent.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(DecoreEventByIntent.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
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