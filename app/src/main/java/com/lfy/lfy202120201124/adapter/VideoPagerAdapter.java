package com.lfy.lfy202120201124.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.R;

import java.util.List;

public class VideoPagerAdapter extends RecyclerView.Adapter<VideoPagerAdapter.VideoViewHolder> {

    private Fragment fragment;
    private List<String> videoPaths;
    private VideoViewHolder currentVideoViewHolder;

    public VideoPagerAdapter(Fragment fragment, List<String> videoPaths) {
        this.fragment = fragment;
        this.videoPaths = videoPaths;
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

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
        }
    }
}
