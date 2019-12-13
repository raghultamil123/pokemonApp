package com.example.pokemonapp.data;

import android.provider.BaseColumns;

public class PokemonContract {

    public static final class PokemonNameEntry implements BaseColumns {

        public static final String TABLE_NAME="pokemon_names";
        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_POKEMON_NAME="pokemon_name";
        public static final String COLUMN_POKEMON_URL = "pokemon_url";



    }
}
