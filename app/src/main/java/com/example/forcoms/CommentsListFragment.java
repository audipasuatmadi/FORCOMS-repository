package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.forcoms.commententity.CommentAdapter;
import com.example.forcoms.commententity.CommentOfTopicListener;
import com.example.forcoms.commententity.CommentViewModel;
import com.example.forcoms.commententity.CommentWithUser;

import java.util.List;

public class CommentsListFragment extends Fragment implements CommentOfTopicListener {

    LiveData<List<CommentWithUser>> allCommentsOfATopic;
    private CommentAdapter commentAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button gotoAddCommentNav = view.findViewById(R.id.comments_list_button);

        RecyclerView recyclerView = view.findViewById(R.id.comments_list_recycler_view);
        commentAdapter = new CommentAdapter(this.getContext());
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        long topicId = getArguments().getLong("TOPIC_ID");

        CommentViewModel commentViewModel = new ViewModelProvider(requireActivity()).get(CommentViewModel.class);
        commentViewModel.getCommentsOfATopic(topicId, this);

        gotoAddCommentNav.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.addCommentFragment, getArguments());
        });

    }

    @Override
    public void topicLiveDataChangeListener(LiveData<List<CommentWithUser>> commentsOfATopic) {
        allCommentsOfATopic = commentsOfATopic;
        allCommentsOfATopic.observe(getViewLifecycleOwner(), new Observer<List<CommentWithUser>>() {
            @Override
            public void onChanged(List<CommentWithUser> commentWithUsers) {
                commentAdapter.setComments(commentWithUsers);
            }
        });
    }
}