package com.example.forcoms.commententity;

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
import com.example.forcoms.sharedpreferences.UserDataPreference;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<CommentWithUser> commentsInATopic;
    private UserDataPreference userDataPreference;
    private NavController navController;

    public CommentAdapter(Context context,View view) {
        this.layoutInflater = LayoutInflater.from(context);
        this.userDataPreference = new UserDataPreference(context);
        this.navController = Navigation.findNavController(view);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        if (!commentsInATopic.isEmpty()) {
            CommentWithUser currentComment = commentsInATopic.get(position);
            holder.content.setText(currentComment.commentData.getContent());
            holder.creator.setText(currentComment.userData.getUsername());
            holder.cardView.setOnClickListener(view->{
                if (currentComment.userData.getId() == userDataPreference.getLoggedId()) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("CONTENT",currentComment.commentData);
                    navController.navigate(R.id.editCommentFragment,bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (commentsInATopic != null) {
            return commentsInATopic.size();
        } else {
            return 0;
        }
    }

    public void setComments(List<CommentWithUser> commentsInATopic) {
        this.commentsInATopic = commentsInATopic;
        notifyDataSetChanged();
    }
}
