package com.example.helloworldapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapterCakeOrder extends RecyclerView.Adapter<MyAdapterCakeOrder.MyViewHolder2> {

    Context context;

    ArrayList<CakeOrderDb> list;


    public MyAdapterCakeOrder(Context context, ArrayList<CakeOrderDb> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cake_item,parent,false);
        return  new MyViewHolder2(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        CakeOrderDb order = list.get(position);
        holder.tv_cake_id1.setText(order.getCake_code());
        holder.tv_cake_add1.setText(order.getCake_add());
        holder.tv_cake_date1.setText(order.getCake_date());
        holder.tv_cake_mseg.setText(order.getCake_msg_print());
        holder.tv_cake_name1.setText(order.getCake_name());
        holder.tv_cake_price1.setText(order.getCake_price());
        holder.tv_cake_status1.setText(order.getCake_status());
        holder.tv_cake_quant.setText(order.getCake_quantity());
        holder.payment_status.setText(order.getPayment_status());

        holder.tv_cake_order_id.setText(order.getOrder_id());


        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                String order_id=holder.tv_cake_order_id.getText().toString();

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Order Updation");
                builder.setMessage("Do you really want to cancel these order ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes,Cancel order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference ref;
                        ref= FirebaseDatabase.getInstance().getReference("Cake_Order_Of_UserId___" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child(order_id);
                        ref.setValue(null);


                        AlertDialog.Builder builder2=new AlertDialog.Builder(context);
                        builder2.setTitle("Order Cancellation");
                        builder2.setMessage("Your Order Has Been Cancelled  Successfully !");
                        builder2.setCancelable(false);
                        builder2.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                               dialog.dismiss();



                            }
                        });
                        builder2.create().show();




                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                    }
                });
                builder.create().show();
            }
        });













    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView tv_cake_id1,tv_cake_name1,tv_cake_price1,tv_cake_add1,tv_cake_status1,tv_cake_date1,tv_cake_quant,tv_cake_mseg,tv_cake_order_id,payment_status;
        Button cancel;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

          tv_cake_id1=itemView.findViewById(R.id.tv_order_cake_id);
          tv_cake_add1=itemView.findViewById(R.id.tv_event_address);
          tv_cake_date1=itemView.findViewById(R.id.tv_date_event);
          tv_cake_mseg=itemView.findViewById(R.id.tv_cake_msg);
          tv_cake_name1=itemView.findViewById(R.id.tv_cake_name);
          tv_cake_price1=itemView.findViewById(R.id.tv_cake_price);
          tv_cake_status1=itemView.findViewById(R.id.tv_order_status);
          tv_cake_quant=itemView.findViewById(R.id.tv_cake_quant);
          tv_cake_order_id=itemView.findViewById(R.id.tv_order_cake_order_id);
          payment_status=itemView.findViewById(R.id.tv_payment_status_cake);


          cancel=itemView.findViewById(R.id.button_to_cancel_cake_order);

        }
    }

}
