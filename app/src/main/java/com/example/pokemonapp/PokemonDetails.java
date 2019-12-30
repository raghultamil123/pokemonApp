package com.example.pokemonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pokemonapp.Adapter.PokemonDetailsAdapter;
import com.example.pokemonapp.Adapter.ViewPagerAdapter;
import com.example.pokemonapp.DTO.Pokemon;
import com.google.android.material.tabs.TabLayout;

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
        TextView heightView = (TextView)findViewById(R.id.pokemon_height);
        heightView.setText(pokemon.getHeight().toString());
        TabLayout tabLayout = findViewById(R.id.tab);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),pokemon);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
