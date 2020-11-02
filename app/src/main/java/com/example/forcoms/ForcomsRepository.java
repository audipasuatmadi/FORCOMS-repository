package com.example.forcoms;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.example.forcoms.commententity.CommentDao;
import com.example.forcoms.commententity.CommentData;
import com.example.forcoms.commententity.CommentWithUser;
import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.topicentity.AddTopicListener;
import com.example.forcoms.topicentity.TopicDao;
import com.example.forcoms.topicentity.TopicData;
import com.example.forcoms.topicentity.TopicWithUser;
import com.example.forcoms.userentity.UserDao;
import com.example.forcoms.userentity.UserData;

import java.util.List;

public class ForcomsRepository {
    private final UserDao userDao;
    private final TopicDao topicDao;
    private final CommentDao commentDao;
    private final LiveData<List<TopicWithUser>> allTopics;
    private LiveData<List<CommentWithUser>> allCommentsOfATopic;

    public ForcomsRepository(Application application) {
        ForcomsDB forcomsDB = ForcomsDB.getDatabase(application);
        userDao = forcomsDB.userDao();
        topicDao = forcomsDB.topicDao();
        commentDao = forcomsDB.commentDao();

        allTopics = topicDao.getAllTopics();
    }

    public void insertCommentData(CommentData commentData) {
        new InsertCommentDataAsync(commentDao).execute(commentData);
    }

    private static class InsertCommentDataAsync extends AsyncTask<CommentData, Void, Boolean> {
        private final CommentDao asyncTaskDao;

        InsertCommentDataAsync(CommentDao commentDao) {
            asyncTaskDao = commentDao;
        }

        @Override
        protected Boolean doInBackground(CommentData... commentData) {
            asyncTaskDao.addComment(commentData[0]);
            return true;
        }
    }


    public void insertTopicData(TopicData topicData, Fragment fragment) {
        new InsertTopicDataAsync(topicDao, fragment).execute(topicData);
    }

    public LiveData<List<TopicWithUser>> getAllTopics() {
        return this.allTopics;
    }

    private static class InsertTopicDataAsync extends AsyncTask<TopicData, Void, Long> {
        private final TopicDao asyncTaskDao;
        private final AddTopicListener callback;

        InsertTopicDataAsync(TopicDao topicDao, Fragment fragment) {
            asyncTaskDao = topicDao;
            callback = (AddTopicListener) fragment;
        }

        @Override
        protected Long doInBackground(TopicData... topicData) {
            return asyncTaskDao.addTopic(topicData[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            callback.onAddTopic(aLong);
        }
    }


    public interface iGetUserDataCredentials {
        void onUserDataUpdate(UserData userData);
    }

    public interface iGetChangeUserDataFeedback {
        void onUserDataChange(boolean isCompleted);
    }

    public void insertUserData(UserData userData, Context context) {
        new insertAsyncTask(userDao, context).execute(userData);
    }

    public void getUserDataWithCredentials(String username, String password, Fragment fragment) {
        new getUserDataWithCredentialsAsync(userDao, fragment).execute(username, password);
    }

    public void getUserDataWithId(long id, Context context) {
        new getUserDataWithIdAsync(userDao, context).execute(id);
    }

    public void updateUserData(UserData userData, Fragment context) {
        new UpdateUserDataAsync(userDao, context).execute(userData);
    }

    private static class UpdateUserDataAsync extends AsyncTask<UserData, Void, Boolean> {
        private final UserDao asyncTaskDao;
        private final iGetChangeUserDataFeedback callback;

        UpdateUserDataAsync(UserDao userDao, Fragment context) {
            asyncTaskDao = userDao;
            callback = (iGetChangeUserDataFeedback) context;
        }

        @Override
        @Nullable
        protected Boolean doInBackground(UserData... userData) {
            asyncTaskDao.updateUserData(userData[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            callback.onUserDataChange(aBoolean);
        }
    }



    private static class getUserDataWithIdAsync extends AsyncTask<Long, Void, UserData> {
        private final UserDao asyncTaskDao;
        iGetUserDataCredentials callback;

        getUserDataWithIdAsync(UserDao userDao, Context context) {
            asyncTaskDao = userDao;
            this.callback = (iGetUserDataCredentials) context;
        }

        @Override
        @Nullable
        protected UserData doInBackground(Long... id) {
            return asyncTaskDao.getUserFromId(id[0]);
        }

        @Override
        protected void onPostExecute(UserData userData) {
            super.onPostExecute(userData);
            callback.onUserDataUpdate(userData);
        }
    }


    private static class getUserDataWithCredentialsAsync extends AsyncTask<String, Void, UserData> {
        private final UserDao asyncTaskDao;
        iGetUserDataCredentials callback;

        getUserDataWithCredentialsAsync(UserDao userDao, Fragment selectedFragment) {
            asyncTaskDao = userDao;
            this.callback = (iGetUserDataCredentials) selectedFragment;
        }

        @Override
        @Nullable
        protected UserData doInBackground(String... credentials) {
            return asyncTaskDao.getUserWithCredentials(credentials[0], credentials[1]);
        }

        @Override
        protected void onPostExecute(UserData userData) {
            super.onPostExecute(userData);
            callback.onUserDataUpdate(userData);
        }
    }


    private static class insertAsyncTask extends AsyncTask<UserData, Void, Void> {
        private final UserDao asyncTaskDao;
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
