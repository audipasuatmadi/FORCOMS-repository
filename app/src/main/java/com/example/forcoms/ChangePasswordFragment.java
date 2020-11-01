package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.util.StringUtil;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ChangePasswordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText oldPassword=view.findViewById(R.id.change_old_password);
        EditText newPassword=view.findViewById(R.id.change_new_password);
        EditText confirmNewPassword=view.findViewById(R.id.change_confirm_new_password);
        Button buttonPassword=view.findViewById(R.id.change_password_button_ganti);

        buttonPassword.setOnClickListener(view1->{
            if (TextUtils.isEmpty(newPassword.getText().toString().trim())){
                if(newPassword.getText().toString().equals(confirmNewPassword.getText().toString())){

                }

            }
        });

    }



}