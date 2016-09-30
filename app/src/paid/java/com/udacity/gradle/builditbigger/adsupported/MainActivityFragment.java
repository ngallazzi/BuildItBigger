package com.udacity.gradle.builditbigger.adsupported;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nikogalla.androidjokes.JokesDisplayActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.EndpointsAsyncTaskListener {
    Button btTellJoke;
    Context mContext;
    ProgressDialog mProgressDialog;
    EndpointsAsyncTask mEndpointAsyncTask;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mEndpointAsyncTask = new EndpointsAsyncTask();
        mEndpointAsyncTask.setListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mEndpointAsyncTask!=null){
            mEndpointAsyncTask.removeListener();
        }
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        btTellJoke = (Button) root.findViewById(R.id.btTellJoke);
        mEndpointAsyncTask = new EndpointsAsyncTask();
        btTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEndpointAsyncTask = new EndpointsAsyncTask();
                mEndpointAsyncTask.execute(mContext);
            }
        });

        return root;
    }

    @Override
    public void onTaskStarted() {
        mProgressDialog = ProgressDialog.show(mContext,getString(R.string.retrieving_joke),"",true);
    }

    @Override
    public void onExecutionFinished(String joke) {
        if (mProgressDialog!=null)
            mProgressDialog.dismiss();
        // Showing joke in a new activity
        Intent intent = new Intent(mContext,JokesDisplayActivity.class);
        intent.putExtra(mContext.getString(R.string.joke_id),joke);
        mContext.startActivity(intent);
    }
}
