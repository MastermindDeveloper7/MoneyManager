package com.developer.mastermind.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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

    public NewAccounts(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + USER_NAME + " TEXT PRIMARY KEY," + TOTAL_AMOUNT + " INTEGER)";

        try {
            //db.execSQL("CREATE TABLE USERDATA (userName text PRIMARY KEY, totalAmount INTEGER)");
            db.execSQL(CREATE_TABLE_QUERY);
            Log.d("Table created","Table created successfullt Userdata");
        }
        catch (SQLiteException e)
        {
            Log.d("Create Table ","Error to create table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser  (String name, int amount)
    {
        long numberOfRecords = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(TOTAL_AMOUNT, amount);
        try {
            numberOfRecords = db.insert(TABLE_NAME, null, contentValues);
        }
        catch (SQLiteException exception)
        {
            Log.e("Insert Exception ","Exception to insert record in UserData" + exception);
            if (exception.getMessage().toString().contains("no such table")) {
                Log.e("**Record Not inserted**", "Creating table " + TABLE_NAME + "because it doesn't exist!");
            }
        }
        db.close();
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
