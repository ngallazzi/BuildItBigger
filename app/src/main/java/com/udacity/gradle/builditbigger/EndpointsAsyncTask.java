package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.nikogalla.androidjokes.JokesDisplayActivity;
import com.nikogalla.jokesbackend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Nicola on 2016-09-19.
 */
public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private static EndpointsAsyncTaskListener mListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mListener.onTaskStarted();
    }

    public void setListener (EndpointsAsyncTaskListener listener){
        mListener = listener;
    }

    public void removeListener (){
        mListener = null;
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-143910.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        try {
            String joke = myApiService.getJoke().execute().getData();
            return joke;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onExecutionFinished(result);
    }

    public interface EndpointsAsyncTaskListener{
        void onTaskStarted();
        void onExecutionFinished(String joke);
    }
}
