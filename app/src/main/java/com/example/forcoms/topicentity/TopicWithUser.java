package com.example.forcoms.topicentity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.forcoms.userentity.UserData;

public class TopicWithUser {
    @Embedded
    public TopicData topicData;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "id"
    )
    public UserData userData;
}
