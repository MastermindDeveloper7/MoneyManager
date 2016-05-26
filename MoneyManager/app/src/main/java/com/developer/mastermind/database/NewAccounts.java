package com.developer.mastermind.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dhananjay on 5/9/2016.
 */
public class NewAccounts extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "USERS.DB";
    private static final String TABLE_NAME = "USERDATA";
    private static final String USER_NAME = "userName";
    private static final String TOTAL_AMOUNT = "totalAmount";




<<<<<<< HEAD:MoneyManager/app/src/main/java/com/developer/mastermind/database/UserDatabase.java
    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
=======
    public NewAccounts(Context context) {
        super(context,DATABASE_NAME, null, 1);
>>>>>>> 923531e75cbda9465d98c9aecdaf87fe75ba81ce:MoneyManager/app/src/main/java/com/developer/mastermind/database/NewAccounts.java
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + USER_NAME + " TEXT PRIMARY KEY," + TOTAL_AMOUNT + " INTEGER)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser  (String name, int amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(TOTAL_AMOUNT, amount);
        long numberOfRecords=db.insert(TABLE_NAME, null, contentValues);
        return numberOfRecords;
    }

    public Integer deleteUser (String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "userName = ? ",
                new String[] {name});
    }

    public long updateUser (String name, int amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NAME, name);
        contentValues.put(TOTAL_AMOUNT, amount);
        long numberOfRecords=db.update(TABLE_NAME, contentValues, "userName = ? ", new String[]{name});
        return numberOfRecords;
    }

    public ArrayList<String> getAllUsers()
    {
        ArrayList<String> accountDetails = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String name=res.getString(res.getColumnIndex(USER_NAME));
            int amount=res.getInt(res.getColumnIndex(TOTAL_AMOUNT));
            accountDetails.add(name);
            accountDetails.add(""+amount);
            Log.d("Arraylist record", name + " : " + amount);
            res.moveToNext();
        }
        res.close();
        db.close();
        return accountDetails;
    }




}
