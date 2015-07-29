package com.keshogroup.boomtownapp;

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

    public int setMaddress(String mnewaddress) {
        if(mcountaddress>5){mcountaddress=0;}
        this.maddress[mcountaddress] = mnewaddress;
        //update frag1
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
