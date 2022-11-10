package com.example.notes24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectsPage extends AppCompatActivity {
    String sem_name,path;
    ListView listView;
    List<String> mylist;
    DatabaseReference databaseReference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_page);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        listView = findViewById(R.id.sub_listView);
//        mylist = new ArrayList<>();
        sem_name = getIntent().getStringExtra("sem_name");
        path = getIntent().getStringExtra("path");
        viewAllFiles();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),UnitsPage.class);
                intent.putExtra("sub_name",mylist.get(i));
                intent.putExtra("path",path+sem_name+"/");
                startActivity(intent);
            }
        });

    }

    private void viewAllFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference(path+sem_name+"/");
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
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}