package com.example.forcoms.topicentity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.R;
import com.example.forcoms.commententity.CommentData;
import com.example.forcoms.commententity.CommentViewModel;
import com.example.forcoms.sharedpreferences.UserDataPreference;

public class AddTopicFragment extends Fragment implements AddTopicListener {

    private long userId;
    private String commentValue;
    private CommentViewModel commentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_topic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText titleInput = view.findViewById(R.id.add_topic_title);
        EditText commentInput = view.findViewById(R.id.add_topic_comment);
        Button submitButton = view.findViewById(R.id.add_topic_submit_button);

        TopicViewModel topicViewModel = new ViewModelProvider(requireActivity()).get(TopicViewModel.class);
        commentViewModel = new ViewModelProvider(requireActivity()).get(CommentViewModel.class);

        NavController navController = Navigation.findNavController(view);

        submitButton.setOnClickListener(view1 -> {
            String titleValue = titleInput.getText().toString();
            commentValue = commentInput.getText().toString();

            if (TextUtils.isEmpty(titleValue.trim()) || TextUtils.isEmpty(commentValue.trim())) {
                Toast.makeText(this.getContext(), "input tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                userId = new UserDataPreference(requireActivity()).getLoggedId();
                TopicData topicData = new TopicData(titleValue, userId);
                topicViewModel.addTopic(topicData, this);

//                Toast.makeText(this.getContext(), "topik berhasil dibuka", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        });

    }

    @Override
    public void onAddTopic(long topicId) {
        CommentData commentData = new CommentData(userId, topicId, commentValue);
        commentViewModel.addComment(commentData);
        Toast.makeText(this.requireActivity(), ""+topicId, Toast.LENGTH_SHORT).show();
    }
}