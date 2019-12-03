package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pokemonapp.DTO.Pokemon;

public class PokemonDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        Pokemon pokemon =(Pokemon) getIntent().getSerializableExtra("Pokemon");
        System.out.println("raghul"+pokemon.getName());

    }
}
