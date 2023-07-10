package com.example.helloworldapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminAdapterShowCakeOrders extends RecyclerView.Adapter<AdminAdapterShowCakeOrders.MyViewHolderC> {

    Context context;

    ArrayList<CakeOrderDb> list;


    public AdminAdapterShowCakeOrders(Context context, ArrayList<CakeOrderDb> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolderC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.admincakeitem,parent,false);
        return  new MyViewHolderC(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderC holder, int position) {

        CakeOrderDb orderDb = list.get(position);

        holder.cakeorderid.setText(orderDb.getOrder_id());
        holder.cakeid.setText(orderDb.getCake_code());
        holder.cakename.setText(orderDb.getCake_name());
        holder.cakeprice.setText(orderDb.getCake_price());
        holder.cakequantity.setText(orderDb.getCake_quantity());
        holder.cakemsg.setText(orderDb.getCake_msg_print());
        holder.cakedate.setText(orderDb.getCake_date());
        holder.cakeaddress.setText(orderDb.getCake_add());
        holder.cakeorderstatus.setText(orderDb.getCake_status());
        holder.payment_status_cake.setText(orderDb.getPayment_status());

        String uid=orderDb.getUid();
        int duration = Toast.LENGTH_SHORT;





        holder.cake_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                list.clear();


                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Cake_Order_Of_UserId___"+uid).child(orderDb.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("cake_status").setValue("Accepted");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);

                        toast.show();


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

        holder.cake_rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Cake_Order_Of_UserId___"+uid).child(orderDb.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("cake_status").setValue("Rejected");
                        Toast toast = Toast.makeText(context,"Order Status Updated" ,duration);
                        toast.show();



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Toast toast = Toast.makeText(context,databaseError.getMessage() ,duration);
                        toast.show();
                    }
                });

            }
        });

        holder.cake_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                FirebaseDatabase database;
                DatabaseReference myref;
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("Cake_Order_Of_UserId___"+uid).child(orderDb.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("cake_status").setValue("Completed");
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
                myref.child("Cake_Order_Of_UserId___"+uid).child(orderDb.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                myref.child("Cake_Order_Of_UserId___"+uid).child(orderDb.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                myref.child("Cake_Order_Of_UserId___"+uid).child(orderDb.getOrder_id()).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public static class MyViewHolderC extends RecyclerView.ViewHolder{

        TextView cakeorderid,cakeid,cakename,cakeprice,cakequantity,cakemsg,cakedate,cakeaddress,cakeorderstatus,payment_status_cake;
        Button cake_accepted,cake_rejected,cake_completed,done,failed,partial;

        public MyViewHolderC(@NonNull View itemView) {
            super(itemView);



            cakeorderid=itemView.findViewById(R.id.admin_item_cake_order_id);
            cakeid=itemView.findViewById(R.id.admin_item_cake_id);
            cakename=itemView.findViewById(R.id.admin_item_cake_name);
            cakeprice=itemView.findViewById(R.id.admin_item_cake_price);
            cakequantity=itemView.findViewById(R.id.admin_item_cake_quantity);
            cakemsg=itemView.findViewById(R.id.admin_item_cake_msg);
            cakedate=itemView.findViewById(R.id.admin_item_cake_date);
            cakeaddress=itemView.findViewById(R.id.admin_item_cake_address);
            cakeorderstatus=itemView.findViewById(R.id.admin_item_cake_order_status);
            payment_status_cake=itemView.findViewById(R.id.admin_item_cake_order_status_payment);






            cake_accepted=itemView.findViewById(R.id.admin_item_cake_accepted);
            cake_rejected=itemView.findViewById(R.id.admin_item_cake_rejected);
            cake_completed=itemView.findViewById(R.id.admin_item_cake_completed);


            done=itemView.findViewById(R.id.admin_done_cake);
            failed=itemView.findViewById(R.id.admin_failed_cake);
            partial=itemView.findViewById(R.id.admin_partial_cake);
        }
    }
}
