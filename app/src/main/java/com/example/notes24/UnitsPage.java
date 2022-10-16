package com.example.notes24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UnitsPage extends AppCompatActivity {
    ListView listView;
    List<Model> mylist;
    DatabaseReference databaseReference;
    String sub_name,path;
    FloatingActionButton uploadPdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units_page);

        sub_name = getIntent().getStringExtra("sub_name");
        path = getIntent().getStringExtra("path");
        uploadPdf = findViewById(R.id.addPdf);



        uploadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),FileUploadPage.class);
                intent.putExtra("path",path+sub_name+"/");
                startActivity(intent);
            }
        });


        listView = findViewById(R.id.listViewUnits);
//        mylist = new ArrayList<>();

        viewAllFiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Model model = mylist.get(i);

                Intent intent = new Intent(UnitsPage.this,PdfViewPage.class);
                intent.putExtra("pdf_url", model.getPdfUrl());
                startActivity(intent);
            }
        });
    }

    private void viewAllFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference(path+sub_name);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mylist = new ArrayList<>();
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    Model model = postSnapshot.getValue(Model.class);
                    mylist.add(model);

                }
                ArrayList<String> uploads = new ArrayList<>();

                for(int i = 0; i < mylist.size(); i++){
                    uploads.add(mylist.get(i).getName());
                }
                Adapter adapter = new Adapter(getApplicationContext(),uploads);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}