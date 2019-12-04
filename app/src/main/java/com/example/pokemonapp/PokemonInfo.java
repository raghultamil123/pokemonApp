package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null && networkInfo.isConnected()) {

            Toast.makeText(this,"connected via "+networkInfo.getTypeName(),Toast.LENGTH_LONG).show();
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(PokemonInfo.this, PokemonDetails.class);
                    intent.putExtra("Pokemon", pokemons.get(position));
                    startActivity(intent);
                }
            });
        }
        else{
            TextView view = (TextView)findViewById(R.id.empty_view);
            view.setText("No internet connection");
            listView.setEmptyView(view);
        }


    }

    @NonNull
    @Override
    public Loader<List<Pokemon>> onCreateLoader(int id, @Nullable Bundle args) {
        return new PokemonLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Pokemon>> loader, List<Pokemon> data) {
     pokemons.addAll(data);
     pokemonInfoAdapter.clear();

     pokemonInfoAdapter.addAll(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Pokemon>> loader) {

    }
}
