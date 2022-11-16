package com.example.notes24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileUploadAuthentication extends AppCompatActivity {

    private EditText accessCode;
    private Button continueButton;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload_authentication);

        accessCode = (EditText) findViewById(R.id.accessCode);
        continueButton = (Button) findViewById(R.id.continueButton);
        path = getIntent().getStringExtra("path");
        String adminCode = new String("1234567");
        accessCode.requestFocus();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputAccessCode = accessCode.getText().toString();
                authenticateAdmin(inputAccessCode, adminCode);
            }
        });
    }

    public void authenticateAdmin(String inputAccessCode, String adminCode) {
        if(inputAccessCode.equals(adminCode)) {
            Intent signUpActivity = new Intent(this, FileUploadPage.class);
            signUpActivity.putExtra("path",path);
            startActivity(signUpActivity);
        }else{
            Toast.makeText(this, "Invalid Code", Toast.LENGTH_LONG).show();
        }
    }
}