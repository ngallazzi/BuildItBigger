package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import junit.framework.Assert;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    String mJoke;
    CountDownLatch signal = null;
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testEndpointsAsyncTask() throws InterruptedException{

        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.setListener(new EndpointsAsyncTask.EndpointsAsyncTaskListener() {
            @Override
            public void onTaskStarted() {

            }

            @Override
            public void onExecutionFinished(String joke) {
                mJoke = joke;
                signal.countDown();
            }
        });
        task.execute();
        signal.await();
        assertTrue(!mJoke.isEmpty());
    }
}