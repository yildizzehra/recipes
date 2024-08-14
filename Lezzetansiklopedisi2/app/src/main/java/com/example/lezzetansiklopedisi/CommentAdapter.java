package com.example.lezzetansiklopedisi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> comments;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.usernameTextView.setText(comment.getUsername());

        if (comment.getText() != null && !comment.getText().isEmpty()) {
            holder.commentTextView.setText(comment.getText());
            holder.commentTextView.setVisibility(View.VISIBLE);
            holder.commentImageView.setVisibility(View.GONE);
        } else if (comment.getImage() != null && !comment.getImage().isEmpty()) {
            byte[] decodedString = Base64.decode(comment.getImage(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.commentImageView.setImageBitmap(bitmap);
            holder.commentImageView.setVisibility(View.VISIBLE);
            holder.commentTextView.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView commentTextView;
        ImageView commentImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username_text_view);
            commentTextView = itemView.findViewById(R.id.comment_text_view);
            commentImageView = itemView.findViewById(R.id.comment_image_view);
        }
    }
}
