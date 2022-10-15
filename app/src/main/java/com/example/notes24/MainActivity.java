package com.example.notes24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView ct,it,cse,el,etc,me,ce,first_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_year = findViewById(R.id.first_year);
        ct = findViewById(R.id.CT);
        it = findViewById(R.id.IT);
        cse = findViewById(R.id.CSE);
        el = findViewById(R.id.EL);
        etc = findViewById(R.id.ETC);
        me = findViewById(R.id.ME);
        ce = findViewById(R.id.CE);


        ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SemesterPage.class);
                intent.putExtra("Department","Ctech");
                startActivity(intent);
            }
        });
        first_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SemesterPage.class);
                intent.putExtra("Department","First_Year");
                startActivity(intent);
            }
        });
    }
}