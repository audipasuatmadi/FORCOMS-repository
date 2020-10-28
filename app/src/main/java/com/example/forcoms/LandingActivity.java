package com.example.forcoms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.forcoms.sharedpreferences.FirstOpenedPreference;

public class LandingActivity extends AppCompatActivity {

    private FirstOpenedPreference firstOpenedPreference;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        firstOpenedPreference = new FirstOpenedPreference(this.getApplicationContext());
        continueButton = findViewById(R.id.button_activity_landing_continue);



        continueButton.setOnClickListener(view -> {
            firstOpenedPreference.setHasOpenedBefore(true);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        firstOpenedPreference.setHasOpenedBefore(true);
    }
}