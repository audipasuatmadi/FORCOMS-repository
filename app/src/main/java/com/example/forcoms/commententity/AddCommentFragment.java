package com.example.forcoms.commententity;

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
import com.example.forcoms.sharedpreferences.UserDataPreference;

public class AddCommentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText content = view.findViewById(R.id.comment_add_comment_edit_ext);
        Button submitButton = view.findViewById(R.id.comment_submit_button);

        CommentViewModel commentViewModel = new ViewModelProvider(requireActivity()).get(CommentViewModel.class);

        submitButton.setOnClickListener(view1 -> {
            String contentVal = content.getText().toString();

            if (TextUtils.isEmpty(contentVal.trim())) {
                Toast.makeText(requireContext(), "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                CommentData commentData = new CommentData(new UserDataPreference(requireActivity()).getLoggedId(), getArguments().getLong("TOPIC_ID"), contentVal);
                commentViewModel.addComment(commentData);
                NavController navController = Navigation.findNavController(view);
                navController.popBackStack();
            }

        });

        super.onViewCreated(view, savedInstanceState);
    }
}