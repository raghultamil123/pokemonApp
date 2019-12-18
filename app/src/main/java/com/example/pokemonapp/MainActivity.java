package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokemonapp.scheduler.PokemonDataService;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button ;
    Button searchButton;
    Button syncButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.pokemonInfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PokemonInfo.class);
                startActivity(intent);
            }
        });
        searchButton = findViewById(R.id.pokemonSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        syncButton = findViewById(R.id.pokemonSync);
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(getApplicationContext(), PokemonDataService.class);
                JobInfo jobInfo = new JobInfo.Builder(1,componentName)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).build();
                JobScheduler jobScheduler = (JobScheduler)getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
                jobScheduler.schedule(jobInfo);

            }
        });

    }
}
