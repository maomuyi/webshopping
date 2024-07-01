package com.lfy.lfy202120201124.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.db.VideoDbHelper;
import com.lfy.lfy202120201124.entity.Comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        holder.bindLikeCount(videoPath);
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
        holder.buttonLike.setOnClickListener(v -> {
            holder.incrementLikeCount(videoPath);
            holder.showLikeAnimation();
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
        ImageButton buttonLike;
        LinearLayout commentsLayout;
        TextView textViewTimestamp;
        TextView textLikeCount;
        CommentAdapter commentAdapter;
        VideoDbHelper dbHelper;
        int likeCount = 0;
        Context context;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            recyclerViewComments = itemView.findViewById(R.id.recycler_view_comments);
            editTextComment = itemView.findViewById(R.id.edit_text_comment);
            buttonSubmitComment = itemView.findViewById(R.id.button_submit_comment);
            buttonShowComments = itemView.findViewById(R.id.button_show_comments);
            buttonCloseComments = itemView.findViewById(R.id.button_close_comments);
            buttonLike = itemView.findViewById(R.id.button_like);
            commentsLayout = itemView.findViewById(R.id.comments_layout);
            textViewTimestamp = itemView.findViewById(R.id.text_view_timestamp);
            textLikeCount = itemView.findViewById(R.id.text_like_count);
            dbHelper = new VideoDbHelper(itemView.getContext());
            context = itemView.getContext();
        }

        public void bindComments(String videoUrl) {
            List<Comment> comments = dbHelper.getCommentsByVideoUrl(videoUrl);
            commentAdapter = new CommentAdapter(comments);
            recyclerViewComments.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            recyclerViewComments.setAdapter(commentAdapter);

            buttonSubmitComment.setOnClickListener(v -> {
                String commentText = editTextComment.getText().toString().trim();
                if (!commentText.isEmpty()) {
                    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                    textViewTimestamp.setText(timestamp);
                    dbHelper.addComment(new Comment(videoUrl, "User", commentText, timestamp));
                    editTextComment.setText("");
                    refreshComments(videoUrl);
                }
            });
        }

        public void bindLikeCount(String videoUrl) {
            likeCount = dbHelper.getLikeCount(videoUrl);
            textLikeCount.setText(String.valueOf(likeCount));
        }

        public void incrementLikeCount(String videoUrl) {
            likeCount++;
            textLikeCount.setText(String.valueOf(likeCount));
            dbHelper.updateLikeCount(videoUrl, likeCount);
            buttonLike.setImageResource(R.drawable.ic_like_filled); // 更新图标为红色
        }

        public void showLikeAnimation() {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.like_animation);
            buttonLike.startAnimation(animation);
        }

        private void refreshComments(String videoUrl) {
            List<Comment> updatedComments = dbHelper.getCommentsByVideoUrl(videoUrl);
            commentAdapter.updateComments(updatedComments);
        }
    }
}
