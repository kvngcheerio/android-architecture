package com.ojaexpress.merchant.data.network.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;

public class StoreFirebaseJobService extends JobService {
    public StoreFirebaseJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
