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
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by aSsi on 5/16/2017.
 */

public class AsyncTaskJoke extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApi = null;
    private Context context;


    private String result;
    private ProgressBar progressBar;

    public AsyncTaskJoke(Context context, ProgressBar progressBar) {
        this.context = context;

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
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.root_url_api));
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

        this.result = result;
        JokeDisplayActivity();
    }

    private void JokeDisplayActivity() {
        Intent intent = new Intent(context, DisplayJoke.class);
        intent.putExtra(DisplayJoke.JOKE, result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}