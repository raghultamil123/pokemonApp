package com.example.pokemonapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokemonapp.DTO.Result;
import com.example.pokemonapp.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailsAdapter extends ArrayAdapter {
    List<Result> entity = new ArrayList<>();
    public PokemonDetailsAdapter(@NonNull Context context,List<Result> data) {
        super(context, 0,data);
        entity = data;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView==null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_details,parent,false);
        }
        Result currentResult = entity.get(position);
        TextView move = listView.findViewById(R.id.move);
        move.setText(currentResult.getName());
        return listView;

    }
}
