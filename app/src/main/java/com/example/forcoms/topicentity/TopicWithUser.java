package com.example.forcoms.topicentity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.forcoms.userentity.UserData;

public class TopicWithUser implements Parcelable {
    @Embedded
    public TopicData topicData;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "id"
    )
    public UserData userData;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
