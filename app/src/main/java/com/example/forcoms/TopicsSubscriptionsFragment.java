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

import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.topicentity.TopicAdapter;
import com.example.forcoms.topicentity.TopicUserJoinedListener;
import com.example.forcoms.topicentity.TopicViewModel;
import com.example.forcoms.topicentity.TopicWithUser;

import java.util.List;

public class TopicsSubscriptionsFragment extends Fragment implements TopicUserJoinedListener {
    private TopicAdapter topicAdapter;
    private boolean visited = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topics_subscriptions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        UserDataPreference userDataPreference = new UserDataPreference(this.requireActivity());
        if (!userDataPreference.isLoggedIn()) {
            if (this.visited) {
                navController.popBackStack();
            } else {
                navController.navigate(R.id.loginFragment);
            }

            this.visited = true;

            return;
        }

        RecyclerView recyclerView = view.findViewById(R.id.subscriptions_list_recycler_view);
        this.topicAdapter = new TopicAdapter(this.getContext(), view);

        recyclerView.setAdapter(topicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        TopicViewModel topicViewModel = new ViewModelProvider(this.requireActivity()).get(TopicViewModel.class);
        topicViewModel.getTopicsUserJoined(userDataPreference.getLoggedId(), this);

    }

    @Override
    public void onGetTopicsUserJoined(LiveData<List<TopicWithUser>> topicsUserJoined) {
        topicsUserJoined.observe(getViewLifecycleOwner(), new Observer<List<TopicWithUser>>() {
            @Override
            public void onChanged(List<TopicWithUser> topicWithUsers) {
                topicAdapter.setTopics(topicWithUsers);
            }
        });
    }
}