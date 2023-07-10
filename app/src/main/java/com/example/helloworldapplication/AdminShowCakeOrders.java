package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminShowCakeOrders extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    AdminAdapterShowCakeOrders myAdapter;
    ArrayList<CakeOrderDb> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_cake_orders);

        String uid = getIntent().getExtras().getString("uid");

        recyclerView = findViewById(R.id.admin_cake_order_recycler_view);
        database = FirebaseDatabase.getInstance().getReference("Cake_Order_Of_UserId___"+uid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new AdminAdapterShowCakeOrders(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    CakeOrderDb orderDb = dataSnapshot.getValue(CakeOrderDb.class);
                    list.add(orderDb);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}