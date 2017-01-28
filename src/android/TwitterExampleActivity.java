package com.red_folder.twitter;

import org.apache.cordova.*;
import android.os.Bundle;



public class TwitterExampleActivity extends Activity implements CordovaInterface { 
CordovaWebView cwv; 
/* Called when the activity is first created. */

 @Override public void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState);
 /*setContentView(R.layout.main); 
cwv = (CordovaWebView) findViewById(R.id.tutorialView);*/ Config.init(this); cwv.loadUrl(Config.getStartUrl());
 }

}