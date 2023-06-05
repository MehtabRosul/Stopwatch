package com.example.stopwatch;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.stopwatch.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private boolean isRunning = false;
    private long pauseedoffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Chronometer chronometer = findViewById(R.id.custom_chronometer);
        Button btnstart = findViewById(R.id.btnstart);
        Button btnstop = findViewById(R.id.btnstop);
        Button btnreset = findViewById(R.id.btnreset);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseedoffset);
                    chronometer.start();
                    isRunning = true;
                }
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    chronometer.stop();
                    pauseedoffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    isRunning = false;
                }
            }
        });
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase((SystemClock.elapsedRealtime()));
                chronometer.stop();
                pauseedoffset = 0;
                isRunning = false;
            }
        });
    }
}