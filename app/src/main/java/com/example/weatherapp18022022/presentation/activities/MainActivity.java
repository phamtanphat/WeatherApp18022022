package com.example.weatherapp18022022.presentation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherapp18022022.R;
import com.example.weatherapp18022022.data.model.Forecast;
import com.example.weatherapp18022022.data.model.Main;
import com.example.weatherapp18022022.data.repositories.ForecastRepository;
import com.example.weatherapp18022022.presentation.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Log.d("BBB",forecast.toString());
            }
        });

        mainViewModel.queryForecastByCityName("Hanoi");
    }
}
