package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pokemonapp.Adapter.DropDownAdapter;
import com.example.pokemonapp.DTO.DropDown;
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
     List<String> auto = new ArrayList<>();
     List<DropDown> pokemonValues = new ArrayList<>();
     boolean isSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search");

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isSelected) {
                    search = pokemonValues.get(position).getName();
                    Bundle bundle = new Bundle();
                    bundle.putString("search", search);
                    NetworkInfo networkInfo = ConnectionHelper.getConnectionInfo(getApplicationContext());
                    if (networkInfo != null && networkInfo.isConnected())
                        getSupportLoaderManager().restartLoader(1, bundle, SearchActivity.this).forceLoad();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSelected = true;
                return false;
            }
        });
        Cursor cursor = getContentResolver().query(PokemonContract.PokemonNameEntry.CONTENT_URI, null, null, null, null);
        System.out.println("gayu da" + cursor.getCount());
        while (cursor.moveToNext()) {
            String pokemonName = cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonNameEntry.COLUMN_POKEMON_NAME));
            DropDown dropDown = new DropDown();
            auto.add(pokemonName);
            dropDown.setName(pokemonName);
            dropDown.setCategory("pokemon");
            pokemonValues.add(dropDown);
        }
        DropDownAdapter adap = new DropDownAdapter(this, pokemonValues);
        spinner.setAdapter(adap);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, auto);
        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setTextColor(Color.BLACK);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Bundle bundle = new Bundle();
                bundle.putString("search",text);
                NetworkInfo networkInfo = ConnectionHelper.getConnectionInfo(getApplicationContext());
                if (networkInfo != null && networkInfo.isConnected())
                    getSupportLoaderManager().restartLoader(1, bundle, SearchActivity.this).forceLoad();
            }
        });
        System.out.println("raghu "+autoCompleteTextView.getText().toString());
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
