package com.example.forcoms.commententity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "comments")
public class CommentData {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo (name = "user_id")
    private long userId;

    @ColumnInfo (name = "topic_id")
    private long topicId;

    @ColumnInfo (name = "content")
    private String content;

    public CommentData(long userId, long topicId, String content) {
        this.userId = userId;
        this.topicId = topicId;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
