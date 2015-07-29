package com.keshogroup.boomtownapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class HomesControllerSubB extends ActionBarActivity {

    HomesFragmentDetailedView fragment2= new HomesFragmentDetailedView();
    FragmentManager fragmentmanager;
    FragmentTransaction fragmenttransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_homes_sub_b);
        fragmentmanager= getFragmentManager();
        fragmenttransaction=fragmentmanager.beginTransaction();
        fragmenttransaction.add(R.id.frameholder2,fragment2);
        fragmenttransaction.commit();

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homes_controller_sub_b, menu);
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
    }*/
}
