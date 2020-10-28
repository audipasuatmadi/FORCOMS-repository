package com.example.forcoms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.forcoms.sharedpreferences.FirstOpenedPreference;

public class MainActivity extends AppCompatActivity {

    private FirstOpenedPreference firstOpenedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkIfHasOpenedBefore()) {
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkIfHasOpenedBefore() {
        firstOpenedPreference = new FirstOpenedPreference(this.getApplicationContext());
        return firstOpenedPreference.hasOpenedBefore();
    }
}