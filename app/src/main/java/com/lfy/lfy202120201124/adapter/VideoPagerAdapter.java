package com.lfy.lfy202120201124.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.db.VideoDbHelper;
import com.lfy.lfy202120201124.entity.Comment;

import java.util.List;

public class VideoPagerAdapter extends RecyclerView.Adapter<VideoPagerAdapter.VideoViewHolder> {

    private Fragment fragment;
    private List<String> videoPaths;
    private VideoViewHolder currentVideoViewHolder;
    private VideoDbHelper dbHelper;

    public VideoPagerAdapter(Fragment fragment, List<String> videoPaths) {
        this.fragment = fragment;
        this.videoPaths = videoPaths;
        this.dbHelper = new VideoDbHelper(fragment.getContext());
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        String videoPath = videoPaths.get(getRealPosition(position));
        holder.videoView.setVideoURI(Uri.parse(videoPath));
        holder.videoView.setOnPreparedListener(mp -> {
            mp.start();
            mp.setLooping(true);
        });
        holder.bindComments(videoPath);
        holder.buttonShowComments.setOnClickListener(v -> {
            if (holder.commentsLayout.getVisibility() == View.GONE) {
                holder.commentsLayout.setVisibility(View.VISIBLE);
            } else {
                holder.commentsLayout.setVisibility(View.GONE);
            }
        });
        holder.buttonCloseComments.setOnClickListener(v -> {
            holder.commentsLayout.setVisibility(View.GONE);
        });
        currentVideoViewHolder = holder;
    }

    @Override
    public int getItemCount() {
        return videoPaths.size() * 1000; // Return a large number to simulate infinite scrolling
    }

    public int getRealPosition(int position) {
        return position % videoPaths.size();
    }

    public int getInitialPosition() {
        return Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % videoPaths.size());
    }

    public void pauseCurrentVideo() {
        if (currentVideoViewHolder != null && currentVideoViewHolder.videoView.isPlaying()) {
            currentVideoViewHolder.videoView.pause();
        }
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        RecyclerView recyclerViewComments;
        EditText editTextComment;
        Button buttonSubmitComment;
        Button buttonShowComments;
        ImageButton buttonCloseComments;
        LinearLayout commentsLayout;
        CommentAdapter commentAdapter;
        VideoDbHelper dbHelper;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            recyclerViewComments = itemView.findViewById(R.id.recycler_view_comments);
            editTextComment = itemView.findViewById(R.id.edit_text_comment);
            buttonSubmitComment = itemView.findViewById(R.id.button_submit_comment);
            buttonShowComments = itemView.findViewById(R.id.button_show_comments);
            buttonCloseComments = itemView.findViewById(R.id.button_close_comments);
            commentsLayout = itemView.findViewById(R.id.comments_layout);
            dbHelper = new VideoDbHelper(itemView.getContext());
        }

        public void bindComments(String videoUrl) {
            List<Comment> comments = dbHelper.getCommentsByVideoUrl(videoUrl);
            commentAdapter = new CommentAdapter(comments);
            recyclerViewComments.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            recyclerViewComments.setAdapter(commentAdapter);

            buttonSubmitComment.setOnClickListener(v -> {
                String commentText = editTextComment.getText().toString().trim();
                if (!commentText.isEmpty()) {
                    dbHelper.addComment(new Comment(videoUrl, "User", commentText));
                    editTextComment.setText("");
                    refreshComments(videoUrl);
                }
            });
        }

        private void refreshComments(String videoUrl) {
            List<Comment> updatedComments = dbHelper.getCommentsByVideoUrl(videoUrl);
            commentAdapter.updateComments(updatedComments);
        }
    }
}
