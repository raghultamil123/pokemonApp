package com.example.pokemonapp.Loader;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.pokemonapp.Helper.HelperClass;
import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.PokemonInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PokemonLoader extends AsyncTaskLoader<List<Pokemon>> {
   public static WeakReference<PokemonInfo> mActivity;
    private String url="https://pokeapi.co/api/v2/pokemon/1/";
    public PokemonLoader(@NonNull Context context) {

        super(context);
        Activity activity = (PokemonInfo)context;


    }
    public PokemonLoader(PokemonInfo activity){
        super(activity);
        mActivity = new WeakReference<PokemonInfo>( activity);

    }


    @Nullable
    @Override
    public List<Pokemon> loadInBackground() {
        List<Pokemon> pokemons = new ArrayList<>();
        final List<String> pokemonInfoUrl = new ArrayList<>();
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/1/");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/4/");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/7/");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/10");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/14");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/19");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/22");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/30");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/35");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/42");
        pokemonInfoUrl.add("https://pokeapi.co/api/v2/pokemon/48");

       int j = 0;
        for(String url:pokemonInfoUrl){
            j++;
            final int i = j;
      Pokemon pokemon= HelperClass.fetchFromUrl(url);
            if(mActivity.get()!=null)
            {
                mActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Ragn");
                        double per = Double.valueOf(i)/Double.valueOf(pokemonInfoUrl.size())*100;
                    mActivity.get().notification.setContentText(""+(Double.valueOf(Math.floor(per)).intValue())+"%").setProgress(pokemonInfoUrl.size(),i,false);
                        mActivity.get(). notificationManager.notify(1, mActivity.get().notification.build());
                    }
                });

            }
      pokemons.add(pokemon);
        }

       mActivity.get().notification.setContentText("Records Taken  "+pokemonInfoUrl.size()).setProgress(0,0,false);
        mActivity.get(). notificationManager.notify(1, mActivity.get().notification.build());

      return pokemons;

    }
}
