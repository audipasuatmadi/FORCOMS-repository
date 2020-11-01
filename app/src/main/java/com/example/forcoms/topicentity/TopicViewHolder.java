package com.example.forcoms.topicentity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.forcoms.R;

public class TopicViewHolder extends RecyclerView.ViewHolder {
    public final TextView title;
    public final TextView creator;

    public TopicViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.topic_title);
        creator = itemView.findViewById(R.id.topic_creator);
    }
}
