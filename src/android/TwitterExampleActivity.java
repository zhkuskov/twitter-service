package com.red_folder.phonegap.plugin.backgroundservice.twitter;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;



public class TwitterExampleActivity extends Activity { 
private static String TAG = "TwitterExampleActivity";
/* Called when the activity is first created. */

 @Override public void onCreate(Bundle savedInstanceState) { 
  super.onCreate(savedInstanceState);
    Log.v(TAG, "onCreate");


      forceMainActivityReload();
    
 }

 private void forceMainActivityReload() {
    PackageManager pm = getPackageManager();
    Intent launchIntent = pm.getLaunchIntentForPackage(getApplicationContext().getPackageName());
    startActivity(launchIntent);
  }

}