package com.keshogroup.boomtownapp;
/*
Application Name: Boomtown Mock App
Application Descrition: Display use of Binders, Fragments, Asynctask, and JSON Retrofit
Owner:Kesho Group LLC
Contact Name: Chris Kesho
Contact Email: keshoLLC-info@yahoo.com
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import retrofit.*;


public class HomesController extends ActionBarActivity {
    Button mbutton1;
    FragmentManager fragmentmanager;
    FragmentTransaction fragmenttransaction;
    HomesFragmentList fragment1= new HomesFragmentList();
    //HomesFragmentDetailedView fragment2= new HomesFragmentDetailedView();//moved to new sub B activity
    EditText meditaddress;
    HomesModel homesmodel= new HomesModel();
    int indexaddress;
    String valueaddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_homes);
        indexaddress=0;
        valueaddress="no current data";
        mbutton1=(Button)findViewById(R.id.button1);
        meditaddress= (EditText)findViewById(R.id.editaddress);
        meditaddress.setImeActionLabel("search",EditorInfo.IME_ACTION_SEARCH);

        fragmentmanager = getFragmentManager();
        fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.add(R.id.frameholder1, fragment1);
        fragmenttransaction.commit();

        meditaddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //Do work
                    valueaddress = meditaddress.getText().toString();
                    indexaddress = homesmodel.setMaddress(valueaddress);
                    Log.i("boomtown", "index=" + indexaddress + " and " + meditaddress.getText().toString());
                    //moved these details to fragment method
                    //fragment1.homelist[indexaddress]=homesmodel.getMaddress(indexaddress);
                    //fragment1.marrayadapter.insert(homesmodel.getMaddress(indexaddress),indexaddress);

                    //fragment1.updatelist(indexaddress, valueaddress);
                    int c;
                    for(c=0;c<6; c++){
                        fragment1.updatelist(c, homesmodel.getMaddress(c));
                    }


                    handled = true;
                }
                return handled;
            }
        });


        mbutton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //if(fragmentmanager.findFragmentById(R.id.frameholder1)==null) { this was creating a null pointer
                    /*
                    fragmentmanager = getFragmentManager();
                    //fragment1 = (HomesFragmentList)fragmentmanager.findFragmentById(R.id.frameholder1);//? this is only checking if there was a previous frag here
                    //fragment1= new HomesFragmentList();
                    fragmenttransaction = fragmentmanager.beginTransaction();
                    fragmenttransaction.add(R.id.frameholder1, fragment1);
                    fragmenttransaction.commit();
                    */

                //}
                //Send alternative command to save input text
                valueaddress = meditaddress.getText().toString();
                indexaddress = homesmodel.setMaddress(valueaddress);
                //fragment1.updatelist(indexaddress, valueaddress);
                int c;
                for(c=0;c<6; c++){
                    fragment1.updatelist(c, homesmodel.getMaddress(c));
                }

            }
        });


    }//End on create


    @Override
    protected void onStart() {
        super.onStart();

        fragment1.mlistview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragment1.updatelist(position, "On click still works!!");

                //moved into seperate sub B activity
                /*
                fragmenttransaction = fragmentmanager.beginTransaction();
                fragmenttransaction.replace(R.id.frameholder1, fragment2);
                fragmenttransaction.addToBackStack("fragment2");
                fragmenttransaction.setBreadCrumbShortTitle("fragment2");
                fragmenttransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmenttransaction.commit();
                */


                //Uri uri = Uri.parse("http://square.github.io/retrofit/static/icon-github.png");
                Intent intent = new Intent();
                intent.putExtra("open","http://thekeshogroup.files.wordpress.com/2015/07/keshogroup-logo1w_kg.jpg");
                intent.setClass(getBaseContext(),HomesControllerSubB.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homes, menu);
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
}
