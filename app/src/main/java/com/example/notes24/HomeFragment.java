package com.example.notes24;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {
    CardView ct,it,cse,el,etc,me,ce,first_year;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_home,null);

        first_year = v.findViewById(R.id.first_year);
        ct = v.findViewById(R.id.CT);
        it = v.findViewById(R.id.IT);
        cse = v.findViewById(R.id.CSE);
        el = v.findViewById(R.id.EL);
        etc = v.findViewById(R.id.ETC);
        me = v.findViewById(R.id.ME);
        ce = v.findViewById(R.id.CE);

        ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SemesterPage.class);
                intent.putExtra("Department","Ctech");
                startActivity(intent);
            }
        });
        first_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SemesterPage.class);
                intent.putExtra("Department","First_Year");
                startActivity(intent);
            }
        });

        return v;

    }
}