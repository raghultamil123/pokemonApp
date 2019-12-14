package com.example.pokemonapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class PokemonContract {

    private static String CONTENT_AUTHORITY = "com.example.pokemonapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_POKEMON_NAME = "pokemon_names";

    public static final class PokemonNameEntry implements BaseColumns {
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POKEMON_NAME;
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_POKEMON_NAME);

        public static final String TABLE_NAME="pokemon_names";
        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_POKEMON_NAME="pokemon_name";
        public static final String COLUMN_POKEMON_URL = "pokemon_url";



    }
}
