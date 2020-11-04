package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.util.StringUtil;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.userentity.AddUserDataListener;
import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;


public class RegisterUserFragment extends Fragment implements AddUserDataListener {
    public RegisterUserFragment() {
        // Required empty public constructor
    }

    private NavController navController;
    private UserData processingUserData;
    private UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button registerButton = view.findViewById(R.id.register_button_register);
        Button gotoLoginPageButton = view.findViewById(R.id.register_button_login);
        EditText usernameEditText = view.findViewById(R.id.register_input_username);
        EditText passwordEditText = view.findViewById(R.id.register_input_password);
        navController = Navigation.findNavController(view);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        usernameEditText.setFilters(new InputFilter[] {
                new InputFilter.AllCaps() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        return String.valueOf(source).toLowerCase().replace(" ", "");
                    }
                }
        });

        Fragment fragment = this;

        registerButton.setOnClickListener(view12 -> {
            String usernameValue = usernameEditText.getText().toString();
            String passwordValue = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(usernameValue.trim()) || TextUtils.isEmpty(passwordValue.trim())) {
                Toast.makeText(view12.getContext(), "username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            processingUserData = new UserData(usernameValue, passwordValue);

            userViewModel.addUserData(processingUserData, fragment);
        });

        gotoLoginPageButton.setOnClickListener(view1 -> {
            navController.popBackStack();
            navController.navigate(R.id.loginFragment);
        });
    }

    @Override
    public void onUserDataAdded(long userId) {
        UserDataPreference userDataPreference = new UserDataPreference(this.getContext());
        userDataPreference.setIsLoggedIn(true);
        userDataPreference.setLoggedInId(userId);

        processingUserData.setId(userId);
        userViewModel.setLoggedInUser(processingUserData);

        Toast.makeText(requireContext(), "Selamat bergabung " + processingUserData.getUsername() + "!", Toast.LENGTH_SHORT).show();

        navController.popBackStack();
    }
}