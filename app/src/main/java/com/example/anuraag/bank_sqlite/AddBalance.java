package com.example.anuraag.bank_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddBalance extends AppCompatActivity {

    private String user;
    private EditText edtxtuser;
    DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);

        dbOperations = new DbOperations(this);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        user = b.getString("username");

        edtxtuser = findViewById(R.id.edt1);

    }

    public void addBalance(View view) {

        String bal = edtxtuser.getText().toString();

        dbOperations.addBalance(user,bal);
        Toast.makeText(this, bal+" added to balance", Toast.LENGTH_SHORT).show();

        finish();
    }
}
