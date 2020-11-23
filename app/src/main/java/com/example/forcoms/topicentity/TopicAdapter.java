package com.example.forcoms.topicentity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forcoms.R;
import com.example.forcoms.sharedpreferences.UserDataPreference;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {
    private final LayoutInflater layoutInflater;
    private final NavController navController;
    private List<TopicWithUser> topics;
    private Context context;
    private UserDataPreference userDataPreference;

    public TopicAdapter(Context context, View view) {
        this.layoutInflater = LayoutInflater.from(context);
        navController = Navigation.findNavController(view);
        this.userDataPreference = new UserDataPreference(view.getContext());
        this.context = context;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        if (!topics.isEmpty()) {
            TopicWithUser currentTopic = topics.get(position);
            holder.title.setText(currentTopic.topicData.getTitle());
            holder.creator.setText(currentTopic.userData.getUsername());

            holder.container.setOnClickListener(view -> {

                if (this.userDataPreference.isLoggedIn()) {
                    if (currentTopic.userData.getId() == this.userDataPreference.getLoggedId()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
                        builder.setTitle("Menu pemilik topik");
                        builder.setMessage("Apakah anda ingin melihat topik anda atau ingin melakukan edit/delete?");
                        builder.setPositiveButton("Edit/Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("CONTENT", currentTopic.topicData);
                                navController.navigate(R.id.editDeleteTopicFragment, bundle);
                            }
                        });
                        builder.setNegativeButton("Lihat Topik", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Bundle bundle = new Bundle();
                                bundle.putLong("TOPIC_ID", currentTopic.topicData.getId());
                                navController.navigate(R.id.commentsListFragment, bundle);
                            }
                        });

                        builder.create().show();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putLong("TOPIC_ID", currentTopic.topicData.getId());
                        navController.navigate(R.id.commentsListFragment, bundle);
                    }
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putLong("TOPIC_ID", currentTopic.topicData.getId());
                    navController.navigate(R.id.commentsListFragment, bundle);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (topics != null) {
            return topics.size();
        } else {
            return 0;
        }
    }

    public void setTopics(List<TopicWithUser> topics) {
        this.topics = topics;
        notifyDataSetChanged();
    }
}
