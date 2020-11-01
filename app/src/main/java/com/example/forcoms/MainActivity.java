package com.example.forcoms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.forcoms.sharedpreferences.FirstOpenedPreference;
import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ForcomsRepository.iGetUserDataCredentials {

    private UserViewModel userViewModel;
    UserDataPreference userDataPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        if (!checkIfHasOpenedBefore()) {
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
        }

        userDataPreference = new UserDataPreference(this);
        if (userDataPreference.isLoggedIn()) {
            long loggedInId = userDataPreference.getLoggedId();
            userViewModel.getUserDataWithId(loggedInId, this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar_bottom);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_profile, R.id.navigation_subscriptions, R.id.navigation_profile).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private boolean checkIfHasOpenedBefore() {
        FirstOpenedPreference firstOpenedPreference = new FirstOpenedPreference(this.getApplicationContext());
        return firstOpenedPreference.hasOpenedBefore();
    }


    @Override
    public void onUserDataUpdate(UserData userData) {
        if (userData == null) {
            userDataPreference.setLoggedInId(0);
            userDataPreference.setIsLoggedIn(false);
            Toast.makeText(this, "user gagal ditemukan", Toast.LENGTH_SHORT).show();

        } else {
            userViewModel.setLoggedInUser(userData);
        }
    }
}