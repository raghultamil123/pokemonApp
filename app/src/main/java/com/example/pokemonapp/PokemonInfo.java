package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pokemonapp.Adapter.PokemonInfoAdapter;
import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.Loader.PokemonLoader;

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
        setTitle("PokemonInfo");
        ListView listView = findViewById(R.id.resultList);
        listView.setAdapter(pokemonInfoAdapter);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        getSupportLoaderManager().initLoader(1,null,this).forceLoad();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PokemonInfo.this,PokemonDetails.class);
                intent.putExtra("Pokemon",pokemons.get(position));
                startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public Loader<List<Pokemon>> onCreateLoader(int id, @Nullable Bundle args) {
        return new PokemonLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Pokemon>> loader, List<Pokemon> data) {

     pokemonInfoAdapter.clear();
     pokemons.addAll(data);
     pokemonInfoAdapter.addAll(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Pokemon>> loader) {

    }
}
