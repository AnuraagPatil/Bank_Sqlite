package com.example.anuraag.bank_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText editText1,editText2,editText3,editText4;
    DbOperations dbOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText1 = findViewById(R.id.edt1);
        editText2 = findViewById(R.id.edt2);
        editText3 = findViewById(R.id.edt3);
        editText4 = findViewById(R.id.edt4);
        dbOperations = new DbOperations(this);
    }

    public void register(View view) {

        String ac_no = editText1.getText().toString();
        String uname = editText2.getText().toString();
        String pwd = editText3.getText().toString();
        String bal = editText4.getText().toString();

        long l = dbOperations.register(ac_no,uname,pwd,bal);

        if(l != -1) {
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
        }

        finish();

    }

    public void clear(View view) {

        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");

    }

    public void cancel(View view) {

        finish();
    }
}
