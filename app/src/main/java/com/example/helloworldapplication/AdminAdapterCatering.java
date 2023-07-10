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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminAdapterCatering extends RecyclerView.Adapter<AdminAdapterCatering.MyViewHolder2>  {



    Context context;

    ArrayList<cateringDatabase> list;


    public AdminAdapterCatering(Context context, ArrayList<cateringDatabase> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdminAdapterCatering.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.admincateringitem,parent,false);
        return  new AdminAdapterCatering.MyViewHolder2(v);
    }



    @Override
    public void onBindViewHolder(@NonNull AdminAdapterCatering.MyViewHolder2 holder, int position) {

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


       String uid=order.getUid();
        int duration = Toast.LENGTH_SHORT;
        holder.catering_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.clear();



                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Catering_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("order_status").setValue("Accepted");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

        holder.catering_rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Catering_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("order_status").setValue("Rejected");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        holder.catering_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Catering_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("order_status").setValue("Completed");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        holder.catering_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Catering_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("payment_status").setValue("Done");
                        Toast toast = Toast.makeText(context,"Payment Status Updated" ,duration);
                        toast.show();


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        holder.catering_failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Catering_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("payment_status").setValue("Failed");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
        holder.partial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Catering_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("payment_status").setValue("Partial Done");
                        Toast toast = Toast.makeText(context,"Payment Status Updated" ,duration);
                        toast.show();


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });













    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{


        TextView tv_catering_order_id,tv_catering_type,tv_catering_price_per_plate,tv_catering_guest_count,tv_catering_event_date,tv_catering_add,tv_catering_requiring_days,tv_catering_menu_list,tv_catering_payment_status,tv_catering_order_status;
        Button catering_accepted,catering_rejected,catering_completed,catering_done,catering_failed,partial;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            tv_catering_order_id=itemView.findViewById(R.id.xtv_catering_order_id_admin);
            tv_catering_type=itemView.findViewById(R.id.xtv_catering_type_admin);
            tv_catering_price_per_plate=itemView.findViewById(R.id.xtv_price_per_plate_admin);
            tv_catering_guest_count=itemView.findViewById(R.id.xtv_guest_count_admin);
            tv_catering_event_date=itemView.findViewById(R.id.xtv_catering_event_date_admin);
            tv_catering_add=itemView.findViewById(R.id.xtv_catering_event_address_admin);
            tv_catering_requiring_days=itemView.findViewById(R.id.xtv_catering_requiring_days_admin);
            tv_catering_menu_list=itemView.findViewById(R.id.xtv_catering_menu_list_admin);
            tv_catering_payment_status=itemView.findViewById(R.id.xtv_payment_status_admin);
            tv_catering_order_status=itemView.findViewById(R.id.xtv_catering_order_status_admin);



            catering_accepted=itemView.findViewById(R.id.admin_accepted_catering);
            catering_completed=itemView.findViewById(R.id.admin_completed_catering);
            catering_rejected=itemView.findViewById(R.id.admin_rejected_catering);

            catering_done=itemView.findViewById(R.id.admin_done_catering);
            catering_failed=itemView.findViewById(R.id.admin_failed_catering);
            partial=itemView.findViewById(R.id.admin_partial_catering);



        }
    }

}

