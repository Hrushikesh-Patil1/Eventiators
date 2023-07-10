package com.example.helloworldapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworldapplication.AppointChef;
import com.example.helloworldapplication.AppointPhotographer;
import com.example.helloworldapplication.DecoreEvents;
import com.example.helloworldapplication.InvitationCard;
import com.example.helloworldapplication.LogIn;
import com.example.helloworldapplication.OrderCake;
import com.example.helloworldapplication.OrderGifts;
import com.example.helloworldapplication.R;
import com.example.helloworldapplication.admin_home;
import com.example.helloworldapplication.birthdayinvitation;

public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        GridLayout layout=(GridLayout) root.findViewById(R.id.mainlayout);
        setsingleEvent(layout);
        return root;



    }

    private void setsingleEvent(GridLayout layout) {
        for(int i=0;i<layout.getChildCount();i++)
        {

            CardView cv=(CardView)layout.getChildAt(i);
            final int finalIntI=i;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalIntI==0)
                    {
                        Intent j = new Intent(getActivity(), DecoreEvents.class);
                        startActivity(j);

                    }
                    else if(finalIntI==1)
                    {
                        Intent j = new Intent(getActivity(), AppointPhotographer.class);
                        startActivity(j);
                    }

                    else if(finalIntI==2)
                    {
                        Intent j = new Intent(getActivity(), AppointChef.class);
                        startActivity(j);
                    }
                    else if(finalIntI==3)
                    {
                        Intent j = new Intent(getActivity(), OrderCake.class);
                        startActivity(j);
                    }

                    else if(finalIntI==4)
                    {
                        Intent j = new Intent(getActivity(), birthdayinvitation.class);
                        startActivity(j);
                    }
                }
            });

        }
    }
    }


