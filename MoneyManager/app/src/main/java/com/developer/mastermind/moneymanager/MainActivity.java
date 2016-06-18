package com.developer.mastermind.moneymanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.mastermind.Util.CustomAdapter;
import com.developer.mastermind.Util.ListModel;
import com.developer.mastermind.database.NewAccounts;
import com.developer.mastermind.database.UserDetails;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView mainListView;
    CustomAdapter adapter;
    private ArrayList<ListModel> CustomListViewValuesArray = new ArrayList<ListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewAccount();
                finish();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //initilizeMainListView();
        setListData();
        adapter = new CustomAdapter(this,CustomListViewValuesArray);
        mainListView.setAdapter(adapter);
    }

    private void initilizeMainListView() {

        mainListView = (ListView) findViewById(R.id.mainContentList);
        NewAccounts newAccountDb = new NewAccounts(getApplicationContext());
        ArrayList<String> allUserDataList = newAccountDb.getAllUsers();
        String[] allUsersDataStringArray = new String[allUserDataList.size()];
        allUsersDataStringArray = allUserDataList.toArray(allUsersDataStringArray);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,allUsersDataStringArray);

        mainListView.setAdapter(listAdapter);
    }

    public void setListData()
    {
        mainListView = (ListView) findViewById(R.id.mainContentList);
        NewAccounts newAccountDb = new NewAccounts(getApplicationContext());
        ArrayList<String> allUserDataList = newAccountDb.getAllUsers();
        String[] allUsersDataStringArray = new String[allUserDataList.size()];
        allUsersDataStringArray = allUserDataList.toArray(allUsersDataStringArray);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,allUsersDataStringArray);


        for (int i=0; i<allUserDataList.size()/2;i++)
        {
            final ListModel sched = new ListModel();
            sched.setName(allUserDataList.get(i*2));
            sched.setTotalAmount(allUserDataList.get(i*2+1));

            CustomListViewValuesArray.add(sched);
        }

    }

    private void addNewAccount() {
        Intent intent = new Intent(this,AddNewAccountant.class);
        startActivity(intent);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.new_account_dialog_layout, null))
                // Add action buttons
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //setListData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
