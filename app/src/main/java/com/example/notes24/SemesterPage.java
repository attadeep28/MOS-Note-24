package com.example.notes24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SemesterPage extends AppCompatActivity {
    ListView listView;
    List<String> mylist;
    DatabaseReference databaseReference;
    String dep_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_page);


        listView = findViewById(R.id.sem_list_view);
//        mylist = new ArrayList<>();
        dep_name = getIntent().getStringExtra("Department");
        viewAllFiles();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),SubjectsPage.class);
                intent.putExtra("sem_name",mylist.get(i));
                intent.putExtra("path","Departments/"+dep_name+"/");
                startActivity(intent);
            }
        });


    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Departments/"+dep_name+"/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mylist = new ArrayList<>();
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    DataSnapshot object = postSnapshot;
                    mylist.add(object.getKey().toString());
                }

                Adapter adapter = new Adapter(getApplicationContext(),mylist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}