package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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


public class ChangePasswordFragment extends Fragment implements ForcomsRepository.iGetChangeUserDataFeedback {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password2, container, false);
    }

    View currentView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText oldPassword=view.findViewById(R.id.change_old_password);
        EditText newPassword=view.findViewById(R.id.change_new_password);
        EditText confirmNewPassword=view.findViewById(R.id.change_confirm_new_password);
        Button buttonPassword=view.findViewById(R.id.change_password_button_ganti);

        UserViewModel userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);

        currentView = view;

        buttonPassword.setOnClickListener(view1->{
            String newPasswordValue = newPassword.getText().toString();
            String confirmNewPasswordValue = confirmNewPassword.getText().toString();

            if (!TextUtils.isEmpty(newPasswordValue.trim())){
                if (newPasswordValue.equals(confirmNewPasswordValue)){
                    String oldPasswordValue = oldPassword.getText().toString();
                    UserData loggedInUser = userViewModel.getLoggedInUser();

                    if (oldPasswordValue.equals(loggedInUser.getPassword())) {
                        loggedInUser.setPassword(newPasswordValue);
                        userViewModel.updateUserData(loggedInUser, this);
                    } else {
                        Toast.makeText(this.getContext(), "password lama tidak benar", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this.getContext(), "password baru tidak match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onUserDataChange(boolean isCompleted) {
        if (isCompleted) {
            Toast.makeText(this.getContext(), "password berhasil diganti", Toast.LENGTH_SHORT).show();
            NavController navController = Navigation.findNavController(currentView);
            navController.popBackStack();
            navController.navigate(R.id.navigation_profile);
        }
    }
}