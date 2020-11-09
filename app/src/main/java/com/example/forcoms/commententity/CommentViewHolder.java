package com.example.forcoms.commententity;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forcoms.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    public final TextView content;
    public final TextView creator;
    public final CardView cardView;

    public CommentViewHolder(View itemView) {
        super(itemView);

        content = itemView.findViewById(R.id.comment_content);
        creator = itemView.findViewById(R.id.comment_creator);
        cardView = itemView.findViewById(R.id.card_comment_container);
    }
}
