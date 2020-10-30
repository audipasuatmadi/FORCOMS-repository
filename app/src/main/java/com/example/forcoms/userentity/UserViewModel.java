package com.example.forcoms.userentity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forcoms.ForcomsRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private ForcomsRepository noteRepository;
    private final LiveData<List<UserData>> allUserData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new ForcomsRepository(application);
        allUserData = noteRepository.getAllUsers();
    }

    public LiveData<List<UserData>> getAllUserData() {
        return allUserData;
    }

    public UserData getLoggedInUserData(int id) {
        return noteRepository.getLoggedInData(id);
    }

    public void addUserData(UserData userData, Context context) {
        noteRepository.insertUserData(userData, context);
    }
}
