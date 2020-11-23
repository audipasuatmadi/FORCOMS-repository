package com.example.forcoms.topicentity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forcoms.ForcomsRepository;

import java.util.List;

public class TopicViewModel extends AndroidViewModel implements TopicUserJoinedListener {
    private final ForcomsRepository forcomsRepository;
    private final LiveData<List<TopicWithUser>> allTopics;
    private LiveData<List<TopicWithUser>> topicsUserJoined;

    public TopicViewModel(@NonNull Application application) {
        super(application);
        forcomsRepository = new ForcomsRepository(application);
        allTopics = forcomsRepository.getAllTopics();
    }

    public void addTopic(TopicData topicData, Fragment fragment) {
        forcomsRepository.insertTopicData(topicData, fragment);
    }

    public LiveData<List<TopicWithUser>> getAllTopics() {
        return allTopics;
    }

    public LiveData<List<TopicWithUser>> getTopicsUserJoined(long userId) {
        forcomsRepository.getTopicsUserJoined(userId, this);
        return this.topicsUserJoined;
    }

    public void updateTopic(TopicData topicData) {
        forcomsRepository.updateTopic(topicData);
    }

    public void deleteTopic(TopicData topicData) {
        forcomsRepository.deleteTopic(topicData);
    }


    @Override
    public void onGetTopicsUserJoined(LiveData<List<TopicWithUser>> topicsUserJoined) {
        this.topicsUserJoined = topicsUserJoined;
    }
}
