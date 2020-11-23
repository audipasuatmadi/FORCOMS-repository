package com.example.forcoms.commententity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    CommentViewModel commentViewModel;
    NavController navController;

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

        commentViewModel = new ViewModelProvider(this.getActivity()).get(CommentViewModel.class);
        navController = Navigation.findNavController(view);

        commentButton.setOnClickListener(view1-> {
            String commentValue = commentEditText.getText().toString();
            if (TextUtils.isEmpty(commentValue.trim())) {
                Toast.makeText(view.getContext(), "Tidak ada komentar", Toast.LENGTH_SHORT).show();
                return;
            }
            content.setContent(commentValue);
            commentViewModel.updateComment(content);

            navController.popBackStack();
        });



        deleteButton.setOnClickListener(view1 -> {
            AlertDialog dialog = this.getDeleteDialog(content);
            dialog.show();
        });
    }

    private AlertDialog getDeleteDialog(CommentData content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.requireActivity());
        builder.setMessage("Setelah di hapus, komentar tidak dapat di kembalikan lagi")
                .setTitle("Hapus Komentar?")
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        commentViewModel.deleteComment(content);
                        navController.popBackStack();
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }

}