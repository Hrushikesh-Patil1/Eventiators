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


public class AdminAdapterPhotographer extends RecyclerView.Adapter<AdminAdapterPhotographer.MyViewHolder2> {

    Context context;

    ArrayList<AppointPhotographerDatabase> list;
    private String uid;


    public AdminAdapterPhotographer(Context context, ArrayList<AppointPhotographerDatabase> list) {
        this.context = context;
        this.list = list;
    }
    public AdminAdapterPhotographer(String uid) {

        this.uid = uid;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adminphotographeritem,parent,false);
        return  new MyViewHolder2(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        AppointPhotographerDatabase order = list.get(position);


        uid=order.getUid();
        int duration = Toast.LENGTH_SHORT;

        holder.x_photographer_id.setText(order.getPhotographer_code());
        holder.x_photographer_type.setText(order.getPhtographer_type());
        holder.x_photographer_status.setText(order.getStatus());
        holder.x_photographer_package.setText(order.getPhotographer_package());
        holder.x_photographer_price.setText(order.getPhotographer_price());
        holder.x_photographer_req.setText(order.getPhotographer_req());
        holder.x_photographer_address.setText(order.getPhotographer_add());
        holder.x_photographer_date.setText(order.getPhotographer_date());
        holder.x_photo_order_id.setText(order.getOrder_id());
        holder.x_photographer_payment_status.setText(order.getPayment_status());





        holder.accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.clear();



                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Photographer_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                myref.child("Photographer_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {


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
                myref.child("Photographer_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                myref.child("Photographer_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                myref.child("Photographer_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("payment_status").setValue("Failed");
                        Toast toast = Toast.makeText(context,"Payment Status Updated" ,duration);
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
                myref.child("Photographer_Order_Of_UserId__"+uid).child(order.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        TextView x_photographer_id,x_photographer_type,x_photographer_status,x_photographer_package,x_photographer_price,x_photographer_req,x_photographer_date,x_photographer_address,x_photo_order_id,x_photographer_payment_status;

        Button accepted,rejected,failed,done,completed,partial;
        //String uid;


        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);



            x_photographer_id= itemView.findViewById(R.id.admin_tv_photo_id);
            x_photographer_type= itemView.findViewById(R.id.admin_tv_photo_name);
            x_photographer_status= itemView.findViewById(R.id.admin_tv_photo_order_status);
            x_photographer_package= itemView.findViewById(R.id.admin_tv_photo_package);
            x_photographer_price= itemView.findViewById(R.id.admin_tv_photo_price);
            x_photographer_req= itemView.findViewById(R.id.admin_tv_photo_req);
            x_photographer_address= itemView.findViewById(R.id.admin_tv_photo_event_address);
            x_photographer_date= itemView.findViewById(R.id.admin_tv_date_event);
            x_photo_order_id=itemView.findViewById(R.id.admin_tv_photo_order_id);
            x_photographer_payment_status=itemView.findViewById(R.id.admin_tv_payment_status_photographer);


            accepted=itemView.findViewById(R.id.admin_accepted_photo);
            rejected=itemView.findViewById(R.id.admin_rejected_photo);
            failed=itemView.findViewById(R.id.admin_failed_photo);
            done=itemView.findViewById(R.id.admin_done_photo);
            completed=itemView.findViewById(R.id.admin_completed_photo);
            partial=itemView.findViewById(R.id.admin_partial_photo);

        }
    }

}




