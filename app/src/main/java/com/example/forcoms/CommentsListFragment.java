package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forcoms.commententity.CommentAdapter;
import com.example.forcoms.commententity.CommentViewModel;
import com.example.forcoms.commententity.CommentWithUser;

import java.util.List;

public class CommentsListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.comments_list_recycler_view);
        final CommentAdapter commentAdapter = new CommentAdapter(this.getContext());
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        CommentViewModel commentViewModel = new ViewModelProvider(requireActivity()).get(CommentViewModel.class);
        commentViewModel.getCommentsOfATopic(1).observe(getViewLifecycleOwner(), new Observer<List<CommentWithUser>>() {
            @Override
            public void onChanged(List<CommentWithUser> commentWithUsers) {
                commentAdapter.setComments(commentWithUsers);
            }
        });
    }
}