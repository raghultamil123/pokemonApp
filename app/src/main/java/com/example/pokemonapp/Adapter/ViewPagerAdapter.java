package com.example.pokemonapp.Adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pokemonapp.AbilitiesFragment;
import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.MovesFragment;
import com.example.pokemonapp.TypesFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Bundle bundle =  new Bundle();
    Pokemon pokemon = new Pokemon();
    public ViewPagerAdapter(FragmentManager fm, Pokemon pokemon) {
        super(fm);
        this.pokemon = pokemon;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            bundle.putParcelableArrayList("moves",pokemon.getMoves());
            MovesFragment movesFragment = new MovesFragment();
            System.out.println("bundle inside");
            movesFragment.setArguments(bundle);
            return movesFragment;
        }
        if(position==1){
            bundle.putParcelableArrayList("types",pokemon.getTypes());
            TypesFragment typesFragment = new TypesFragment();
            typesFragment.setArguments(bundle);
            return typesFragment;
        }
        if(position==2){
            bundle.putParcelableArrayList("abilities",pokemon.getAbilities());
            AbilitiesFragment abilitiesFragment = new AbilitiesFragment();
            abilitiesFragment.setArguments(bundle);
            return abilitiesFragment;
        }
        return new MovesFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "Moves";
        if(position==1)
            return "Types";
        if(position==2)
            return "Ability";
        return "enjoy";
    }

}
