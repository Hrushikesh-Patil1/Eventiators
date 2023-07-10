package com.example.helloworldapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class MyAdapterCateringOrdersList extends RecyclerView.Adapter<MyAdapterCateringOrdersList.MyViewHolder2>  {



    Context context;

    ArrayList<cateringDatabase> list;


    public MyAdapterCateringOrdersList(Context context, ArrayList<cateringDatabase> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterCateringOrdersList.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemcatering,parent,false);
        return  new MyAdapterCateringOrdersList.MyViewHolder2(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyAdapterCateringOrdersList.MyViewHolder2 holder, int position) {

        cateringDatabase order = list.get(position);

        holder.tv_catering_order_id.setText(order.getOrder_id());
        holder.tv_catering_type.setText(order.getCatering_type());
        holder.tv_catering_price_per_plate.setText(order.getPrice_per_plate());
        holder.tv_catering_guest_count.setText(order.getGuest_count());
        holder.tv_catering_add.setText(order.getEvent_address());
        holder.tv_catering_event_date.setText(order.getEvent_date());
        holder.tv_catering_menu_list.setText(order.getMenu_list());
        holder.tv_catering_payment_status.setText(order.getPayment_status());
        holder.tv_catering_order_status.setText(order.getOrder_status());
        holder.tv_catering_requiring_days.setText(order.getReq_days());


        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String order_id=holder.tv_catering_order_id.getText().toString();

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Order Updation");
                builder.setMessage("Do you really want to cancel these order ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes,Cancel order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference ref;
                        ref= FirebaseDatabase.getInstance().getReference("Catering_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child(order_id);
                        ref.setValue(null);
                        list.clear();

                        AlertDialog.Builder builder2=new AlertDialog.Builder(context);
                        builder2.setTitle("Order Cancellation");
                        builder2.setMessage("Your Order Has Been Cancelled  Successfully !");
                        builder2.setCancelable(false);
                        builder2.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                ((Activity)context).finish();



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


        TextView tv_catering_order_id,tv_catering_type,tv_catering_price_per_plate,tv_catering_guest_count,tv_catering_event_date,tv_catering_add,tv_catering_requiring_days,tv_catering_menu_list,tv_catering_payment_status,tv_catering_order_status;
        Button cancel;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            tv_catering_order_id=itemView.findViewById(R.id.xtv_catering_order_id);
            tv_catering_type=itemView.findViewById(R.id.xtv_catering_type);
            tv_catering_price_per_plate=itemView.findViewById(R.id.xtv_price_per_plate);
            tv_catering_guest_count=itemView.findViewById(R.id.xtv_guest_count);
            tv_catering_event_date=itemView.findViewById(R.id.xtv_catering_event_date);
            tv_catering_add=itemView.findViewById(R.id.xtv_catering_event_address);
            tv_catering_requiring_days=itemView.findViewById(R.id.xtv_catering_requiring_days);
            tv_catering_menu_list=itemView.findViewById(R.id.xtv_catering_menu_list);
            tv_catering_payment_status=itemView.findViewById(R.id.xtv_payment_status);
            tv_catering_order_status=itemView.findViewById(R.id.xtv_catering_order_status);

            cancel=itemView.findViewById(R.id.xbutton_to_cancel_catering_order);

        }
    }

}

