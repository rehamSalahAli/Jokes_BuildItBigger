package com.android.joke_android_lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    public final static String JOKE = "JOKE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_joke);

        String joke = getIntent().getStringExtra(JOKE);

        TextView textViewJoke = (TextView) findViewById(R.id.textview_joke);

        if (joke != null) {
            textViewJoke.setText(joke);
        } else {
            textViewJoke.setText("can't find the joke!");
        }
    }


}

