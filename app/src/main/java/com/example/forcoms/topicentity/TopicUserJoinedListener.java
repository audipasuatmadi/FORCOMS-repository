package com.example.forcoms.topicentity;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface TopicUserJoinedListener {
    void onGetTopicsUserJoined(LiveData<List<TopicWithUser>> topicsUserJoined);
}
