package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.util.StringUtil;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;


public class RegisterUserFragment extends Fragment {
    public RegisterUserFragment() {
        // Required empty public constructor
    }

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
        EditText usernameEditText = view.findViewById(R.id.register_input_username);
        EditText passwordEditText = view.findViewById(R.id.register_input_password);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = usernameEditText.getText().toString();
                String passwordValue = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(usernameValue.trim()) || TextUtils.isEmpty(passwordValue.trim())) {
                    Toast.makeText(view.getContext(), "Username dan Password harus isi", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserData newUserData = new UserData(usernameValue, passwordValue);

                userViewModel.addUserData(newUserData);
                Toast.makeText(view.getContext(), "Very nice", Toast.LENGTH_SHORT).show();

            }
        });
    }
}