package com.keshogroup.boomtownapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.TextView;


public class HomesFragmentDetailedView extends Fragment {

    private static final String TAG= "boomtown";
    WebView mwebview;
    TextView mtextview;


    public HomesFragmentDetailedView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_homes_detailed_view, container, false);
        mwebview= (WebView) view.findViewById(R.id.webview_fhdv);
        mtextview= (TextView) view.findViewById(R.id.textview_fhdv);
        //mwebview=getWebView();
        //This piece of code prevents automatic handling of url by another client which is norm
        mwebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        //mwebview.loadUrl("http://thekeshogroup.files.wordpress.com/2015/07/keshogroup-logo1w_kg.jpg");

         String urlstring= getActivity().getIntent().getStringExtra("open");
        mwebview.loadUrl(urlstring);
        if(urlstring.matches("http://www.zillowstatic.com/cms/AF-BottomMerchUpsell-1_HP-BottomRight-2X_updated.jpg"))
        {
            mtextview.setText("We are sorry this property is\nno longer on the market.\nPlease try back later");
        }
        return view;
    }



}
