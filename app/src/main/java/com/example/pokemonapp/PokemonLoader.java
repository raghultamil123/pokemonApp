package com.example.pokemonapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import static com.example.pokemonapp.HelperClass.fetchFromUrl;

public class PokemonLoader extends AsyncTaskLoader<List<Pokemon>> {

    private String url="https://pokeapi.co/api/v2/pokemon/1/";
    public PokemonLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Pokemon> loadInBackground() {
        List<Pokemon> pokemons = new ArrayList<>();
      Pokemon pokemon= HelperClass.fetchFromUrl(url);
      pokemons.add(pokemon);
      return pokemons;

    }
}
