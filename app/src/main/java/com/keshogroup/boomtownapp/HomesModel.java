package com.keshogroup.boomtownapp;

import android.os.AsyncTask;

/**
 * Created by Chris on 7/28/2015.
 */
public class HomesModel {
    String[] maddress= new String[6];
    int[] mzipcode= new int[6];
    String[][] mfulladdress= new String[6][2];//row,column
    int mcountfulladdress;
    int mcountaddress;
    int mcountzip;
    int cnt;


//constructor
    public HomesModel() {
        mcountfulladdress=0;
        mcountaddress=0;
        mcountzip=0;

        for( cnt=0; cnt<6; cnt++){
            mzipcode[cnt]=0;
            maddress[cnt]="no house data";
            //add full house info
        }
    }

    private class Asynctaskrest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //work
            String result = params[0];
            return result;
        }

        @Override
        protected void onPostExecute(String postresult) {
            super.onPostExecute(postresult);
            //may need to use this?
            HomesModel.this.maddress[3]="postresult";
            maddress[5]="postresult";


        }
    }

    //method
    public int setMaddress(String mnewaddress) {
        if(mcountaddress>5){mcountaddress=0;}
        this.maddress[mcountaddress] = mnewaddress;
        maddress[4] = "no this";
        //update frag1
        //An asynctask can only be used once! So you have to create a new one every time
        Asynctaskrest callrest= new Asynctaskrest();
        callrest.execute(mnewaddress);
        mcountaddress++;
       return mcountaddress-1;
    }

    public void setMzipcode(int mnewzipcode) {
        this.mzipcode[mcountzip] = mnewzipcode;
        mcountzip++;
        if(mcountzip>5){mcountzip=0;}
    }

    public void setMfulladdress(String mnewfulladdress, String mnewzip) {
        //this.mfulladdress = mfulladdress;
        this.mfulladdress[mcountfulladdress][0] = mnewfulladdress;
        this.mfulladdress[mcountfulladdress][1] = mnewzip;
        mcountfulladdress++;
        if(mcountfulladdress>5){mcountfulladdress=0;}
    }

    public int getMzipcode( int mziprequest) {
        return mzipcode[mziprequest];
    }

    public String getMaddress(int maddressrequest) {

        return this.maddress[maddressrequest];
    }
}
