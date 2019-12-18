package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.Helper.ConnectionHelper;
import com.example.pokemonapp.Loader.PokemonSeachLoader;
import com.example.pokemonapp.data.PokemonContract;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Pokemon>
 {

    Pokemon pokemon;
    Spinner spinner;
    String search="";
     String [] value = {"ditto","bulbasaur","voltorb"};
     List<String> pokemonValues = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search");
       /* ContentValues values = new ContentValues();
        values.put(PokemonContract.PokemonNameEntry.ID,1);
        values.put(PokemonContract.PokemonNameEntry.COLUMN_POKEMON_NAME,"bulbasaur");
        values.put(PokemonContract.PokemonNameEntry.COLUMN_POKEMON_URL,"https://pokeapi.co/api/v2/pokemon/1/");
         getContentResolver().insert(PokemonContract.PokemonNameEntry.CONTENT_URI,values);*/
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search = pokemonValues.get(position);
               Bundle bundle = new Bundle();
               bundle.putString("search",search);
                NetworkInfo networkInfo = ConnectionHelper.getConnectionInfo(getApplicationContext());
               if(networkInfo !=null && networkInfo.isConnected())
                getSupportLoaderManager().restartLoader(1, bundle, SearchActivity.this).forceLoad();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Cursor cursor = getContentResolver().query(PokemonContract.PokemonNameEntry.CONTENT_URI,null,null,null,null);
        System.out.println("gayu da"+cursor.getCount());
        while(cursor.moveToNext()) {
           String pokemonName =  cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonNameEntry.COLUMN_POKEMON_NAME));
           System.out.println(pokemonName);
           pokemonValues.add(pokemonName);
        }
        ArrayAdapter adap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pokemonValues);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adap);

    }

     @NonNull
     @Override
     public Loader<Pokemon> onCreateLoader(int id, @Nullable Bundle args) {

        if(args!=null){
            search = args.getString("search");
        }
        return new PokemonSeachLoader(this,search);
     }

     @Override
     public void onLoadFinished(@NonNull Loader<Pokemon> loader, Pokemon data) {
          Intent intent = new Intent(SearchActivity.this,PokemonDetails.class);
          intent.putExtra("Pokemon",data);
          startActivity(intent);
     }

     @Override
     public void onLoaderReset(@NonNull Loader<Pokemon> loader) {

     }
 }
