package com.example.forcoms.commententity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.forcoms.userentity.UserData;

public class CommentWithUser {
    @Embedded
    public CommentData commentData;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "id"
    )
    public UserData userData;

}
