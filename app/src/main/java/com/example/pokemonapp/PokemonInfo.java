package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokemonapp.Adapter.PokemonInfoAdapter;
import com.example.pokemonapp.DTO.Pokemon;
import com.example.pokemonapp.Helper.ConnectionHelper;
import com.example.pokemonapp.Helper.HelperClass;
import com.example.pokemonapp.Loader.PokemonLoader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PokemonInfo extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Pokemon>> {
   Pokemon pokemon;
    TextView textView;
    PokemonInfoAdapter pokemonInfoAdapter;
    List<Pokemon> pokemons;
   public NotificationManager notificationManager;
  public  NotificationChannel notificationChannel;
  public  NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "netowork");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);
        PokemonLoader.mActivity = new WeakReference<PokemonInfo>(this);
        pokemons = new ArrayList<>();
        pokemonInfoAdapter = new PokemonInfoAdapter(this,pokemons);
        setTitle("PokemonInfo");
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "netowork");
        ListView listView = findViewById(R.id.resultList);
        listView.setAdapter(pokemonInfoAdapter);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        ConnectionHelper.getConnectionInfo(getApplicationContext());
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(ConnectionHelper.getConnectionInfo(getApplicationContext())!=null &&ConnectionHelper.getConnectionInfo(getApplicationContext()).isConnected()) {

           // Toast.makeText(this,"connected via "+networkInfo.getTypeName(),Toast.LENGTH_LONG).show();
            createNotification("Connected via "+networkInfo.getTypeName().toLowerCase());
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(PokemonInfo.this, PokemonDetails.class);
                    intent.putExtra("Pokemon", pokemons.get(position));
                    startActivity(intent);
                }
            });
        }
        else{
            createNotification("No internet connection");
            TextView view = (TextView)findViewById(R.id.empty_view);
            view.setText("No internet connection");
            listView.setEmptyView(view);
        }


    }

    private void createNotification(String connectionText) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             notificationChannel = new NotificationChannel("netowork" ,"internet_connection", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
           Intent intent =new Intent(this,PokemonInfo.class);
           //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
          //  PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
             notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
             notification = new NotificationCompat.Builder(this, "netowork")
                    .setContentTitle("Connection")
                    .setContentText(connectionText)
                   // .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher_round);
       /*     int progressMax = 100;
            int progressMin = 0;
            notification.setProgress(progressMax,progressMin,false);*/
            notificationManager.notify(1, notification.build());
           // notification.setContentText("completed").setProgress(0,0,false);



        }
    }

    @NonNull
    @Override
    public Loader<List<Pokemon>> onCreateLoader(int id, @Nullable Bundle args) {
        createNotification("Fetching records from server Please Wait...");
        return new PokemonLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Pokemon>> loader, List<Pokemon> data) {
        notification.setContentText("Completed Thanks for your cooperation").setProgress(0,0,false);
        notificationManager.notify(1, notification.build());
     pokemons.addAll(data);
     pokemonInfoAdapter.clear();

     pokemonInfoAdapter.addAll(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Pokemon>> loader) {

    }

}
