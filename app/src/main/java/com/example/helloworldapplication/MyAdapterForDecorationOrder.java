package com.example.helloworldapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldapplication.ui.my_order.my_orderFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class MyAdapterForDecorationOrder extends RecyclerView.Adapter<MyAdapterForDecorationOrder.MyViewHolder2> {

    Context context;

    ArrayList<Decore_Set_Order> list;


    public MyAdapterForDecorationOrder(Context context, ArrayList<Decore_Set_Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_decoration_order,parent,false);
        return  new MyViewHolder2(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        Decore_Set_Order order = list.get(position);
        holder.tv_order_id1.setText(order.getOrder_id());
        holder.tv_set_id1.setText(order.getSet_code());
        holder.tv_set_name1.setText(order.getSet_name());
        holder.tv_set_price1.setText(order.getSet_price());
        holder.tv_set_add1.setText(order.getSet_add());
        holder.tv_set_status1.setText(order.getStatus());
        holder.tv_set_date1.setText(order.getSet_date());
        holder.tv_payment.setText(order.getPayment_status());
        holder.tv_req_days.setText(order.getRequired());


        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                String order_id=holder.tv_order_id1.getText().toString();



                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Order Updation");
                builder.setMessage("Do you really want to cancel these order ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes,Cancel order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        DatabaseReference ref;
                        ref= FirebaseDatabase.getInstance().getReference("Decoration_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child(order_id);
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

        TextView tv_set_id1,tv_set_name1,tv_set_price1,tv_set_add1,tv_set_status1,tv_set_date1,tv_order_id1,tv_req_days,tv_payment;
        Button cancel;


        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            tv_set_id1= itemView.findViewById(R.id.tv_set_id);
            tv_set_name1=itemView.findViewById(R.id.tv_set_name);
            tv_set_price1=itemView.findViewById(R.id.tv_order_price);
            tv_set_add1=itemView.findViewById(R.id.tv_event_address);
            tv_set_status1=itemView.findViewById(R.id.tv_order_status);
            tv_set_date1=itemView.findViewById(R.id.tv_date_event);
            tv_order_id1=itemView.findViewById(R.id.tv_decore_order_id);
            tv_req_days=itemView.findViewById(R.id.tv_requiring);
            tv_payment=itemView.findViewById(R.id.tv_payment_status);


            cancel=itemView.findViewById(R.id.button_to_cancel_decore_order);

        }
    }

}
