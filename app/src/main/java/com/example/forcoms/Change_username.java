package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;

public class Change_username extends Fragment {

    public Change_username() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_username, container, false);
    }

    View currentView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText oldUsername=view.findViewById(R.id.username_old);
        EditText newUsername=view.findViewById(R.id.username_new);
        Button buttonChange=view.findViewById(R.id.username_button_change);

        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        currentView=view;
        buttonChange.setOnClickListener(view1->{
            String newUsernameValue = newUsername.getText().toString();
            String confirmNewUsernameValue = oldUsername.getText().toString();

            if (!TextUtils.isEmpty(newUsernameValue.trim())){
                if (newUsernameValue.equals(confirmNewUsernameValue)){
                    String oldUsernameValue = oldUsername.getText().toString();
                    UserData loggedInUser = userViewModel.getLoggedInUser();

                    if (oldUsernameValue.equals(loggedInUser.getUsername())) {
                        loggedInUser.setPassword(newUsernameValue);
                        userViewModel.updateUserData(loggedInUser, this);
                    } else {
                        Toast.makeText(this.getContext(), "username lama tidak benar", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this.getContext(), "username baru tidak match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onUserDataChange(boolean isCompleted) {
        if (isCompleted) {
            Toast.makeText(this.getContext(), "username berhasil diganti", Toast.LENGTH_SHORT).show();
            NavController navController = Navigation.findNavController(currentView);
            navController.popBackStack();
            navController.navigate(R.id.navigation_profile);
        }
    }
}