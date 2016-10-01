package com.udacity.gradle.builditbigger.adsupported;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nikogalla.androidjokes.JokesDisplayActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.EndpointsAsyncTaskListener {
    Button btTellJoke;
    Context mContext;
    ProgressDialog mProgressDialog;
    EndpointsAsyncTask mEndpointAsyncTask;
    private InterstitialAd mInterstitialAd;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        requestNewInterstitial();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        btTellJoke = (Button) root.findViewById(R.id.btTellJoke);
        btTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEndpointAsyncTask = new EndpointsAsyncTask();
                mEndpointAsyncTask.setListener(MainActivityFragment.this);
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
    public void onExecutionFinished(final String joke) {
        mEndpointAsyncTask.removeListener();
        if (mProgressDialog!=null)
            mProgressDialog.dismiss();
        // Showing ad
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                       // Showing joke in a new activity
                    Intent intent = new Intent(mContext,JokesDisplayActivity.class);
                    intent.putExtra(mContext.getString(R.string.joke_id),joke);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

}
