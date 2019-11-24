package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PokemonInfo extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Pokemon>> {
   Pokemon pokemon;
    TextView textView;
    PokemonInfoAdapter pokemonInfoAdapter;
    List<Pokemon> pokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);

        pokemons = new ArrayList<>();
        pokemonInfoAdapter = new PokemonInfoAdapter(this,pokemons);
        ListView listView = findViewById(R.id.resultList);
        listView.setAdapter(pokemonInfoAdapter);
        getSupportLoaderManager().initLoader(1,null,this).forceLoad();


    }

    @NonNull
    @Override
    public Loader<List<Pokemon>> onCreateLoader(int id, @Nullable Bundle args) {
        return new PokemonLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Pokemon>> loader, List<Pokemon> data) {

     pokemonInfoAdapter.clear();
     pokemonInfoAdapter.addAll(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Pokemon>> loader) {

    }
}
