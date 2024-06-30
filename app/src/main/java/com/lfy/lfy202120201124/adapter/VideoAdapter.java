package com.lfy.lfy202120201124.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.entity.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private static final String TAG = "VideoAdapter";

    private Context context;
    private List<Video> videoList;
    private SimpleExoPlayer exoPlayer;
    private int currentPlayingPosition = -1;
    private Handler handler;
    private Runnable pauseVideoRunnable;
    private RecyclerView recyclerView;

    public VideoAdapter(Context context, List<Video> videoList, RecyclerView recyclerView) {
        this.context = context;
        this.videoList = videoList;
        this.exoPlayer = new SimpleExoPlayer.Builder(context).build();
        this.handler = new Handler(Looper.getMainLooper());
        this.pauseVideoRunnable = () -> {
            if (currentPlayingPosition != -1) {
                pauseVideo(currentPlayingPosition);
            }
        };
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载视频项布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        // 绑定视频数据到视图
        Video video = videoList.get(position);
        holder.bind(video, position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        // 视图附加到窗口时恢复视频播放
        holder.resumeVideo();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        // 视图从窗口分离时暂停视频播放
        holder.pauseVideo();
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    // 更新视频列表并通知适配器刷新
    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    // 获取当前正在播放的视频位置
    public int getCurrentPlayingPosition() {
        return currentPlayingPosition;
    }

    // 视频项的视图持有者
    class VideoViewHolder extends RecyclerView.ViewHolder {
        PlayerView playerView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            // 初始化PlayerView
            playerView = itemView.findViewById(R.id.player_view);
        }

        public void bind(Video video, int position) {
            // 加载视频数据到ExoPlayer
            Uri videoUri = Uri.parse(video.getUrl());
            MediaItem mediaItem = MediaItem.fromUri(videoUri);
            exoPlayer.setMediaItem(mediaItem);

            Log.d(TAG, "Binding video at position " + position + ", URL: " + video.getUrl());

            // 如果是当前播放位置，则显示PlayerView并播放视频
            if (position == currentPlayingPosition) {
                playerView.setVisibility(View.VISIBLE);
                playerView.setPlayer(exoPlayer);
                exoPlayer.prepare();
                exoPlayer.play();
            } else {
                // 否则隐藏PlayerView并清空播放器
                playerView.setVisibility(View.GONE);
                playerView.setPlayer(null);
            }
        }

        // 恢复视频播放
        public void resumeVideo() {
            if (currentPlayingPosition != -1) {
                playerView.setVisibility(View.VISIBLE);
                playerView.setPlayer(exoPlayer);
                exoPlayer.setPlayWhenReady(true);
                handler.removeCallbacks(pauseVideoRunnable);
            }
        }

        // 暂停视频播放
        public void pauseVideo() {
            if (currentPlayingPosition != -1) {
                exoPlayer.setPlayWhenReady(false);
                playerView.setVisibility(View.GONE);
                playerView.setPlayer(null);
            }
        }
    }

    // 播放指定位置的视频
    public void playVideo(int position) {
        if (position != currentPlayingPosition) {
            pauseVideo(currentPlayingPosition);
            currentPlayingPosition = position;
            handler.postDelayed(pauseVideoRunnable, 3000); // 自动暂停3秒后
            notifyDataSetChanged();
        }
    }

    // 根据位置暂停视频播放
    private void pauseVideo(int position) {
        VideoViewHolder viewHolder = (VideoViewHolder) recyclerView.findViewHolderForAdapterPosition(position);
        if (viewHolder != null) {
            viewHolder.pauseVideo();
        }
    }

    // 释放ExoPlayer资源
    public void releasePlayer() {
        exoPlayer.release();
    }
}
