package com.example.pokemonapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.pokemonapp.data.PokemonContract.PokemonNameEntry;

public class PokemonDataProvider extends ContentProvider {
    private PokemonDataHelper pokemonDataHelper;
   private static final int POKEMON_NAME_LIST = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI("com.example.pokemonapp", PokemonContract.PokemonNameEntry.CONTENT_URI.getPath(),POKEMON_NAME_LIST);
    }
    @Override
    public boolean onCreate() {
          pokemonDataHelper = new PokemonDataHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase = pokemonDataHelper.getReadableDatabase();
        Cursor cursor;
        int value = uriMatcher.match(uri);
        switch (value){
            case POKEMON_NAME_LIST:cursor = sqLiteDatabase.query(PokemonNameEntry.TABLE_NAME,null,null,null,null,null,null);
                                   break;
            default:throw new IllegalStateException("hi");
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int value = uriMatcher.match(uri);
        switch (value){
            case POKEMON_NAME_LIST:return PokemonNameEntry.CONTENT_LIST_TYPE;
            default:throw new IllegalStateException("hi");

        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
       int value =uriMatcher.match(uri);
       switch(value) {
           case POKEMON_NAME_LIST:   return insertPokemon(uri, values);
           default:throw new IllegalStateException("hello");
       }

    }

    private Uri insertPokemon(Uri uri, ContentValues values) {
        SQLiteDatabase sqLiteDatabase = pokemonDataHelper.getWritableDatabase();
        String name= values.getAsString(PokemonNameEntry.COLUMN_POKEMON_NAME);
        if(name==null){
            return null;
        }
        long id = sqLiteDatabase.insert(PokemonNameEntry.TABLE_NAME,null,values);
        return ContentUris.withAppendedId(uri, id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
