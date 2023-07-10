package com.example.helloworldapplication.ui.my_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.helloworldapplication.R;

import com.example.helloworldapplication.ViewCateringOrders;
import com.example.helloworldapplication.View_Decoration_orders;
import com.example.helloworldapplication.View_Gift_orders;
import com.example.helloworldapplication.View_cake_orders;
import com.example.helloworldapplication.View_photographer_bookings;

public class my_orderFragment extends Fragment {


        Button decore,photogrpaher,cake,catering;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_my_order, container, false);

        decore=root.findViewById(R.id.button_to_display_decoration_order);
        photogrpaher=root.findViewById(R.id.button_to_display_photographer_bookings);
        cake=root.findViewById(R.id.button_to_display_cake_order);

        catering=root.findViewById(R.id.button_to_display_catering_service_orders);



        decore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_Decoration_orders.class);
                startActivity(i);

            }
        });

        photogrpaher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_photographer_bookings.class);
                startActivity(i);
            }
        });


        catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ViewCateringOrders.class);
                startActivity(i);
            }
        });


        cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_cake_orders.class);
                startActivity(i);
            }
        });



        return root;
    }
}