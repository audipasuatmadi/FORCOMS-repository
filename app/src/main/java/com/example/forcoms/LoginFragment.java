package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;

import java.util.Objects;


public class LoginFragment extends Fragment implements ForcomsRepository.iGetUserDataCredentials {

    private NavController navController;
    private UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        Button gotoRegisterButton = view.findViewById(R.id.button_register);
        Button loginButton = view.findViewById(R.id.button_login);
        EditText usernameEditText = view.findViewById(R.id.login_input_username);
        EditText passwordEditText = view.findViewById(R.id.login_input_password);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        loginButton.setOnClickListener(view1 -> {
            String usernameValue = usernameEditText.getText().toString();
            String passwordValue = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(usernameValue.trim()) || TextUtils.isEmpty(passwordValue.trim())) {
                Toast.makeText(view.getContext(), "username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            userViewModel.getUserDataWithCredentials(usernameValue, passwordValue, this);

        });

        gotoRegisterButton.setOnClickListener(view1 -> {
            navController.popBackStack();
            navController.navigate(R.id.registerUserFragment);
        });

    }

    @Override
    public void onUserDataUpdate(UserData userData) {
        if (userData != null) {
            UserDataPreference userDataPreference = new UserDataPreference(this.getContext());
            userDataPreference.setLoggedInId(userData.getId());
            userDataPreference.setIsLoggedIn(true);
            userViewModel.setLoggedInUser(userData);
            Toast.makeText(this.getContext(), "selamat datang " + userDataPreference.getLoggedId(), Toast.LENGTH_SHORT).show();

            navController.popBackStack();
            navController.navigate(R.id.navigation_profile);

        } else {
            Toast.makeText(this.getContext(), "akun tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}