package com.example.forcoms.commententity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forcoms.ForcomsRepository;

import java.util.List;

public class CommentViewModel extends AndroidViewModel {
    private final ForcomsRepository repository;
    private LiveData<List<CommentWithUser>> allCommentsOfATopic;

    public CommentViewModel(@NonNull Application application) {
        super(application);
        repository = new ForcomsRepository(application);
    }

    public void addComment(CommentData commentData) {
        repository.insertCommentData(commentData);
    }

}
