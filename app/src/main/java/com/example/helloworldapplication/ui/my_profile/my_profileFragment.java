package com.example.helloworldapplication.ui.my_profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.helloworldapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link my_profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class my_profileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public my_profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment my_profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static my_profileFragment newInstance(String param1, String param2) {
        my_profileFragment fragment = new my_profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseDatabase database;
        DatabaseReference userRef;
        ProgressBar progressBar;
        TextView tv_profile,tv_phone,tv_mail,tv_home;
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_my_profile, container, false);

        String email_string= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        tv_profile =v.findViewById(R.id.id_profile);
        tv_home=v.findViewById(R.id.id_home);
        tv_phone=v.findViewById(R.id.id_call);
        tv_mail=v.findViewById(R.id.id_mail);
        progressBar=v.findViewById(R.id.progressBar_profile);
        progressBar.setVisibility(View.VISIBLE);
        database=FirebaseDatabase.getInstance();
        userRef=database.getReference("Users");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email_string)){

                            tv_profile.setText(ds.child("name").getValue(String.class));
                            tv_phone.setText(ds.child("phone").getValue(String.class));
                            tv_home.setText(ds.child("add").getValue(String.class));
                            tv_mail.setText(ds.child("email").getValue(String.class));
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}