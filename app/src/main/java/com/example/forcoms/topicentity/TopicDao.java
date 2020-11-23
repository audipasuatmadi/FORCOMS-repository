package com.example.forcoms.topicentity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TopicDao {
    @Insert
    long addTopic(TopicData topicData);

    @Transaction
    @Query("SELECT * FROM topics ORDER BY id DESC")
    LiveData<List<TopicWithUser>> getAllTopics();

    @Transaction
//    @Query("SELECT DISTINCT * FROM topics JOIN comments ON comments.topic_id=topic_id WHERE comments.user_id=:userId")
    @Query("SELECT DISTINCT topics.id, topics.title, topics.user_id FROM topics INNER JOIN comments ON topics.id=comments.topic_id WHERE comments.user_id=:userId")
    LiveData<List<TopicWithUser>> getTopicsUserJoined(long userId);

    @Update
    void updateTopic(TopicData topicData);

    @Delete
    void deleteTopic(TopicData topicData);
}
