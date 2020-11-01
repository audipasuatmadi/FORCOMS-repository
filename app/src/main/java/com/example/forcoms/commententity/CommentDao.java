package com.example.forcoms.commententity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface CommentDao {
    @Insert
    void addComment(CommentData commentData);

    @Transaction
    @Query("SELECT * FROM comments WHERE topic_id=:topicId")
    LiveData<List<CommentWithUser>> getAllComments(long topicId);
}
