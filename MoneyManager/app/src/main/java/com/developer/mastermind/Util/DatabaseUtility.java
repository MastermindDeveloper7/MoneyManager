package com.developer.mastermind.Util;

import android.content.Context;

import com.developer.mastermind.database.NewAccounts;

/**
 * Created by Dhananjay on 5/22/2016.
 */
public class DatabaseUtility {

        public boolean insertNewAccountant(Context context,String[]values)
        {
            NewAccounts database = new NewAccounts(context);
           // database.add
            return true;
        }
}
