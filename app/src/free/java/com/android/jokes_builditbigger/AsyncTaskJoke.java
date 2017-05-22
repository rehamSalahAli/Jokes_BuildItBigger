package com.android.jokes_builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.android.joke_android_lib.DisplayJoke;
import com.example.assi.myapplication.backend.myApi.MyApi;
import com.example.assi.myapplication.backend.myApi.model.MyBean;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by aSsi on 5/16/2017.
 */

public class AsyncTaskJoke extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApi = null;
    private Context context;
    private InterstitialAd interstitialAd;
    private ProgressBar progressBar;
    private String result;


    public AsyncTaskJoke(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(MyApi.DEFAULT_ROOT_URL);
            myApi = builder.build();
        }
        try {
            return myApi.setJoke(new MyBean()).execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
        }

        this.result = result;
        JokeDisplayActivity();

        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if (progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
                JokeDisplayActivity();
            }

            @Override
            public void onAdClosed() {
                JokeDisplayActivity();
            }
        });
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)

                .build();
        interstitialAd.loadAd(adRequest);
    }

    private void JokeDisplayActivity() {
        Intent intent = new Intent(context, DisplayJoke.class);
        intent.putExtra(DisplayJoke.JOKE, result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}