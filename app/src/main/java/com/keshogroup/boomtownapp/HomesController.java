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

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.*;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


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
        //@GET("/q/30328.xml")
        //Searchresults listaddress();

        //@GET("/q/{zip}.json")
        //JsonObject listzipcode(@Path("zip") String zip);

        //@GET("/q/30322.json")
        //JsonObject listzipcode();

        //@GET("/&address={address}&citystatezip={zip}")
        //JsonObject listaddress(@Path("address") String address, @Path("zip") String zip);

        @GET("/GetDeepSearchResults.htm")
        Searchresults listaddress(@Query("zws-id") String zwsid, @Query("address") String address, @Query("citystatezip") String citystatezip);
        //@GET("/GetDeepSearchResults.htm?zws-id=X1-ZWz1euyrodefwr_4g4th&address={address}&citystatezip={zip}")
        //Searchresults listaddress(@Path("address") String address, @Path("zip") String zip, @Path("question") String question);
        // '?' prevents using dynamic blocks so replace it with path and manual ?
        //zillow doesn't allow json so adjust builder to use simplexml

        //@GET("/GetDeepSearchResults.htm?zws-id=X1-ZWz1euyrodefwr_4g4th&address=970+parsons+st&citystatezip=30314")
        //Searchresults listaddress();
        // '?' prevents using dynamic blocks so replace it with path and manual ?
        //zillow doesn't allow json so adjust builder to use simplexml



        //List<Object> listzipcode(@Path("zip") String zip);//java.util.list is similar to an array
    }




    public interface ZillowService2 {


        @GET("/GetUpdatedPropertyDetails.htm")
        Pictureresults listpicture(@Query("zws-id") String zwsid, @Query("zpid") String zpid);


    }//end zillowservice2







    private class Asynctaskrest extends AsyncTask<String, Void, String> {
        String asynczpid;
        @Override
        protected String doInBackground(String... params) {
            //work
            String result = params[0];
            String resultaddress, resultzip;

            //homesmodel.setMaddress(result);

            String query= "/q/30328"; String format=".json"; String web = "http://api.wunderground.com/api";
            String please= "/852ddd19c6a16593"; String feature= "/conditions";
            //String surl= web+please+feature+query+format;
            String surl= web+please+feature;
            //String surl="http://api.wunderground.com/api/852ddd19c6a16593/forecast/geolookup/conditions/q/CA/San_Francisco.json";//"http://api.wunderground.com/api/852ddd19c6a16593/features/settings/q/query.format";
            //http://api.wunderground.com/api/852ddd19c6a16593/conditions/q/30328.json



            /*
            * Below is an example of calling the API for the address for the exact address match "2114 Bigelow Ave", "Seattle, WA":
            * http://www.zillow.com/webservice/GetDeepSearchResults.htm?zws-id=<ZWSID>&address=2114+Bigelow+Ave&citystatezip=Seattle%2C+WA
            */

            resultaddress=result.substring(0, (result.length() - 6));
            resultaddress=resultaddress.replace(' ', '+');
            resultzip=result.substring(result.length() - 5);
            Log.i("popfly", "/" + resultaddress + "/" + resultzip + "/");
            String zillowurl="http://www.zillow.com/webservice";

            RestAdapter restAdapter = new RestAdapter.Builder()

                    //.setClient(new OkClient(new OkHttpClient()))
                    .setEndpoint(zillowurl)//zillowurl
                    .setConverter(new SimpleXMLConverter(false))
                    //.setConverter(new retrofit.converter.SimpleXMLConverter())
                    .build();
            ZillowService service=restAdapter.create(ZillowService.class);




            //Searchresults task=service.listaddress();






            try {
                //Move inside try incase a zid doesnt return!
                //service.listaddress();
                //JsonObject object= service.listaddress(resultaddress, resultzip);//zillow doesnt allow json but xml
                Searchresults data=service.listaddress("X1-ZWz1euyrodefwr_4g4th",resultaddress, resultzip);
                //Searchresults data=service.listaddress();
                asynczpid=data.response.results.result.zpid;

                Log.i("popfly", "zpid="+data.response.results.result.zpid);
                Log.i("popfly", "message="+data.message.toString());
                Log.i("popfly", "request="+data.request.toString());

                //Log.i("popfly", "zpid="+data.results.toString());
                //Log.i("popfly", "zpid="+data.searchresults);
                //JsonObject object2=object.getAsJsonObject("current_observation");
                //object2.getAsJsonPrimitive("temp_c").getAsInt();

               // Log.i("popfly", "Size= "+object.size());
                //String temperature=
                //object.has("temperature_string");
                //Log.i("popfly", "bool"+object2.has("temp_c")+ object2.getAsJsonPrimitive("temp_c").getAsInt());
                //Log.i("popfly", "bool" + object.has("current_observation"));
                //Log.i("popfly", "bool"+object2.has("features"));
            } catch (Exception e) {
                e.printStackTrace();

                Log.i("popfly", "Didn't work!"+ e.toString());
            }



            return result;
        }

        @Override
        protected void onPostExecute(String postresult) {
            super.onPostExecute(postresult);
            indexaddress=homesmodel.setMaddress(postresult);
            homesmodel.setMzpid(indexaddress,asynczpid);
            fragment1.updatelist(indexaddress, homesmodel.getMaddress(indexaddress));

        }
    }//end of Asynctaskrest






    private class Asynctaskrest2 extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {
            //work

            String resultzpid = homesmodel.getMzpid(params[0]);
            String zillowurl2="http://www.zillow.com/webservice";
            String mresultpicture="http://www.zillowstatic.com/cms/AF-BottomMerchUpsell-1_HP-BottomRight-2X_updated.jpg";

            /*
            http://www.zillow.com/webservice/GetUpdatedPropertyDetails.htm?zws-id=<ZWSID>&zpid=48749425
            */

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(zillowurl2)//zillowurl
                    .setConverter(new SimpleXMLConverter(false))
                    .build();
            ZillowService2 picservice=restAdapter.create(ZillowService2.class);
            Pictureresults mpicture=picservice.listpicture("X1-ZWz1euyrodefwr_4g4th",resultzpid);

            try {
                mresultpicture=mpicture.response.images.image.get(0);
                Log.i("popfly", "picture="+mpicture.response.images.image.get(0).toString());
                Log.i("popfly", "picture="+mpicture.response.images.toString());
                Log.i("popfly", "picture="+mpicture.response.images.image.size());

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("popfly", "Didn't work!"+ e.toString());
            }

            return mresultpicture;
        }

        @Override
        protected void onPostExecute(String postresult) {
            super.onPostExecute(postresult);
            //call activity 2 with picture info
            Intent intent = new Intent();
            intent.putExtra("open",postresult);
            intent.setClass(getBaseContext(),HomesControllerSubB.class);
            startActivity(intent);

        }
    }//end of Asynctaskrest2









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
                fragment1.updatelist(position, "zpid"+ homesmodel.getMzpid(position));
                Asynctaskrest2 asynctaskrest2= new Asynctaskrest2();
                asynctaskrest2.execute(position);
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
