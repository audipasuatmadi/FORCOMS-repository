package com.example.forcoms.topicentity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface TopicDao {
    @Insert
    void addTopic(TopicData topicData);

    @Transaction
    @Query("SELECT * FROM topics")
    LiveData<List<TopicWithUser>> getAllTopics();
}