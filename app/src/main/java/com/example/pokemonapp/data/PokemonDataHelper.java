package com.example.pokemonapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pokemonapp.data.PokemonContract.PokemonNameEntry;
import androidx.annotation.Nullable;

public class PokemonDataHelper extends SQLiteOpenHelper {
    public static Integer DATABASE_VERSION = 1;
    public static String DATABASE_NAME="pokemon.db";
    public PokemonDataHelper(@Nullable Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          StringBuilder builder = new StringBuilder();
          builder.append("CREATE TABLE").append(PokemonNameEntry.TABLE_NAME).append("(")
                  .append(PokemonNameEntry.ID).append(" INTEGER PRIMARY KEY,").append(PokemonNameEntry.COLUMN_POKEMON_NAME)
                  .append(" TEXT NOT NULL,").append(PokemonNameEntry.COLUMN_POKEMON_URL)
                  .append(" TEXT NOT NULL");
          db.execSQL(builder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
