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
import android.os.AsyncTask;
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

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;


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

    public interface ZillowService {
        //@GET("/q/30328.json")
        @GET("/q/{zip}.json")
        Object listzipcode(@Path("zip") String zip);
        //@GET("/basil2style")
        //Object listzipcode();

        //List<Object> listzipcode(@Path("zip") String zip);//java.util.list is similar to an array
    }




    private class Asynctaskrest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //work
            String result = params[0];
            //homesmodel.setMaddress(result);

            String query= "/q/30328"; String format=".json"; String web = "http://api.wunderground.com/api";
            String please= "/852ddd19c6a16593"; String feature= "/conditions";
            //String surl= web+please+feature+query+format;
            String surl= web+please+feature;
            //String surl="http://api.wunderground.com/api/852ddd19c6a16593/forecast/geolookup/conditions/q/CA/San_Francisco.json";//"http://api.wunderground.com/api/852ddd19c6a16593/features/settings/q/query.format";

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(surl)
                    .build();
            ZillowService service=restAdapter.create(ZillowService.class);
            //List<String> zipcode=service.listzipcode(result);
            //service.listzipcode().length();

            Log.i("popfly", service.listzipcode(result).toString());







            return result;
        }

        @Override
        protected void onPostExecute(String postresult) {
            super.onPostExecute(postresult);
            indexaddress=homesmodel.setMaddress(postresult);
            fragment1.updatelist(indexaddress, homesmodel.getMaddress(indexaddress));

        }
    }//end of Asynctaskrest



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
                    //Send alternative command to save input text
                    valueaddress = meditaddress.getText().toString();
                    //An asynctask can only be used once! So you have to create a new one every time
                    Asynctaskrest callrest= new Asynctaskrest();
                    callrest.execute(valueaddress);

                    handled = true;
                }
                return handled;
            }
        });




        mbutton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                //Send alternative command to save input text
                valueaddress = meditaddress.getText().toString();
                //An asynctask can only be used once! So you have to create a new one every time
                Asynctaskrest callrest= new Asynctaskrest();
                callrest.execute(valueaddress);

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
