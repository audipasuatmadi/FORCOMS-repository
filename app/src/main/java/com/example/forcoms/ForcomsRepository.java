package com.example.forcoms;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.forcoms.sharedpreferences.UserDataPreference;
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

    public void insertUserData(UserData userData, Context context) {
        new insertAsyncTask(userDao, context).execute(userData);
    }

    public LiveData<List<UserData>> getAllUsers() {
        return allUsers;
    }

    public UserData getLoggedInData(int id) {
        return userDao.getUserFromId(id);
    }

    private static class insertAsyncTask extends AsyncTask<UserData, Void, Void> {
        private UserDao asyncTaskDao;
        private final Context context;

        insertAsyncTask(UserDao userDao, Context context) {
            asyncTaskDao = userDao;
            this.context = context;
        }

        @Override
        protected Void doInBackground(UserData... noteEntities) {
            long userId = asyncTaskDao.addUserData(noteEntities[0]);
            UserDataPreference userDataPreference = new UserDataPreference(this.context);
            userDataPreference.setLoggedInId(userId);
            return null;
        }
    }

}
