package com.example.pokemonapp.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ContentValues;
import android.os.AsyncTask;

import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.DTO.Result;
import com.example.pokemonapp.Helper.HelperClass;
import com.example.pokemonapp.data.PokemonContract;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PokemonDataService extends JobService {
    JobParameters parameters;
    BackgroundSync sync;
    @Override
    public boolean onStartJob(JobParameters params) {
        System.out.println("gayu");
        int id = getContentResolver().delete(PokemonContract.PokemonNameEntry.CONTENT_URI,null,null);
        System.out.println("rows deleted"+id);
         this.parameters = params;
         sync = new BackgroundSync();
        try {
            sync.execute(new URL("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {



        return true;
    }
    private class BackgroundSync extends AsyncTask<URL,Void, List<Result>>{

        @Override
        protected List<Result> doInBackground(URL... urls) {
            List<Pokemon> pokemons = new ArrayList<>();
            List<Result> results = HelperClass.fetchPokemonName(urls[0].toString());
            return results;
        }

        @Override
        protected void onPostExecute(List<Result> pokemons) {
            for(int i = 1;i<= pokemons.size();i++){
                ContentValues values = new ContentValues();
        values.put(PokemonContract.PokemonNameEntry.ID,i);
        values.put(PokemonContract.PokemonNameEntry.COLUMN_POKEMON_NAME,pokemons.get(i-1).getName());
        values.put(PokemonContract.PokemonNameEntry.COLUMN_POKEMON_URL,pokemons.get(i-1).getUrl());
         getContentResolver().insert(PokemonContract.PokemonNameEntry.CONTENT_URI,values);

            }
            super.onPostExecute(pokemons);


        }

    }
}
