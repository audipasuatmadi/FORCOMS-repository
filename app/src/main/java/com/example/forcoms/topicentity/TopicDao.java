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
    long addTopic(TopicData topicData);

    @Transaction
    @Query("SELECT * FROM topics ORDER BY id DESC")
    LiveData<List<TopicWithUser>> getAllTopics();
}
