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

public class AdapterAdminUserlist extends RecyclerView.Adapter<AdapterAdminUserlist.MyViewHolder> {

    Context context;

    ArrayList<User> list;


    public AdapterAdminUserlist(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userlistitem,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.userid.setText(user.getUid());
        holder.email.setText(user.getEmail());

        holder.view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname=user.getName();
                String uid=user.getUid();
                String uphone=user.getPhone();
                String uemail=user.getEmail();
                String uadd=user.getAdd();

                Intent i=new Intent(context,AdminShowUserDetailsAndOrders.class);
                i.putExtra("uname",uname);
                i.putExtra("mail",uemail);
                i.putExtra("uphone",uphone);
                i.putExtra("uadd",uadd);
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

        TextView email,userid;
        Button view_details;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userid=itemView.findViewById(R.id.admin_new_userid);

            email=itemView.findViewById(R.id.admin_new_userEmail);
            view_details=itemView.findViewById(R.id.admin_view_user_detail);



        }
    }

}