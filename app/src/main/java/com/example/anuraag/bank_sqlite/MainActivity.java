package com.example.anuraag.bank_sqlite;

import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2;
    DbOperations dbOperations;
    CharArrayBuffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbOperations = new DbOperations(this);
        editText1 = findViewById(R.id.edt1);
        editText2 = findViewById(R.id.edt2);

        buffer = new CharArrayBuffer(5);
    }

    public void register(View view) {

        Intent i = new Intent(this,Register.class);
        startActivity(i);

    }

    public void login(View view) {

        String uname = editText1.getText().toString();
        String pass = editText2.getText().toString();

        Cursor cursor = dbOperations.login(uname,pass);

        if(cursor.getCount() == 0){

            Toast.makeText(this, "Login Failed"+cursor.getColumnCount(), Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

            //migrate to Home with user name

            Intent i = new Intent(this,Home.class);
            i.putExtra("username", uname);
            startActivity(i);

        }

    }

    public void show(View view) {

        Cursor cursor = dbOperations.getAll();

        cursor.moveToFirst();

        do {

            Toast.makeText(this, "" + cursor.getString(0)+cursor.getString(1), Toast.LENGTH_SHORT).show();
        }while(cursor.moveToNext());

        cursor.close();

    }
}
