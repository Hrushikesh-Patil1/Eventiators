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

public class AdminViewPhotographer extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    AdminAdapterPhotographer myAdapter;
    ArrayList<AppointPhotographerDatabase> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_photographer);

        String uid = getIntent().getExtras().getString("uid");





        recyclerView = findViewById(R.id.admin_photographer_order_recyclerview);
        database = FirebaseDatabase.getInstance().getReference("Photographer_Order_Of_UserId__"+uid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new AdminAdapterPhotographer(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    AppointPhotographerDatabase order = dataSnapshot.getValue(AppointPhotographerDatabase.class);
                    list.add(order);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}