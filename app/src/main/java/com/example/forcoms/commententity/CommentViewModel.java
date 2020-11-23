package com.example.forcoms.commententity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forcoms.ForcomsRepository;

import java.util.List;

public class CommentViewModel extends AndroidViewModel implements CommentOfTopicListener {
    private final ForcomsRepository repository;
    private LiveData<List<CommentWithUser>> allCommentsOfATopic;
    private CommentOfTopicListener callback;
    private long topicId;

    public CommentViewModel(@NonNull Application application) {
        super(application);
        repository = new ForcomsRepository(application);
    }

    public void addComment(CommentData commentData) {
        repository.insertCommentData(commentData);
    }
    public void getCommentsOfATopic(long topicId, Fragment fragment) {
        repository.getAllCommentsOfATopic(topicId, this);
        this.callback = (CommentOfTopicListener) fragment;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getTopicId() {
        return this.topicId;
    }

    public void updateComment(CommentData commentData) {
        repository.updateComment(commentData);
    }

    public void deleteComment(CommentData commentData) {
        repository.deleteComment(commentData);
    }

    @Override
    public void topicLiveDataChangeListener(LiveData<List<CommentWithUser>> commentsOfATopic) {
        allCommentsOfATopic = commentsOfATopic;
        callback.topicLiveDataChangeListener(allCommentsOfATopic);
    }
}
