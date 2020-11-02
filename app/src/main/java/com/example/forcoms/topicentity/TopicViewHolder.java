package com.example.forcoms.topicentity;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forcoms.R;

public class TopicViewHolder extends RecyclerView.ViewHolder {
    public final TextView title;
    public final TextView creator;
    public final CardView container;

    public TopicViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.topic_title);
        creator = itemView.findViewById(R.id.topic_creator);
        container = itemView.findViewById(R.id.card_topic_container);
    }
}
