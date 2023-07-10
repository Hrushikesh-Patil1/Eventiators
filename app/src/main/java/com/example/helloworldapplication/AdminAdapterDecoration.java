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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldapplication.ui.my_order.my_orderFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class AdminAdapterDecoration extends RecyclerView.Adapter<AdminAdapterDecoration.MyViewHolder2> {

    Context context;

    ArrayList<Decore_Set_Order> list;
    private String uid;


    public AdminAdapterDecoration(Context context, ArrayList<Decore_Set_Order> list) {
        this.context = context;
        this.list = list;
    }
    public AdminAdapterDecoration(String uid) {

        this.uid = uid;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.admin_decoration_item,parent,false);
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
        uid=order.getUid();
        int duration = Toast.LENGTH_SHORT;





        holder.accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.clear();



              FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Decoration_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("status").setValue("Accepted");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

        holder.rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Decoration_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("status").setValue("Rejected");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        holder.completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Decoration_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("status").setValue("Completed");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Decoration_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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




        holder.failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Decoration_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                myref.child("Decoration_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("payment_status").setValue("Partially Done");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
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

        TextView tv_set_id1,tv_set_name1,tv_set_price1,tv_set_add1,tv_set_status1,tv_set_date1,tv_order_id1,tv_req_days,tv_payment;
        Button accepted,rejected,failed,done,completed,partial;
        //String uid;


        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            tv_set_id1= itemView.findViewById(R.id.admin_tv_set_id);
            tv_set_name1=itemView.findViewById(R.id.admin_tv_set_name);
            tv_set_price1=itemView.findViewById(R.id.admin_tv_order_price);
            tv_set_add1=itemView.findViewById(R.id.admin_tv_event_address);
            tv_set_status1=itemView.findViewById(R.id.admin_tv_order_status);
            tv_set_date1=itemView.findViewById(R.id.admin_tv_date_event);
            tv_order_id1=itemView.findViewById(R.id.admin_tv_decore_order_id);
            tv_req_days=itemView.findViewById(R.id.admin_tv_requiring);
            tv_payment=itemView.findViewById(R.id.admin_tv_payment_status);


           accepted=itemView.findViewById(R.id.admin_accepted);
           rejected=itemView.findViewById(R.id.admin_rejected);
           failed=itemView.findViewById(R.id.admin_failed);
           done=itemView.findViewById(R.id.admin_done);
           completed=itemView.findViewById(R.id.admin_completed);
           partial=itemView.findViewById(R.id.admin_partial);

        }
    }

}



