package com.nikogalla.androidjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class JokesDisplayActivity extends AppCompatActivity {
    final static String TAG = JokesDisplayActivity.class.getSimpleName();
    TextView tvJoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_display);
        tvJoke = (TextView) findViewById(R.id.tvJoke);
        try{
            String joke = getIntent().getStringExtra(getString(R.string.joke_id));
            tvJoke.setText(joke);
        }catch (Exception e){
            Log.d(TAG,"Cannot display joke: " + e.getMessage());
        }
    }
}
