package com.android.jokes_builditbigger;

import android.util.Log;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by aSsi on 5/18/2017.
 */
public class AsyncTask_JokeTest {
    @Test
    public void doInBackground() throws Exception {
        try {
            MainActivity mainActivity = new MainActivity();
            AsyncTaskJoke asyncTask_joke = new AsyncTaskJoke(mainActivity, null);
            asyncTask_joke.execute();
            Thread.sleep(5000);

            String running_result = asyncTask_joke.get(25, TimeUnit.SECONDS);


            assertNotNull(running_result);

            assertTrue(running_result.length() > 0);
        } catch (Exception e) {

            Log.e("AsyncTask_JokeTest", "DoInBackground : Timed out");
        }
    }


}
