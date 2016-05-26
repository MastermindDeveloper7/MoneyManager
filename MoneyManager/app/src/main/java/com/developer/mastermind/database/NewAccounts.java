package com.developer.mastermind.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dhananjay on 5/9/2016.
 */
public class NewAccounts extends SQLiteOpenHelper{

    private static String DATABASE_NAME="USERS.DB";
    private static  String TABLE_NAME="USERDATA";
    private static String USER_NAME_COLUMN="USER_NAME";
    private static String PASSWORD_COLUMN="PASSWORD";

    private static String createTableQuery="Crete table " + TABLE_NAME + " ( " + USER_NAME_COLUMN + "text," + PASSWORD_COLUMN +"text );";

    public NewAccounts(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
