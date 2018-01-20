package com.example.anuraag.bank_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anuraag on 19/1/18.
 */

public class DbOperations extends SQLiteOpenHelper {

    String query = "CREATE TABLE BANK(ACCOUNT_NO,USERNAME,PASSWORD,BALANCE)";

    public DbOperations(Context context) {
        super(context, "BANK", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long register(String ac_no, String uname, String pwd, String bal) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ACCOUNT_NO",ac_no);
        values.put("USERNAME",uname);
        values.put("PASSWORD",pwd);
        values.put("BALANCE",bal);

        return database.insert("BANK",null,values);
    }

    public Cursor login(String uname, String pass) {

        SQLiteDatabase database = getReadableDatabase();
        String[] col = {"USERNAME","PASSWORD"};

        return database.query("BANK",col,"USERNAME=? and PASSWORD=?", new String[]{uname,pass},null,null,null);

    }

    public Cursor getAll() {

        SQLiteDatabase database = getReadableDatabase();
        String[] col = {"USERNAME","PASSWORD"};

        return database.query("BANK",col,null,null,null,null,null);

    }

    public Cursor getDetails(String user) {

        SQLiteDatabase database = getReadableDatabase();
        String[] col = {"ACCOUNT_NO","USERNAME","BALANCE"};

        return database.query("BANK",col,"USERNAME=?", new String[]{user},null,null,null);
    }

    public void addBalance(String user, String s) {

        SQLiteDatabase database = getWritableDatabase();

        Cursor cursor = getDetails(user);
        cursor.moveToFirst();
        int bal = cursor.getInt(2);

        s = String.valueOf(bal + Integer.parseInt(s));

        ContentValues values = new ContentValues();
        values.put("BALANCE",s);
        database.update("BANK",values,"USERNAME=?", new String[]{user});

    }
}
