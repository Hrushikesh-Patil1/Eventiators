package com.example.helloworldapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.userid.setText(user.getUid());
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.address.setText(user.getAdd());
        holder.email.setText(user.getEmail());



        holder.admin_decoration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid=holder.userid.getText().toString();
                Intent i=new Intent(context,AdminDecoration.class);
                i.putExtra("uid",uid);
                context.startActivity(i);


            }
        });


        holder.admin_photographer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid=holder.userid.getText().toString();
                Intent i=new Intent(context,AdminViewPhotographer.class);
                i.putExtra("uid",uid);
                context.startActivity(i);


            }
        });


        holder.admin_cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid=holder.userid.getText().toString();
                Intent i=new Intent(context,AdminShowCakeOrders.class);
                i.putExtra("uid",uid);
                context.startActivity(i);


            }
        });


        holder.admin_catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid=holder.userid.getText().toString();
                Intent i=new Intent(context,AdminViewCatering.class);
                i.putExtra("uid",uid);
                context.startActivity(i);


            }
        });








    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,phone,address,email,userid;
        Button admin_decoration,admin_cake,admin_photographer,admin_catering;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userid=itemView.findViewById(R.id.tvUidAdmin);
            name = itemView.findViewById(R.id.tvNameAdmin);
            phone = itemView.findViewById(R.id.tvPhoneAdmin);
            address = itemView.findViewById(R.id.tvaddressAdmin);
            email=itemView.findViewById(R.id.tvEmailAdmin);

            admin_cake=itemView.findViewById(R.id.admin_view_cake);
            admin_decoration=itemView.findViewById(R.id.admin_view_decoration);

            admin_photographer=itemView.findViewById(R.id.admin_view_photo);

            admin_catering=itemView.findViewById(R.id.admin_view_catering);


        }
    }

}