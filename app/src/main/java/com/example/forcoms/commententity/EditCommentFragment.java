package com.example.forcoms.commententity;

import android.os.Bundle;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcoms.R;
import com.example.forcoms.commententity.CommentData;

public class EditCommentFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CommentData content = getArguments().getParcelable("CONTENT");

        EditText commentEditText = view.findViewById(R.id.edit_comment_content);
        commentEditText.setText(content.getContent());
        Button commentButton = view.findViewById(R.id.edit_comment_button);
        Button deleteButton = view.findViewById(R.id.edit_comment_delete_button);
        CommentViewModel commentViewModel = new ViewModelProvider(this.getActivity()).get(CommentViewModel.class);

        commentButton.setOnClickListener(view1-> {
            String commentValue = commentEditText.getText().toString();
            if (TextUtils.isEmpty(commentValue.trim())) {
                Toast.makeText(view.getContext(), "Tidak ada komentar", Toast.LENGTH_SHORT).show();
                return;
            }
            content.setContent(commentValue);
            commentViewModel.updateComment(content);
            NavController navController = Navigation.findNavController(view);
            navController.popBackStack();
        });
    }

}