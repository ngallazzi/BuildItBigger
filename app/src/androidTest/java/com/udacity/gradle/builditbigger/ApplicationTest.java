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
    final CountDownLatch signal = new CountDownLatch(1);
    final String TAG = ApplicationTest.class.getSimpleName();
    public ApplicationTest() {
        super(Application.class);
    }
    public void testEndpointsAsyncTask() throws InterruptedException{
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        String joke = task.doInBackground(getContext());
        assertTrue(!joke.isEmpty());
    }
}