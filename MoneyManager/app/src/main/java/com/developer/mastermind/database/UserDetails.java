package com.developer.mastermind.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.text.method.DateTimeKeyListener;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Dhananjay on 5/9/2016.
 */

public class UserDetails extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EXPENSES";
    private static final String TABLE_NAME = "USERDATA";
    private static final String USER_NAME = "userName";
    private static final String AMOUNT = "Amount";
    private static final String DESCRIPTION = "Description";
    private static final String CREATED_DATE = "createdDate";

    public UserDetails(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + USER_NAME + " TEXT PRIMARY KEY," + AMOUNT + " INTEGER,"+DESCRIPTION+ " TEXT,"+CREATED_DATE+" date default CURRENT_DATE)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public long addRecord  (String name, int amount,String description)
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(AMOUNT, amount);
        contentValues.put(DESCRIPTION, description);
        contentValues.put(CREATED_DATE, dateFormat.format(date));
        long numberOfRecords=db.insert(TABLE_NAME, null, contentValues);
        return numberOfRecords;
    }

    public Integer deleteRecord (String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "userName = ? ",
                new String[] {name});
    }

    public long updateRecord (String name, int amount,String description,Date date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NAME, name);
        contentValues.put(AMOUNT, amount);
        contentValues.put(DESCRIPTION, description);
        contentValues.put(CREATED_DATE, String.valueOf(date));
        long numberOfRecords=db.update(TABLE_NAME, contentValues, "userName = ? ", new String[]{name});
        return numberOfRecords;
    }

    public String getDescription(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME + "where userName=" + name + "", null);
        String description=res.getString(res.getColumnIndex(DESCRIPTION));
        return description;
    }
    public ArrayList<String> getAllRecords()
    {
        ArrayList<String> accountDetails = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String name=res.getString(res.getColumnIndex(USER_NAME));
            int amount = res.getInt(res.getColumnIndex(AMOUNT));
            String description=res.getString(res.getColumnIndex(DESCRIPTION));
            String date=res.getString(res.getColumnIndex(CREATED_DATE));
            accountDetails.add(name);
            accountDetails.add(""+amount);
            Log.d("Arraylist record", name + " : " + amount+" : "+description+" : "+date);
            res.moveToNext();
        }
        res.close();
        db.close();
        return accountDetails;
    }
}
