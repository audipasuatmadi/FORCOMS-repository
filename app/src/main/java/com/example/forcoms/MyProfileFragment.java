package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Button changePasswordButton=view.findViewById(R.id.logout_edit);

        changePasswordButton.setOnClickListener(view1->{
            navController.navigate(R.id.changePasswordFragment);
        });

        UserDataPreference userDataPreference = new UserDataPreference(this.getContext());
        if (userDataPreference.isLoggedIn()) {
            UserViewModel userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
            UserData loggedInUser = userViewModel.getLoggedInUser();
            inflateUserDataToViews(loggedInUser, view);

            Button logoutButton = view.findViewById(R.id.logout_button);

            logoutButton.setOnClickListener(view1 -> {
                userDataPreference.setIsLoggedIn(false);
                userDataPreference.setLoggedInId(0);
                userViewModel.setLoggedInUser(null);
                gotoLoginFragment();
            });
        } else {
            gotoLoginFragment();
        }
    }

    private void gotoLoginFragment() {
        navController.popBackStack();
        navController.navigate(R.id.loginFragment);
    }

    private void inflateUserDataToViews(UserData userData, View view) {
        TextView usernameView = view.findViewById(R.id.profile_username);
        TextView passwordView = view.findViewById(R.id.profile_password);

        usernameView.setText(userData.getUsername());
        passwordView.setText(userData.getPassword());

    }
}