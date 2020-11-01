package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.topicentity.TopicAdapter;
import com.example.forcoms.topicentity.TopicData;
import com.example.forcoms.topicentity.TopicViewModel;
import com.example.forcoms.topicentity.TopicWithUser;
import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class TopicsListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topics_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_open_topic);
        NavController navController = Navigation.findNavController(view);

        RecyclerView recyclerView = view.findViewById(R.id.topic_lists_recycler_view);
        final TopicAdapter topicAdapter = new TopicAdapter(this.getContext());

        UserDataPreference userDataPreference = new UserDataPreference(requireActivity());

        recyclerView.setAdapter(topicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        TopicViewModel topicViewModel = new ViewModelProvider(requireActivity()).get(TopicViewModel.class);
        topicViewModel.getAllTopics().observe(getViewLifecycleOwner(), new Observer<List<TopicWithUser>>() {
            @Override
            public void onChanged(List<TopicWithUser> topicWithUsers) {
                topicAdapter.setTopics(topicWithUsers);
            }
        });

        floatingActionButton.setOnClickListener(view1 -> {
            if (userDataPreference.isLoggedIn()) {
                navController.navigate(R.id.addTopicFragment);
            } else {
                navController.navigate(R.id.navigation_profile);
            }
        });

    }
}