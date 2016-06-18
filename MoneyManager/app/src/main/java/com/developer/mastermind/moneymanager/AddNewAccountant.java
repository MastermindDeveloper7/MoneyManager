package com.developer.mastermind.moneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.mastermind.database.NewAccounts;
import com.developer.mastermind.database.UserDetails;

public class AddNewAccountant extends AppCompatActivity implements View.OnClickListener{

    Button addButton , cancelButton;
    EditText nameEditText , amountEditText,otherDetailsEditText;
    NewAccounts newAccountDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_accountant);
        initializeViews();
    }

    private void initializeViews()
    {
        addButton = (Button) findViewById(R.id.add);
        cancelButton =  (Button) findViewById(R.id.cancel);
        nameEditText =  (EditText) findViewById(R.id.name);
        amountEditText = (EditText) findViewById(R.id.amount);
        otherDetailsEditText = (EditText) findViewById(R.id.otherDetails);
        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int buttondId = v.getId();

        if (buttondId == addButton.getId())
        {
            newAccountDb = new NewAccounts(getApplicationContext());
            Log.d("Object Created","Object Created");
            addNewAccount();
        }
        else if (buttondId == cancelButton.getId())
        {

        }
    }

    private void addNewAccount() {
        NewAccounts newAccountDb = new NewAccounts(getApplicationContext());
        String name = nameEditText.getText().toString();
        String amountString = amountEditText.getText().toString();
        String otherDetails = otherDetailsEditText.getText().toString();
        int amount = Integer.parseInt(amountString);

        long noOfRecords = newAccountDb.addUser(name,amount);

        Toast.makeText(getApplicationContext(),""+noOfRecords,Toast.LENGTH_LONG).show();

        if (noOfRecords >= 1)
            Toast.makeText(getApplicationContext(),"Record Inserted Successfully",Toast.LENGTH_LONG).show();

        UserDetails userDetailsDb = new UserDetails(getApplicationContext());

        noOfRecords = userDetailsDb.addRecord(name,amount,otherDetails);

        if (noOfRecords >= 1)
            Toast.makeText(getApplicationContext(),"Description Added",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
    }
}
