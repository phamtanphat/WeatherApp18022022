package com.example.weatherapp18022022.presentation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weatherapp18022022.R;
import com.example.weatherapp18022022.data.model.Forecast;
import com.example.weatherapp18022022.data.model.Main;
import com.example.weatherapp18022022.data.repositories.ForecastRepository;
import com.example.weatherapp18022022.presentation.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    ImageView imgDetailState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgDetailState = findViewById(R.id.imgDetailState);

        Glide.with(this)
                .load("http://openweathermap.org/img/wn/10d@2x.png")
                .into(imgDetailState);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MainViewModel(new ForecastRepository());
            }
        }).get(MainViewModel.class);
        mainViewModel.getForecastData().observe(this, new Observer<Forecast>() {
            @Override
            public void onChanged(Forecast forecast) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Log.d("BBB",simpleDateFormat.format(forecast.getCurrentTime() * 1000) + "");
            }
        });

        mainViewModel.queryForecastByCityName("Hanoi");
    }
}
