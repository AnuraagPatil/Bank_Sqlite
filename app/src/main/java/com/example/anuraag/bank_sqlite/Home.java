package com.example.anuraag.bank_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView textView1,textView2,textView3;

    private String user;

    public DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbOperations = new DbOperations(this);

        textView1 = findViewById(R.id.ac_no);
        textView2 = findViewById(R.id.usr);
        textView3 = findViewById(R.id.bal);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        user = b.getString("username");
    }

    public void getDetails(View view) {

        Cursor cursor = dbOperations.getDetails(user);

        cursor.moveToFirst();

        textView1.setText(cursor.getString(0));
        textView2.setText(cursor.getString(1));
        textView3.setText(cursor.getString(2));

        cursor.close();

    }

    public void addBalance(View view) {

        Intent i = new Intent(this,AddBalance.class);
        i.putExtra("username",user);
        startActivity(i);
    }
}
