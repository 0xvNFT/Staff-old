package com.jaysonstaff.staff;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.jaysonstaff.staff.model.Workspace;

import java.util.UUID;

public class WorkspaceActivity extends AppCompatActivity {
    private EditText editTextCompany;
    private EditText editTextAddress;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextEmployees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);

        editTextCompany = findViewById(R.id.company);
        editTextAddress = findViewById(R.id.address);
        editTextEmail = findViewById(R.id.email);
        editTextPhone = findViewById(R.id.phone);
        editTextEmployees = findViewById(R.id.employee);

        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String company = editTextCompany.getText().toString();
        String address = editTextAddress.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String employees = editTextEmployees.getText().toString();

        // Return the new workspace data to the calling activity
        Intent intent = new Intent();
        intent.putExtra("company", company);
        intent.putExtra("address", address);
        intent.putExtra("email", email);
        intent.putExtra("phone", phone);
        intent.putExtra("employees", employees);
        setResult(RESULT_OK, intent);
        finish();
    }


    private void saveWorkspace() {
        String company = editTextCompany.getText().toString();
        String address = editTextAddress.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String employees = editTextEmployees.getText().toString();

        // Create a new Workspace object
        Workspace workspace = new Workspace(company, address, email, phone, employees);

        // Save the workspace to SharedPreferences
        saveWorkspaceToSharedPreferences(workspace);

        // Return to MainActivity
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private void saveWorkspaceToSharedPreferences(Workspace workspace) {
        SharedPreferences sharedPreferences = getSharedPreferences("workspaces", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Generate a unique key for the workspace
        String key = UUID.randomUUID().toString();

        // Convert the Workspace object to JSON
        Gson gson = new Gson();
        String workspaceJson = gson.toJson(workspace);

        // Save the workspace JSON with the unique key
        editor.putString(key, workspaceJson);
        editor.apply();
    }


}
