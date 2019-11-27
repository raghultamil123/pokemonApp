package com.example.pokemonapp.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.pokemonapp.Helper.HelperClass;
import com.example.pokemonapp.DTO.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonLoader extends AsyncTaskLoader<List<Pokemon>> {

    private String url="https://pokeapi.co/api/v2/pokemon/1/";
    public PokemonLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Pokemon> loadInBackground() {
        List<Pokemon> pokemons = new ArrayList<>();
        List<String> pokemonInfoUrl = new ArrayList<>();
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
        for(String url:pokemonInfoUrl){
      Pokemon pokemon= HelperClass.fetchFromUrl(url);
      pokemons.add(pokemon);
        }

      return pokemons;

    }
}
