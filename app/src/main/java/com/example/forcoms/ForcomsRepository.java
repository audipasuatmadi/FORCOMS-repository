package com.example.forcoms;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.forcoms.userentity.UserDao;
import com.example.forcoms.userentity.UserData;

import java.util.List;

public class ForcomsRepository {
    private UserDao userDao;
    private final LiveData<List<UserData>> allUsers;

    public ForcomsRepository(Application application) {
        ForcomsDB forcomsDB = ForcomsDB.getDatabase(application);
        userDao = forcomsDB.userDao();
        allUsers = userDao.getAllUserData();
    }

    public void insertUserData(UserData userData) {
        new insertAsyncTask(userDao).execute(userData);
    }

    public LiveData<List<UserData>> getAllUsers() {
        return allUsers;
    }

    public UserData getLoggedInData(int id) {
        return userDao.getUserFromId(id);
    }

    private static class insertAsyncTask extends AsyncTask<UserData, Void, Void> {
        private UserDao asyncTaskDao;

        insertAsyncTask(UserDao userDao) {
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(UserData... noteEntities) {
            asyncTaskDao.addUserData(noteEntities[0]);
            return null;
        }
    }

}
