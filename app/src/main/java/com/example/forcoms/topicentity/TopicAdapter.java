package com.example.forcoms.topicentity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forcoms.R;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {
    private final LayoutInflater layoutInflater;
    private final NavController navController;
    private List<TopicWithUser> topics;

    public TopicAdapter(Context context, View view) {
        this.layoutInflater = LayoutInflater.from(context);
        navController = Navigation.findNavController(view);
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
                Bundle bundle = new Bundle();
                bundle.putLong("TOPIC_ID", currentTopic.topicData.getId());
                navController.navigate(R.id.commentsListFragment, bundle);
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
