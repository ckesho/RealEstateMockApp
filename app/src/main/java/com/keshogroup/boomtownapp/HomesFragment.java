package com.keshogroup.boomtownapp;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomesFragment extends Fragment {

    private class Async extends AsyncTask<String, Void, Integer>{
    @Override
    protected Integer doInBackground(String... params) {
        //work
        Integer result=0;
        return result;
    }

    @Override
    protected void onPostExecute(Integer postresult) {
        super.onPostExecute(postresult);
    }
}
    Async minternettask1;
    ArrayAdapter<String> marrayadapter;
    public String[] homelist= {"no data", "no data","no data","no data","no data","no data"};//array=6
    //String[] homelist= new String[400];
    ListView mlistview1;
    int cnt;

    //constructor
    public HomesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        for(cnt=0;cnt<400; cnt++){
            homelist[cnt]="no house data";
        }
        mlistview1=(ListView)getActivity().findViewById(R.id.listview1);
        Log.i("boomtown", "mlistview success");
        marrayadapter= new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.textviewtemplate,homelist);
        Log.i("boomtown", "init madapter success");
        //mlistview1.setAdapter(marrayadapter);
        mlistview1.setBackgroundColor(Color.CYAN);
        Log.i("boomtown", "set madapter success");
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_homes, container, false);

        /*
        for(cnt=0;cnt<400; cnt++){
            homelist[cnt]="no house data";
        }
        */
        mlistview1=(ListView)view.findViewById(R.id.listview1);
        Log.i("boomtown", "mlistview success");
        marrayadapter= new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.textviewtemplate,homelist);
        Log.i("boomtown", "init madapter success");
        mlistview1.setAdapter(marrayadapter);
        mlistview1.setBackgroundColor(Color.CYAN);
        Log.i("boomtown", "set madapter success");
        //mlistview1.OnItemClickListener;

        /* //Moved this listener to homescontroller
        mlistview1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updatelist(position, "On click works");
            }
        });
        */



        return view;
    }
    public void updatelist(int inindex, String invalue){
        homelist[inindex]=invalue;
        marrayadapter= new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.textviewtemplate,homelist);
        mlistview1.setAdapter(marrayadapter);
    }
}
