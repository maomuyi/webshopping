package com.lfy.lfy202120201124.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.entity.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> commentList;
    private static final String FIXED_IMAGE_URL = "G:\\lfy202120201124\\app\\src\\main\\res\\mipmap-mdpi\\touxiang.jpg"; // 固定图片 URL

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.usernameTextView.setText(comment.getUserName());
        holder.contentTextView.setText(comment.getContent());
        holder.timestampTextView.setText(comment.getTimestamp());
        Glide.with(holder.itemView.getContext()).load(FIXED_IMAGE_URL).into(holder.imageView); // 使用固定图片 URL
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView contentTextView;
        TextView timestampTextView;
        ImageView imageView;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            contentTextView = itemView.findViewById(R.id.content);
            timestampTextView = itemView.findViewById(R.id.timestamp);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public void updateComments(List<Comment> comments) {
        this.commentList = comments;
        notifyDataSetChanged();
    }
}
