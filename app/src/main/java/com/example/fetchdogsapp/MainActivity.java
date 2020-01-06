package com.example.fetchdogsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import com.example.fetchdogsapp.adapter.DogAdapter;
import com.example.fetchdogsapp.helper.CustomRVItemTouchListener;
import com.example.fetchdogsapp.helper.RecyclerViewItemClickListener;
import com.example.fetchdogsapp.models.Dogs;
import com.example.fetchdogsapp.models.DogsList;
import com.example.fetchdogsapp.retrofit.RetroClient;
import com.example.fetchdogsapp.service.ApiService;
import com.example.fetchdogsapp.utils.InternetConnection;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Dogs> dogsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDogsData();
    }

    protected void loadDogsData() {

        /**
         * Checking Internet Connection
         */
        if (InternetConnection.checkConnection(getApplicationContext())) {

            final ProgressDialog dialog;

            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Getting JSON data");
            dialog.setMessage("Please wait...");
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<DogsList> call = api.getDogs();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<DogsList>() {
                @Override
                public void onResponse(Call<DogsList> call, Response<DogsList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if(response.isSuccessful()) {

                        /**
                         * Got Successfully
                         */
                        // Log.d("LOGGG", "onResponse: " + response.body().getContacts());
                        dogsList = response.body().getDogs();

                        /**
                         * Binding that List to Adapter
                         */
                        RecyclerView recyclerDogs = (RecyclerView) findViewById(R.id.recyclerDogs);
                        DogAdapter adapter = new DogAdapter(dogsList, MainActivity.this);
                        recyclerDogs.setAdapter(adapter);
                        recyclerDogs.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        /**
                         * Add listener to every recycler view items
                         */
                        recyclerDogs.addOnItemTouchListener(new CustomRVItemTouchListener(MainActivity.this, recyclerDogs, new RecyclerViewItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Snackbar.make(findViewById(R.id.layoutMain), "onClick at position : " + position, Snackbar.LENGTH_LONG).show();

                            }

                            @Override
                            public void onLongClick(View view, int position) {
                                Snackbar.make(findViewById(R.id.layoutMain), "onLongClick at position : " + position, Snackbar.LENGTH_LONG).show();
                            }
                        }));

                    } else {
                        Snackbar.make(findViewById(R.id.layoutMain), "Something going wrong!", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<DogsList> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            Snackbar.make(findViewById(R.id.layoutMain), "Check your internet connection!", Snackbar.LENGTH_LONG).show();
        }
    }
}

