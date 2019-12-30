package com.example.pokemonapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pokemonapp.Adapter.PokemonDetailsAdapter;
import com.example.pokemonapp.DTO.Result;

import java.util.List;

public class AbilitiesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_list,container,false);
        ListView listView  = view.findViewById(R.id.listing);
        List<Result> abilitiesResult =  getArguments().getParcelableArrayList("abilities");
        PokemonDetailsAdapter adapter = new PokemonDetailsAdapter(getActivity(),abilitiesResult);
        listView.setAdapter(adapter);
        return view;
    }
}
