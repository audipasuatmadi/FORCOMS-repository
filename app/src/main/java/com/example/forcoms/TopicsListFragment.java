package com.example.forcoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.sharedpreferences.UserDataPreference;
import com.example.forcoms.topicentity.TopicData;
import com.example.forcoms.topicentity.TopicViewModel;
import com.example.forcoms.userentity.UserData;
import com.example.forcoms.userentity.UserViewModel;


public class TopicsListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topics_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText titleEdit = view.findViewById(R.id.dummy_title);
        Button button = view.findViewById(R.id.dummy_button);

        UserDataPreference userDataPreference = new UserDataPreference(this.getContext());
        TopicViewModel topicViewModel = new ViewModelProvider(requireActivity()).get(TopicViewModel.class);

        button.setOnClickListener(view1 -> {
            TopicData topicData = new TopicData(titleEdit.getText().toString(), userDataPreference.getLoggedId());
            topicViewModel.addTopic(topicData);
        });
    }
}