package com.jaysonstaff.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_WORKSPACE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        Button createButton = findViewById(R.id.create);

        String name = getIntent().getStringExtra("name");
        if (name != null) {
            textViewWelcome.setText("Xin Chào, \n" + name + "!");
        }

        createButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WorkspaceActivity.class);
            startActivityForResult(intent, REQUEST_CODE_WORKSPACE);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_WORKSPACE && resultCode == RESULT_OK) {
            String company = data.getStringExtra("company");
            String address = data.getStringExtra("address");
            String email = data.getStringExtra("email");
            String phone = data.getStringExtra("phone");
            String employees = data.getStringExtra("employees");

            MainFragment fragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("company", company);
            bundle.putString("address", address);
            bundle.putString("email", email);
            bundle.putString("phone", phone);
            bundle.putString("employees", employees);
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
