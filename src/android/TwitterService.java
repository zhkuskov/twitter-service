package com.red_folder.twitter;

import java.io.BufferedInputStream;

import android.content.res.Resources;
import  	android.graphics.drawable.Drawable;
import android.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

/*
import com.example.android.common.logger.Log;
import com.example.android.common.logger.LogFragment;
import com.example.android.common.logger.LogWrapper;
import com.example.android.common.logger.MessageOnlyLogFilter;*/


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import  	android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;


/*#####################*/

public class TwitterService extends BackgroundService {

	private static final String TAG = TwitterService.class.getSimpleName();

/*-----------------------*/	
	@Override
	protected JSONObject doWork() {
		if (newTweet()) { 
			Log.d(TAG, "New Tweet - I'll need to send a notification");
			showNotification("New Tweet","Click to open");
		} else
			Log.d(TAG, "No new Tweets, back to sleep I go");
			
		return null;
	}
	
/*----------------------*/
	@Override
	protected JSONObject getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

/*------------------------*/
	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}

/*----------------------*/
	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub

	}

/*-----------------*/
	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub

	}

/*------------------*/
	@Override
	protected void setConfig(JSONObject arg0) {
		// TODO Auto-generated method stub

	}

/*-------------*/	
	/*private String getMaxID() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		return sp.getString("MaxID", "");
	}
*/
/*-----------------*/	
	/*private void setMaxID(String maxID) {
		if (maxID != null) {
			SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("MaxID", maxID);
			editor.commit();
		}
	}*/


/*-------------------------*/	
private Boolean newTweet() {

Boolean result = false;

try{
new DownloadTask().execute("http://search.twitter.com/search.json?q=phonegap&rpp=1&page1");
result = true;
}catch (Exception ex) {
			// Do something with the error in production code
			Log.d(TAG, "ERROR");
			Log.d(TAG, ex.getMessage());

		}//try

return result;
/*************************/


	/*	Boolean result = false;
		String oldMaxID = getMaxID();
		String newMaxID = oldMaxID;*/
		
	/*	HttpClient httpClient;
		HttpGet getMethod;
		HttpResponse response;*/

	/*	InputStream responseStream;

URL url = new URL(Uri.parse("http://search.twitter.com/search.json?q=phonegap&rpp=1&page1"));
   HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		try {

     responseStream = new BufferedInputStream(urlConnection.getInputStream()); */
   //  readStream(responseStream);
   

//---------_------->
	/*		httpClient = new DefaultHttpClient();
			getMethod = new HttpGet("http://search.twitter.com/search.json?q=phonegap&rpp=1&page1");
			response = httpClient.execute(getMethod);
			responseStream = response.getEntity().getContent();*/
		
	/*		StringBuilder dataString = new StringBuilder();
			char[] buffer = new char[1024];
			Reader reader = new InputStreamReader(responseStream, "UTF-8");
			int charCount;
			while ((charCount = reader.read(buffer, 0, buffer.length)) > 0){
				dataString.append(buffer, 0, charCount);
			}
			
			JSONObject data = new JSONObject(dataString.toString());
		
			if (data.has("max_id_str")) {
				newMaxID = data.getString("max_id_str");

				if (!newMaxID.equals(oldMaxID)) {
					setMaxID(newMaxID);
					result = true;
				}
			}

		} catch (Exception ex) {
			// Do something with the error in production code
			Log.d(TAG, "ERROR");
			Log.d(TAG, ex.getMessage());
		} finally {
			// Close out the response stream and any open connections in production code

     urlConnection.disconnect();
   
		}
		
		return result;*/

	}

/*---------------------*/	
public void showNotification( CharSequence contentTitle, CharSequence contentText ) {

NotificationCompat.Builder mBuilder =
    new NotificationCompat.Builder(this)
    .setSmallIcon(R.drawable.sym_action_email)
    .setContentTitle("My notification")
    .setContentText("Hello World!");


Intent resultIntent = new Intent(this, TwitterExampleActivity.class);

// Because clicking the notification opens a new ("special") activity, there's
//no need to create an artificial back stack.

PendingIntent resultPendingIntent =
    PendingIntent.getActivity(
    this,
    0,
    resultIntent,
    0
);

mBuilder.setContentIntent(resultPendingIntent);

// Sets an ID for the notification
int mNotificationId = 001;
// Gets an instance of the NotificationManager service
NotificationManager mNotifyMgr =
        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
mNotifyMgr.notify(mNotificationId, mBuilder.build());


	}
	


/*#####################*/

/**
     * Implementation of AsyncTask, to fetch the data in the background away from
     * the UI thread.
     */
    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                if(loadFromNetwork(urls[0])){
return "yes";
}else{
return "no";
}
            } catch (Exception ex) {
              Log.d(TAG, ex.getMessage());

return "no";
            }
        }

        /**
         * Uses the logging framework to display the output of the fetch
         * operation in the log fragment.
         */
        @Override
        protected void onPostExecute(String result) {
          Log.d(TAG, result);
        }
    }

    /** Initiates the fetch operation. */
    private Boolean loadFromNetwork(String urlString) throws IOException {
        InputStream stream = null;
      /*  String str ="";*/
Boolean result = false;

        try {
            stream = downloadUrl(urlString);
           /* str */ result = readIt(stream/*, 500*/);
       } finally {
           if (stream != null) {
               stream.close();
            }
        }
       /* return str;*/
return result;
    }

    /**
     * Given a string representation of a URL, sets up a connection and gets
     * an input stream.
     * @param urlString A string representation of a URL.
     * @return An InputStream retrieved from a successful HttpURLConnection.
     * @throws java.io.IOException
     */
    private InputStream downloadUrl(String urlString) throws IOException {
        // BEGIN_INCLUDE(get_inputstream)
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Start the query
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
        // END_INCLUDE(get_inputstream)
    }

    /** Reads an InputStream and converts it to a String.
     * @param stream InputStream containing HTML from targeted site.
     * @param len Length of string that this method returns.
     * @return String concatenated according to len parameter.
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    private Boolean readIt(InputStream stream/*, int len*/) throws IOException, UnsupportedEncodingException {

/*
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
*/

Boolean result = false;
		String oldMaxID = getMaxID();
		String newMaxID = oldMaxID;

			StringBuilder dataString = new StringBuilder();
			char[] buffer = new char[1024];

    
Reader reader = null;		

reader = new InputStreamReader(stream, "UTF-8");
			int charCount;
			while ((charCount = reader.read(buffer, 0, buffer.length)) > 0){
				dataString.append(buffer, 0, charCount);
			}//while
			
			JSONObject data = new JSONObject(dataString.toString());
		
			if (data.has("max_id_str")) {
				newMaxID = data.getString("max_id_str");

				if (!newMaxID.equals(oldMaxID)) {
					setMaxID(newMaxID);
					result = true;
				}//if
			}//if

return result;

    }

/*-------------*/	
	private String getMaxID() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		return sp.getString("MaxID", "");
	}

/*-----------------*/	
	private void setMaxID(String maxID) {
		if (maxID != null) {
			SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("MaxID", maxID);
			editor.commit();
		}
	}

    /** Create a chain of targets that will receive log data */
    public void initializeLogging() {

  /*      // Using Log, front-end to the logging chain, emulates
        // android.util.log method signatures.

        // Wraps Android's native log framework
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        // A filter that strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        mLogFragment =
                (LogFragment) getSupportFragmentManager().findFragmentById(R.id.log_fragment);
        msgFilter.setNext(mLogFragment.getLogView());*/

    }
}