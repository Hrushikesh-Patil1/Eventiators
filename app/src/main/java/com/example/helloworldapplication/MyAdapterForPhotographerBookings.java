package com.example.helloworldapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapterForPhotographerBookings extends RecyclerView.Adapter<MyAdapterForPhotographerBookings.MyViewHolder2> {

    Context context;

    ArrayList<AppointPhotographerDatabase> list;


    public MyAdapterForPhotographerBookings(Context context, ArrayList<AppointPhotographerDatabase> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.photgrapherorderitem,parent,false);
        return  new MyViewHolder2(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        AppointPhotographerDatabase order = list.get(position);
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



        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                String order_id=holder.x_photo_order_id.getText().toString();


                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Booking Updation");
                builder.setMessage("Do you really want to cancel your this booking?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference ref;
                        ref=FirebaseDatabase.getInstance().getReference("Photographer_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child(order_id);
                        ref.setValue(null);

                        AlertDialog.Builder builder2=new AlertDialog.Builder(context);
                        builder2.setTitle("Booking Cancellation");
                        builder2.setMessage("Your Booking Has Been Cancelled  Successfully !");
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
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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

        TextView x_photographer_id,x_photographer_type,x_photographer_status,x_photographer_package,x_photographer_price,x_photographer_req,x_photographer_date,x_photographer_address,x_photo_order_id,x_photographer_payment_status;
        Button cancel;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            x_photographer_id= itemView.findViewById(R.id.tv_photo_id);
            x_photographer_type= itemView.findViewById(R.id.tv_photo_name);
            x_photographer_status= itemView.findViewById(R.id.tv_photo_order_status);
            x_photographer_package= itemView.findViewById(R.id.tv_photo_package);
            x_photographer_price= itemView.findViewById(R.id.tv_photo_price);
            x_photographer_req= itemView.findViewById(R.id.tv_photo_req);
            x_photographer_address= itemView.findViewById(R.id.tv_photo_event_address);
            x_photographer_date= itemView.findViewById(R.id.tv_date_event);
            x_photo_order_id=itemView.findViewById(R.id.tv_photo_order_id);
            x_photographer_payment_status=itemView.findViewById(R.id.tv_payment_status_photographer);
            cancel=itemView.findViewById(R.id.button_to_cancel);



        }
    }

}

