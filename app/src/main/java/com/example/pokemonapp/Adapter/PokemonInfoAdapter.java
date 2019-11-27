package com.example.pokemonapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonInfoAdapter extends ArrayAdapter {
    List<Pokemon> pokemonInfos = new ArrayList<>();
    public PokemonInfoAdapter(@NonNull Context context,List<Pokemon> datas) {
        super(context, 0,datas);
        pokemonInfos = datas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_pokemon,parent,false);
        }
        Pokemon currentPokemon = pokemonInfos.get(position);
        TextView nameView = (TextView)listView.findViewById(R.id.pokemonName);
        nameView.setText(currentPokemon.getName());
        TextView weightView = (TextView)listView.findViewById(R.id.pokemonWeight);
        weightView.setText(""+currentPokemon.getWeight());
        return listView;


    }
}
