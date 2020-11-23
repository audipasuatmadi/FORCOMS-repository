package com.example.forcoms;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forcoms.commententity.CommentDao;
import com.example.forcoms.commententity.CommentData;
import com.example.forcoms.commententity.CommentOfTopicListener;
import com.example.forcoms.commententity.CommentWithUser;
import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.topicentity.AddTopicListener;
import com.example.forcoms.topicentity.TopicDao;
import com.example.forcoms.topicentity.TopicData;
import com.example.forcoms.topicentity.TopicUserJoinedListener;
import com.example.forcoms.topicentity.TopicWithUser;
import com.example.forcoms.userentity.AddUserDataListener;
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

    public void getAllCommentsOfATopic(long topicId, AndroidViewModel viewModel) {
        new GetCommentsOfATopicAsync(commentDao, viewModel).execute(topicId);
    }

    public void updateComment(CommentData commentData) {
        new UpdateCommentAsync(commentDao).execute(commentData);
    }

    public void deleteComment(CommentData commentData) {
        new DeleteCommentAsync(commentDao).execute(commentData);
    }

    private static class DeleteCommentAsync extends AsyncTask<CommentData, Void, Void> {
        private final CommentDao asyncTaskDao;

        DeleteCommentAsync(CommentDao commentDao) {
            asyncTaskDao = commentDao;
        }

        @Override
        protected Void doInBackground(CommentData... commentData) {
            asyncTaskDao.deleteData(commentData[0]);
            return null;
        }
    }

    private static class UpdateCommentAsync extends AsyncTask<CommentData, Void, Void> {
        private final CommentDao asyncTaskDao;

        UpdateCommentAsync(CommentDao commentDao) {
            asyncTaskDao = commentDao;
        }

        @Override
        protected Void doInBackground(CommentData... commentData) {
            asyncTaskDao.updateData(commentData[0]);
            return null;
        }
    }


    private static class GetCommentsOfATopicAsync extends AsyncTask<Long, Void, LiveData<List<CommentWithUser>>> {
        private final CommentDao asyncTaskDao;
        private final CommentOfTopicListener callback;

        GetCommentsOfATopicAsync(CommentDao commentDao, AndroidViewModel androidViewModel) {
            asyncTaskDao = commentDao;
            callback = (CommentOfTopicListener) androidViewModel;
        }

        @Override
        protected LiveData<List<CommentWithUser>> doInBackground(Long... topicId) {
            return asyncTaskDao.getAllComments(topicId[0]);
        }


        @Override
        protected void onPostExecute(LiveData<List<CommentWithUser>> listLiveData) {
            super.onPostExecute(listLiveData);
            callback.topicLiveDataChangeListener(listLiveData);
        }
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

    public void updateTopic(TopicData topicData) {
        new UpdateTopicAsync(topicDao).execute(topicData);
    }

    public void deleteTopic(TopicData topicData) {
        new DeleteTopicAsync(topicDao).execute(topicData);
    }

    public void getTopicsUserJoined(long userId, AndroidViewModel androidViewModel) {
        new GetTopicsUserJoinedAsync(topicDao, androidViewModel).execute(userId);
    }

    private static class GetTopicsUserJoinedAsync extends AsyncTask<Long, Void, LiveData<List<TopicWithUser>>> {
        private final TopicDao asyncTaskDao;
        private final TopicUserJoinedListener callback;

        GetTopicsUserJoinedAsync(TopicDao topicDao, AndroidViewModel androidViewModel) {
            asyncTaskDao = topicDao;
            callback = (TopicUserJoinedListener) androidViewModel;
        }

        @Override
        protected LiveData<List<TopicWithUser>> doInBackground(Long... topicId) {
            return asyncTaskDao.getTopicsUserJoined(topicId[0]);
        }


        @Override
        protected void onPostExecute(LiveData<List<TopicWithUser>> listLiveData) {
            super.onPostExecute(listLiveData);
            callback.onGetTopicsUserJoined(listLiveData);
        }
    }

    private static class DeleteTopicAsync extends AsyncTask<TopicData, Void, Void> {
        private final TopicDao asyncTaskDao;

        DeleteTopicAsync(TopicDao topicDao) {
            asyncTaskDao = topicDao;
        }

        @Override
        protected Void doInBackground(TopicData... topicData) {
            asyncTaskDao.deleteTopic(topicData[0]);
            return null;
        }
    }


    private static class UpdateTopicAsync extends AsyncTask<TopicData, Void, Void> {
        private final TopicDao asyncTaskDao;

        UpdateTopicAsync(TopicDao topicDao) {
            asyncTaskDao = topicDao;
        }

        @Override
        protected Void doInBackground(TopicData... topicData) {
            asyncTaskDao.updateTopic(topicData[0]);
            return null;
        }
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

    public void insertUserData(UserData userData, Fragment fragment) {
        new insertAsyncTask(userDao, fragment).execute(userData);
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


    private static class insertAsyncTask extends AsyncTask<UserData, Void, Long> {
        private final UserDao asyncTaskDao;
        private final AddUserDataListener callback;

        insertAsyncTask(UserDao userDao, Fragment fragment) {
            asyncTaskDao = userDao;
            this.callback = (AddUserDataListener) fragment;
        }

        @Override
        protected Long doInBackground(UserData... noteEntities) {
            return asyncTaskDao.addUserData(noteEntities[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            callback.onUserDataAdded(aLong);
        }
    }

}
