package com.example.forcoms.userentity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forcoms.ForcomsRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final ForcomsRepository forcomsRepository;

    private UserData loggedInUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        forcomsRepository = new ForcomsRepository(application);
    }


    public UserData getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserData userData) {
        this.loggedInUser = userData;
    }

    public void getUserDataWithCredentials(String username, String password, Fragment fragment) {
        forcomsRepository.getUserDataWithCredentials(username, password, fragment);
    }

    public void addUserData(UserData userData, Fragment fragment) {
        forcomsRepository.insertUserData(userData, fragment);
    }

    public void getUserDataWithId(long id, Context context) {
        forcomsRepository.getUserDataWithId(id, context);
    }

    public void updateUserData(UserData userData, Fragment context) {
        forcomsRepository.updateUserData(userData, context);
    }
}
