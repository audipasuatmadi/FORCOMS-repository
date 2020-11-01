package com.example.forcoms.topicentity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.forcoms.ForcomsRepository;

public class TopicViewModel extends AndroidViewModel {
    private final ForcomsRepository forcomsRepository;

    public TopicViewModel(@NonNull Application application) {
        super(application);
        forcomsRepository = new ForcomsRepository(application);
    }

    public void addTopic(TopicData topicData) {
        forcomsRepository.insertTopicData(topicData);
    }

}
