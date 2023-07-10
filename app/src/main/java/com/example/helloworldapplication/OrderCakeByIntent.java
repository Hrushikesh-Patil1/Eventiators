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
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrderCakeByIntent extends AppCompatActivity {
    ImageView imageView_cake_view;
    TextView cake_name_tx,cake_price_tx,cake_dis_tx,cake_code_tx,tv;
    Button paynow;

    int multiplication_value=1;
    final int UPI_PAYMENT = 0;
    Button order_cake_button,b1;
    DatePicker dp;
    EditText ed_cake_add,ed_cake_message;
    FirebaseDatabase db;
    FirebaseAuth auth;
    ProgressBar bar;
    String check_for_quant="0.5";
    String string_date="";
    String payment_status="";
    String cake_price;
    TextView tv_amount_bill,tv_account_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cake_by_intent);

        imageView_cake_view=findViewById(R.id.imageinintent_cake);

        ed_cake_add=findViewById(R.id.editText_for_add_cake);

        ed_cake_message=findViewById(R.id.edittext_for_cake_message);


        cake_name_tx=findViewById(R.id.cake_name);
        cake_price_tx=findViewById(R.id.cake_Price);
        cake_dis_tx=findViewById(R.id.cake_dis);
        cake_code_tx=findViewById(R.id.cake_id);
        paynow=findViewById(R.id.pay_now);
        paynow.setVisibility(View.INVISIBLE);



        tv_account_details=findViewById(R.id.account_details_cake);
        tv_account_details.setVisibility(View.INVISIBLE);

        tv_amount_bill=findViewById(R.id.bill_amount_cake);
        tv_amount_bill.setVisibility(View.INVISIBLE);





        order_cake_button =findViewById(R.id.button_for_order_cake);

        bar=findViewById(R.id.progressBar_cake);
        bar.setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();

        int resId = bundle.getInt("resId");
        imageView_cake_view.setImageResource(resId);


        String cake_name = getIntent().getExtras().getString("cake_name");
        cake_name_tx.setText(cake_name);


        cake_price = getIntent().getExtras().getString("cake_price");
        cake_price_tx.setText("Price Charge :\t" + cake_price);


        String cake_code=getIntent().getExtras().getString("cake_code");
        cake_code_tx.setText("Set Code :\t"+ cake_code);

        String cake_disp=getIntent().getExtras().getString("cake_dis");
        cake_dis_tx.setText("Description Box  :\n"+ cake_disp);



        dp = (DatePicker) findViewById(R.id.datePickercake);
        b1 = (Button) findViewById(R.id.button_to_display_date_cake);
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


        order_cake_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long time=System.currentTimeMillis();
                String time_string = time+"";

                String string_for_event_add=ed_cake_add.getText().toString();

                String string_for_message=ed_cake_message.getText().toString();
















                if(string_for_event_add.isEmpty())
                {
                    ed_cake_add.setError("Enter Full Address");
                    ed_cake_add.requestFocus();
                }
                else if(string_date.isEmpty()){
                    Toast.makeText(OrderCakeByIntent.this, "Select Date ", Toast.LENGTH_SHORT).show();
                }
                else if(payment_status.isEmpty()){
                    Toast.makeText(OrderCakeByIntent.this, "Your Payment Is Not Done Yet ...You Can Choose Pay On Delievery Option If Any Issue", Toast.LENGTH_SHORT).show();
                }

                else if(!string_for_event_add.isEmpty() && !string_date.isEmpty()&&!payment_status.isEmpty()) {



                    AlertDialog.Builder builder=new AlertDialog.Builder(OrderCakeByIntent.this);
                    builder.setTitle("Order Confirmation");
                    builder.setMessage("Do you want to confirm your order ?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes,Comfirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {







                            bar.setVisibility(View.VISIBLE);

                            String status="Submitted ...Waiting To Accpeted ";
                            CakeOrderDb order = new CakeOrderDb(cake_code,cake_name,string_for_message,check_for_quant,string_date,cake_price,string_for_event_add,status,time_string,FirebaseAuth.getInstance().getUid(),payment_status);


                            String path = "Cake_Order_Of_UserId___" + FirebaseAuth.getInstance().getCurrentUser().getUid() ;
                            db.getInstance().getReference(path).child(String.valueOf(time)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        bar.setVisibility(View.GONE);
                                        //Toast.makeText(OrderCakeByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();



                                        AlertDialog.Builder builder_new=new AlertDialog.Builder(OrderCakeByIntent.this);
                                        builder_new.setTitle("Order Execution");
                                        builder_new.setMessage("Your Order has been placed successfully.\nYou can check your order status from my order section.\nOnce your order get accepted ,order will be delievered to your address. \nYou can pay on deleivery\nThank you for ordering !");
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
                                        Toast.makeText(OrderCakeByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

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
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String transcId = df.format(c);

       paynow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


                String price_pay=cake_price.substring(0,3);

                int price_convert=Integer.parseInt(price_pay);

                double final_price=price_convert * multiplication_value;
                int int_amount=(int)final_price;

             String amount=String.valueOf(int_amount);

               String note = "Cake Order Payment Of User -"+FirebaseAuth.getInstance().getCurrentUser().getUid();
               String name = "Lokesh Patil";
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
                       Toast.makeText(OrderCakeByIntent.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
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
                    check_for_quant = "0.5";
                multiplication_value=1;

                break;
            case R.id.radio_2:
                if (checked)
                    check_for_quant = "1";
                multiplication_value=2;
                break;
            case R.id.radio_3:
                if (checked)
                    check_for_quant = "2";
                multiplication_value=4;
                break;

            case R.id.pay_on_delievery:
                if (checked)
                  payment_status="On Delievery";
                paynow.setVisibility(View.INVISIBLE);
                tv_amount_bill.setVisibility(View.INVISIBLE);
                tv_account_details.setVisibility(View.INVISIBLE);

                break;

            case R.id.pay_upi:
                if (checked)
                paynow.setVisibility(View.VISIBLE);
                tv_amount_bill.setVisibility(View.INVISIBLE);
                tv_account_details.setVisibility(View.INVISIBLE);
                break;
            case R.id.pay_on_credentials_cake:
                if (checked)
                    paynow.setVisibility(View.INVISIBLE);
                tv_amount_bill.setVisibility(View.VISIBLE);
                tv_account_details.setVisibility(View.VISIBLE);
                payment_status="Pay To Admin Credential";

                String price_pay=cake_price.substring(0,3);

                int price_convert=Integer.parseInt(price_pay);

                double final_price=price_convert * multiplication_value;
                int int_amount=(int)final_price;

                String amount=String.valueOf(int_amount);

                tv_amount_bill.setText("Bill amount :\t"+amount);

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



        if (isConnectionAvailable(OrderCakeByIntent.this)) {
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
                Toast.makeText(OrderCakeByIntent.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                payment_status="Paid";
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(OrderCakeByIntent.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OrderCakeByIntent.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(OrderCakeByIntent.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
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