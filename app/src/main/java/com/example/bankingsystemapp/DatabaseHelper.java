package com.example.bankingsystemapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Users.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9000000000,'SAUMYA',10000.00,'s@gmail.com','XXXXXXXXXXXX9876','ABC09876543')");
        db.execSQL("insert into user_table values(9111111111,'SAM',800000,'sa@gmail.com','XXXXXXXXXXXX9821','BCA98765432')");
        db.execSQL("insert into user_table values(9222222222,'SANYA',700000,'sanya@gmail.com','XXXXXXXXXXXX8721','CAB87654321')");
        db.execSQL("insert into user_table values(9333333333,'CHRIS',144001,'chris@gmail.com','XXXXXXXXXXXX4876','ABC76543210')");
        db.execSQL("insert into user_table values(9444444444,'AVICII',600000,'avicii@gmail.com','XXXXXXXXXXXX1234','BCA65432109')");
        db.execSQL("insert into user_table values(9555555555,'HEMS',78688585,'hems@gmail.com','XXXXXXXXXXXX6788','CAB54321098')");
        db.execSQL("insert into user_table values(9666666666,'LAARY',700.80,'laary@gmail.com','XXXXXXXXXXXX8888','ABC43210987')");
        db.execSQL("insert into user_table values(9777777777,'ANN',800022,'ann@gmail.com','XXXXXXXXXXXX6666','BCA32109876')");
        db.execSQL("insert into user_table values(9888888888,'VAMP',607046,'vamp@gmail.com','XXXXXXXXXXXX8855','CAB21098765')");
        db.execSQL("insert into user_table values(9999999999,'KETH',7989890,'keth@gmail.com','XXXXXXXXXXXX7799','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
