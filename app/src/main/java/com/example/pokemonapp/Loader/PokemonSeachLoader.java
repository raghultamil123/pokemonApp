package com.example.pokemonapp.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.Helper.HelperClass;

public class PokemonSeachLoader extends AsyncTaskLoader<Pokemon> {
    String search;
    public PokemonSeachLoader(@NonNull Context context,String search) {
        super(context);
        this.search = search;
    }

    @Nullable
    @Override
    public Pokemon loadInBackground() {
        String url = "https://pokeapi.co/api/v2/pokemon/"+search;
        Pokemon pokemon = HelperClass.fetchFromUrl(url);
        return pokemon;
    }
}
