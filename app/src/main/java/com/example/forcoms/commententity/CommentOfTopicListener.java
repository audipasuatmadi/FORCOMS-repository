package com.example.forcoms.commententity;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface CommentOfTopicListener {
    void topicLiveDataChangeListener(LiveData<List<CommentWithUser>> commentsOfATopic);
}
