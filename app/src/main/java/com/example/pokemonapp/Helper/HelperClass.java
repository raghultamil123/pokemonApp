package com.example.pokemonapp.Helper;

import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.DTO.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class HelperClass {



     public static Pokemon fetchFromUrl(String urlString){

         URL  url= createUrl(urlString);
         String jsonResponse = "";
         jsonResponse = makeHttpRequest(url);
         Pokemon pokemon = resultFromJsonResponse(jsonResponse);

         return pokemon;

     }

    private static Pokemon resultFromJsonResponse(String jsonResponse) {
          Pokemon pokemon = new Pokemon();
        List<Result> moves = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            pokemon.setBaseExperience(Integer.valueOf(jsonObject.getInt("base_experience")));
            pokemon.setWeight(Integer.valueOf(jsonObject.getInt("weight")));
            pokemon.setHeight(Integer.valueOf(jsonObject.getInt("height")));
            pokemon.setName(jsonObject.getString("name"));
            JSONArray movesArray = jsonObject.getJSONArray("moves");
            for(int i = 0;i<movesArray.length();i++){
                Result result = new Result();
             JSONObject moveObject = (JSONObject) movesArray.getJSONObject(i).get("move");
           result.setName(  moveObject.getString("name"));
            result.setUrl( moveObject.getString("url"));
            moves.add(result);
            }
           pokemon.setMoves(moves);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    public static URL createUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String readValueFromStream(InputStream inputStream) throws IOException {
        StringBuilder str = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line="";
        while((line=bufferedReader.readLine())!=null){
            str.append(line);
        }
        return str.toString();

    }
    public static String makeHttpRequest(URL url){
        String jsonResponse="";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        if(url!=null){
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.connect();
                if(urlConnection.getResponseCode() == 200){
                    inputStream =     urlConnection.getInputStream();
                }
                if(inputStream != null){
                    jsonResponse = readValueFromStream(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonResponse;
    }

}
