package com.example.pokemonapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.pokemonapp.DTO.Result;
import com.example.pokemonapp.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailsAdapter extends ArrayAdapter {
    List<Result> entity = new ArrayList<>();
    boolean isType;
    public PokemonDetailsAdapter(@NonNull Context context,List<Result> data,boolean isType) {
        super(context, 0,data);
        entity = data;
        this.isType = isType;

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
        if(isType)
            move.setTextColor(ContextCompat.getColor(getContext(),getColor(currentResult.getName())));
        return listView;

    }
    public static int getColor(String type){
        if(type.isEmpty()||type==null)
            return R.color.colorAccent;
        switch(type){
            case "water":return R.color.blue;
            case "fire":return R.color.red;
            case "bug":return R.color.green;
            case "poison":return R.color.purple;
            case "flying":return R.color.orange;
            default:return R.color.black;
        }

    }
}
