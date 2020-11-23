package com.example.forcoms;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.forcoms.topicentity.TopicData;
import com.example.forcoms.topicentity.TopicViewModel;


public class EditDeleteTopicFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_delete_topic, container, false);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TopicData topicData = getArguments().getParcelable("CONTENT");

        EditText title = view.findViewById(R.id.edit_topic_content);
        Button editButton = view.findViewById(R.id.edit_topic_button);
        Button deleteButton = view.findViewById(R.id.edit_topic_delete_button);
        TopicViewModel topicViewModel = new ViewModelProvider(this.requireActivity()).get(TopicViewModel.class);

        title.setText(topicData.getTitle());

        NavController navController = Navigation.findNavController(view);

        editButton.setOnClickListener(view1 -> {
            String newTitle = title.getText().toString();
            if (TextUtils.isEmpty(newTitle.trim())) {
                Toast.makeText(view.getContext(), "Judul Topik Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            topicData.setTitle(newTitle);
            topicViewModel.updateTopic(topicData);
            navController.popBackStack();
        });

        deleteButton.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.requireActivity());
            builder.setTitle("Hapus Topik?");
            builder.setMessage("Setelah di hapus, topik tidak dapat di kembalikan lagi");
            builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    topicViewModel.deleteTopic(topicData);
                    navController.popBackStack();
                }
            });
            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        });


    }
}