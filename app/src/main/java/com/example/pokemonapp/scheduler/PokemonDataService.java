package com.example.pokemonapp.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class PokemonDataService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
