package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pokemonapp.DTO.Pokemon;

public class PokemonDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        setTitle("Pokemon Details");
        Pokemon pokemon =(Pokemon) getIntent().getSerializableExtra("Pokemon");
        TextView nameView = (TextView)findViewById(R.id.pokemon_name);
        nameView.setText(pokemon.getName());
        TextView weightView = (TextView)findViewById(R.id.pokemon_weight);
        weightView.setText(pokemon.getWeight().toString());
        TextView experienceView = (TextView)findViewById(R.id.pokemon_base_experience);
        experienceView.setText(pokemon.getBaseExperience().toString());

    }
}
